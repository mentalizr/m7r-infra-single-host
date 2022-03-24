package org.mentalizr.infra.buildEntities.html;

import de.arthurpicht.utils.io.nio2.FileUtils;
import org.mentalizr.infra.InfraRuntimeException;
import org.mentalizr.infra.buildEntities.html.files.InitHtml;
import org.mentalizr.infra.buildEntities.html.files.LoginChunkHtml;
import org.mentalizr.infra.buildEntities.html.files.LoginVoucherChunkHtml;
import org.mentalizr.infra.buildEntities.html.files.PatientChunkHtml;
import org.mentalizr.infra.utils.FileHelper;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class HtmlFiles {

    private static HtmlFiles htmlFiles;

    private final List<Path> htmlFileList;
    private long checksum;

    public static HtmlFiles getInstance() {
        if (htmlFiles == null) htmlFiles = new HtmlFiles();
        return htmlFiles;
    }

    private HtmlFiles() {
        this.htmlFileList = new ArrayList<>();
        this.htmlFileList.add(new InitHtml().asPath());
        this.htmlFileList.add(new LoginChunkHtml().asPath());
        this.htmlFileList.add(new LoginVoucherChunkHtml().asPath());
        this.htmlFileList.add(new PatientChunkHtml().asPath());
        checkExistence();
        computeChecksum();
    }

    public List<Path> getHtmlFileList() {
        return this.htmlFileList;
    }

    public long getChecksum() {
        return this.checksum;
    }

    private void computeChecksum() {
        try {
            this.checksum = FileHelper.computeCrc32(this.htmlFileList);
        } catch (IOException e) {
            throw new InfraRuntimeException("Exception on computing crc32 checksum: " + e.getMessage(), e);
        }
    }

    private void checkExistence() {
        for (Path file : htmlFileList) {
            if (!FileUtils.isExistingRegularFile(file))
                throw new InfraRuntimeException("File not found: [" + file.toAbsolutePath() + "].");
        }
    }

}

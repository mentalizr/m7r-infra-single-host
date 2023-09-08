package org.mentalizr.infra.buildEntities.html;

import de.arthurpicht.utils.core.strings.StringSubstitutor;
import de.arthurpicht.utils.core.strings.StringSubstitutorConfiguration;
import de.arthurpicht.utils.io.checksum.Checksums;
import de.arthurpicht.utils.io.nio2.FileUtils;
import de.arthurpicht.utils.io.tempDir.TempDirs;
import org.mentalizr.commons.paths.host.hostDir.M7rHostTempDir;
import org.mentalizr.infra.InfraRuntimeException;
import org.mentalizr.infra.build.Backend;
import org.mentalizr.infra.buildEntities.html.files.InitHtml;
import org.mentalizr.infra.buildEntities.html.files.LoginChunkHtml;
import org.mentalizr.infra.buildEntities.html.files.LoginVoucherChunkHtml;
import org.mentalizr.infra.buildEntities.html.files.PatientChunkHtml;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class HtmlFilesRegistry {

    private static HtmlFilesRegistry htmlFilesRegistry;

    private final List<Path> htmlFileList;
    private long checksum;

    public static HtmlFilesRegistry getInstance() {
        if (htmlFilesRegistry == null) htmlFilesRegistry = new HtmlFilesRegistry();
        return htmlFilesRegistry;
    }

    private HtmlFilesRegistry() {
        Path initHtmlExtended = buildInitHtmlExtended();
        this.htmlFileList = new ArrayList<>();
        this.htmlFileList.add(initHtmlExtended);
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

    private Path buildInitHtmlExtended() {
        Path initHtml = new InitHtml().asPath();
        if (!FileUtils.isExistingRegularFile(initHtml))
            throw new InfraRuntimeException("File not found: [" + initHtml.toAbsolutePath() + "].");
        String initHtmlString = getInitHtmlAsString();
        StringSubstitutorConfiguration stringSubstitutorConfiguration = getStringSubstitutionConfiguration();
        String initHtmlExtendedString = StringSubstitutor.substitute(initHtmlString, stringSubstitutorConfiguration);
        Path tempDir = createTempDir();
        Path initHtmlExtendedPath = tempDir.resolve(initHtml.getFileName());
        writeInitHtmlExtendedToTempDir(initHtmlExtendedPath, initHtmlExtendedString);
        return initHtmlExtendedPath;
    }

    private static Path createTempDir() {
        try {
            return TempDirs.createUniqueTempDirAutoRemove(new M7rHostTempDir().asPath()).asPath();
        } catch (IOException e) {
            throw new InfraRuntimeException("Exception on creating temp dir: " + e.getMessage(), e);
        }
    }

    private static String getInitHtmlAsString() {
        Path initHtml = new InitHtml().asPath();
        try {
            return Files.readString(initHtml);
        } catch (IOException e) {
            throw new InfraRuntimeException("Exception reading [" + initHtml.toAbsolutePath() + "]: " + e.getMessage(), e);
        }
    }

    private static void writeInitHtmlExtendedToTempDir(Path initHtmlExtended, String initHtmlExtendedString) {
        try {
            Files.writeString(initHtmlExtended, initHtmlExtendedString);
        } catch (IOException e) {
            throw new InfraRuntimeException("Could not write [" + initHtmlExtended.toAbsolutePath() + "]: " + e.getMessage(), e);
        }
    }

    private static StringSubstitutorConfiguration getStringSubstitutionConfiguration() {
        return new StringSubstitutorConfiguration.Builder()
                .withPre("${{")
                .withPost("}}")
                .withSubstitution("buildId", Backend.getBuildIdString())
                .build();
    }

    private void computeChecksum() {
        try {
            this.checksum = Checksums.computeCrc32(this.htmlFileList);
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

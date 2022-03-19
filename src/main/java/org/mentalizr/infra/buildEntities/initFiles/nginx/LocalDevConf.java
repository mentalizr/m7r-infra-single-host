package org.mentalizr.infra.buildEntities.initFiles.nginx;

import de.arthurpicht.utils.core.strings.Strings;
import org.mentalizr.infra.InfraRuntimeException;
import org.mentalizr.infra.buildEntities.initFiles.InitFile;
import org.mentalizr.infra.utils.IOUtils;
import org.mentalizr.infra.utils.InputStreams;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class LocalDevConf implements InitFile {

    private final String string;

    public static LocalDevConf getInstance() {
        return new LocalDevConf();
    }

    private LocalDevConf() {
        List<String> stringList = getAsStrings();
        this.string = Strings.listing(stringList, "\n");
    }

    @Override
    public String getFileName() {
        return "localdev.conf";
    }

    @Override
    public String getContent() {
        return this.string;
    }

    private List<String> getAsStrings() {
        InputStream inputStream = IOUtils.getFileFromResourceAsStream("nginx/localdev.conf");
        try {
            return InputStreams.toStrings(inputStream);
        } catch (IOException e) {
            throw new InfraRuntimeException("Could not read " + getFileName() + " from resources.");
        }
    }
}

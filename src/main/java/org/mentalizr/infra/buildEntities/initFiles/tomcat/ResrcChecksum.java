package org.mentalizr.infra.buildEntities.initFiles.tomcat;

import org.mentalizr.infra.buildEntities.initFiles.InitFile;

public class ResrcChecksum implements InitFile {

    private final String content;

    public ResrcChecksum(long checksum) {
        this.content = Long.toString(checksum);
    }

    @Override
    public String getFileName() {
        return "checksum";
    }

    @Override
    public String getContent() {
        return this.content;
    }
}

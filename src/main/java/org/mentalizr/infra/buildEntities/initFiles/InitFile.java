package org.mentalizr.infra.buildEntities.initFiles;

import org.mentalizr.infra.utils.FileHelper;

import java.io.IOException;
import java.nio.file.Path;

public interface InitFile {

    String getFileName();

    String getContent();

    default Path writeToHostTempDir() throws IOException {
        return FileHelper.writeToM7rInfraTempDir(getFileName(), getContent());
    }

}

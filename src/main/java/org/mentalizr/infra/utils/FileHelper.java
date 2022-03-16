package org.mentalizr.infra.utils;

import de.arthurpicht.utils.io.tempDir.TempDir;
import de.arthurpicht.utils.io.tempDir.TempDirs;
import org.mentalizr.commons.M7rDirs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileHelper {

    public static Path writeToM7rTempDir(String fileName, String content) throws IOException {
        M7rDirs m7rDirs = new M7rDirs();
        if (!m7rDirs.existsTempDir()) Files.createDirectories(m7rDirs.getTempDir());
        TempDir tempDir = TempDirs.createUniqueTempDirAutoRemove(m7rDirs.getTempDir());
        Path file = tempDir.asPath().resolve(fileName);
        Files.writeString(file, content);
        return file;
    }


}

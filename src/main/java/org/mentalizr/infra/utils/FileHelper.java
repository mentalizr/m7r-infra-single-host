package org.mentalizr.infra.utils;

import de.arthurpicht.utils.io.tempDir.TempDir;
import de.arthurpicht.utils.io.tempDir.TempDirs;
import org.mentalizr.commons.dirs.host.M7rHostTempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileHelper {

    public static Path writeToM7rInfraTempDir(String fileName, String content) throws IOException {
        M7rHostTempDir m7RHostTempDir = M7rHostTempDir.createInstance();
        if (!m7RHostTempDir.exists()) Files.createDirectories(m7RHostTempDir.asPath());
        TempDir tempDir = TempDirs.createUniqueTempDirAutoRemove(m7RHostTempDir.asPath());
        Path file = tempDir.asPath().resolve(fileName);
        Files.writeString(file, content);
        return file;
    }

}

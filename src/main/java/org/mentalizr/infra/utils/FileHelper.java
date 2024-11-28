package org.mentalizr.infra.utils;

import de.arthurpicht.utils.io.tempDir.TempDir;
import org.mentalizr.commons.paths.host.hostDir.M7rHostTempDir;
import org.mentalizr.infra.InfraRuntimeException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileHelper {

    public static Path writeToM7rInfraTempDir(String fileName, String content) throws IOException {
        M7rHostTempDir m7RHostTempDir = new M7rHostTempDir();
        if (!m7RHostTempDir.exists()) Files.createDirectories(m7RHostTempDir.asPath());
        TempDir tempDir = new TempDir.Creator().withParentDir(new M7rHostTempDir().asPath()).create();
        Path file = tempDir.asPath().resolve(fileName);
        Files.writeString(file, content);
        return file;
    }

    public static TempDir createM7rInfraTempDir() {
        M7rHostTempDir m7RHostTempDir = new M7rHostTempDir();
        try {
            if (!m7RHostTempDir.exists()) Files.createDirectories(m7RHostTempDir.asPath());
            return new TempDir.Creator().withParentDir(m7RHostTempDir.asPath()).create();
        } catch (IOException | TempDir.TempDirCreationException e) {
            throw new InfraRuntimeException("Exception on creating temp dir: " + e.getMessage(), e);
        }
    }

    public static Path createM7rInfraTempFile(String filename, String content) {
        Path tempFile = createM7rInfraTempDir().asPath().resolve(filename);
        try {
            return Files.writeString(tempFile, content);
        } catch (IOException e) {
            throw new InfraRuntimeException("Exception on creating temp checksum file: " + e.getMessage(), e);
        }
    }

}

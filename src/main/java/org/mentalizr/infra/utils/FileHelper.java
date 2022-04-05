package org.mentalizr.infra.utils;

import de.arthurpicht.utils.core.assertion.MethodPreconditions;
import de.arthurpicht.utils.io.nio2.FileUtils;
import de.arthurpicht.utils.io.tempDir.TempDir;
import de.arthurpicht.utils.io.tempDir.TempDirs;
import org.mentalizr.commons.paths.host.hostDir.M7rHostTempDir;
import org.mentalizr.infra.InfraRuntimeException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CRC32;

import static de.arthurpicht.utils.core.assertion.MethodPreconditions.assertArgumentNotNull;

public class FileHelper {

    public static Path writeToM7rInfraTempDir(String fileName, String content) throws IOException {
        M7rHostTempDir m7RHostTempDir = M7rHostTempDir.createInstance();
        if (!m7RHostTempDir.exists()) Files.createDirectories(m7RHostTempDir.asPath());
        TempDir tempDir = TempDirs.createUniqueTempDirAutoRemove(m7RHostTempDir.asPath());
        Path file = tempDir.asPath().resolve(fileName);
        Files.writeString(file, content);
        return file;
    }

    public static TempDir createM7rInfraTempDir() {
        M7rHostTempDir m7RHostTempDir = M7rHostTempDir.createInstance();
        try {
            if (!m7RHostTempDir.exists()) Files.createDirectories(m7RHostTempDir.asPath());
            return TempDirs.createUniqueTempDirAutoRemove(m7RHostTempDir.asPath());
        } catch (IOException e) {
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

    public static long computeCrc32(Path path) throws IOException {
        CRC32 crc32 = new CRC32();
        InputStream inputStream = new FileInputStream(path.toFile());
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            crc32.update(buffer, 0, bytesRead);
        }
        return crc32.getValue();
    }

    public static long computeCrc32(List<Path> paths) throws IOException {
        CRC32 crc32 = new CRC32();
        for (Path file : paths) {
            InputStream inputStream = new FileInputStream(file.toFile());
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                crc32.update(buffer, 0, bytesRead);
            }
        }
        return crc32.getValue();
    }

    @Deprecated
    public static void copyDirectory(Path source, Path target, CopyOption... options) throws IOException {
        assertArgumentNotNull("source", source);
        assertArgumentNotNull("target", target);

//        String pathString = "/home/m7radmin/gitrepos/m7r/core/m7r-frontend/node_modules/@fortawesome/fontawesome-free/webfonts";
//        Path path = Paths.get(pathString);
//        System.out.println("exists? " + FileUtils.isExistingDirectory(path));
//        System.out.println("exists [" + source.toString() + "]? " + FileUtils.isExistingDirectory(source));
//        System.out.println("equals? " + pathString.equals(source.toString()));

        if (!FileUtils.isExistingDirectory(source)) throw new IllegalArgumentException("Directory not found: [" + source.toAbsolutePath() + "].");
        if (!FileUtils.isExistingDirectory(target)) throw new IllegalArgumentException("Directory not found: [" + target.toAbsolutePath() + "].");

        String sourceDirName = source.getFileName().toString();
        Path targetDir = target.resolve(sourceDirName);
        copyDirectoryContent(source, targetDir, options);
    }

    @Deprecated
    public static void copyDirectoryContent(Path source, Path target, CopyOption... options) throws IOException {
        assertArgumentNotNull("source", source);
        assertArgumentNotNull("target", target);
        if (!FileUtils.isExistingDirectory(source)) throw new IllegalArgumentException("Directory not found: [" + source.toAbsolutePath() + "].");
        if (!FileUtils.isExistingDirectory(target)) throw new IllegalArgumentException("Directory not found: [" + target.toAbsolutePath() + "].");

        Files.walkFileTree(source, new SimpleFileVisitor<>() {

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attributes) throws IOException {
                Files.createDirectories(target.resolve(source.relativize(dir)));
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
                Files.copy(file, target.resolve(source.relativize(file)), options);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    public static List<Path> getContainingFiles(Path directory) throws IOException {
        assertArgumentNotNull("directory", directory);
        if (!FileUtils.isExistingDirectory(directory))
            throw new IllegalArgumentException("Directory not found: [" + directory.toAbsolutePath() + "].");

        List<Path> pathList = new ArrayList<>();

        Files.walkFileTree(directory, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
                pathList.add(file);
                return FileVisitResult.CONTINUE;
            }
        });

        return pathList;
    }

    public static boolean hasSubdirectories(Path path) throws IOException {
        assertArgumentNotNull("path", path);
        if (!FileUtils.isExistingDirectory(path))
            throw new IllegalArgumentException("Specified path is no existing directory.");

        return Files.list(path).anyMatch(Files::isDirectory);
    }

}

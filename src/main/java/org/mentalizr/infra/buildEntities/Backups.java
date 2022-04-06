package org.mentalizr.infra.buildEntities;

import de.arthurpicht.utils.core.collection.Lists;
import org.mentalizr.commons.paths.host.hostDir.BackupDestinationDir;
import org.mentalizr.infra.InfraRuntimeException;
import org.mentalizr.infra.utils.FileHelper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class Backups {

    public static boolean hasBackup() {
        BackupDestinationDir backupDestinationDir = BackupDestinationDir.createInstance();
        try {
            return FileHelper.hasSubdirectories(backupDestinationDir.asPath());
        } catch (IOException e) {
            throw new InfraRuntimeException(e.getMessage(), e);
        }
    }

    public static Path getLatestBackup() {
        BackupDestinationDir backupDestinationDir = BackupDestinationDir.createInstance();
        List<String> pathList = getSortedFileBackupNames(backupDestinationDir.asPath());
        if (pathList.isEmpty()) throw new IllegalStateException("No backups found.");
        return backupDestinationDir.asPath().resolve(Lists.getLastElement(pathList));
    }

    private static List<String> getSortedFileBackupNames(Path backupDestinationDir) {
        try {
            return Files.list(backupDestinationDir)
                    .filter(Files::isDirectory)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .sorted()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new InfraRuntimeException("Cannot read directory [" + backupDestinationDir.toAbsolutePath() + "]: " + e.getMessage(), e);
        }
    }

}

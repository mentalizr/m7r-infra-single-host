package org.mentalizr.infra.taskAgent;

import de.arthurpicht.taskRunner.task.TaskPreconditionException;
import org.mentalizr.cli.adminApi.AdminApiException;
import org.mentalizr.cli.adminApi.DatabaseStatus;
import org.mentalizr.cli.adminApi.Recover;
import org.mentalizr.cli.adminApi.Session;
import org.mentalizr.commons.paths.host.hostDir.BackupDefaultDir;
import org.mentalizr.infra.InfraRuntimeException;
import org.mentalizr.infra.buildEntities.Backups;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

public class RecoverTaskAgent {

    private static final Logger logger = LoggerFactory.getLogger(RecoverTaskAgent.class.getSimpleName());

    public static void assertDatabaseIsEmpty() throws TaskPreconditionException {
        try {
            Session.loginWithLocalConfiguration();
            boolean isEmpty = !DatabaseStatus.isEmpty();
            Session.logout();
            if (!isEmpty) throw new TaskPreconditionException("Database not empty.");
        } catch (AdminApiException e) {
            throw new InfraRuntimeException("Determining database status failed. " + e.getMessage(), e);
        }
    }

    public static boolean isDatabaseNotEmpty() {
        try {
            Session.loginWithLocalConfiguration();
            boolean isEmpty = !DatabaseStatus.isEmpty();
            Session.logout();
            return isEmpty;
        } catch (AdminApiException e) {
            throw new InfraRuntimeException("Determining database status failed. " + e.getMessage(), e);
        }
    }

    public static void recoverDev() {
        BackupDefaultDir backupDefaultDir = new BackupDefaultDir();
        logger.info("Recover from backup for dev.");
        try {
            Session.loginWithLocalConfiguration();
            Recover.executeForDirectory(backupDefaultDir.asPath());
            Session.logout();
        } catch (AdminApiException e) {
            throw new InfraRuntimeException("Recover from dev backup failed. " + e.getMessage(), e);
        }
    }

    public static void recoverLatest() {
        if (!Backups.hasBackup())
            throw new InfraRuntimeException("No backup found.");
        Path lastestBackupPath = Backups.getLatestBackup();
        logger.info("Recover latest backup: [" + lastestBackupPath.toAbsolutePath() + "].");
        try {
            Session.loginWithLocalConfiguration();
            Recover.executeForDirectory(lastestBackupPath);
            Session.logout();
        } catch (AdminApiException e) {
            throw new InfraRuntimeException("Recover latest backup failed. " + e.getMessage(), e);
        }
    }

}

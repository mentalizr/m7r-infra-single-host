package org.mentalizr.infra.taskAgent;

import org.mentalizr.cli.adminApi.AdminApiException;
import org.mentalizr.cli.adminApi.CliExternalApi;
import org.mentalizr.cli.adminApi.Session;
import org.mentalizr.commons.paths.host.hostDir.BackupDestinationDir;
import org.mentalizr.infra.InfraRuntimeException;

public class BackupTaskAgent {

    public static void backup() {
        BackupDestinationDir backupDestinationDir = new BackupDestinationDir();
        try {
            Session.loginWithLocalConfiguration();
            CliExternalApi.backup(backupDestinationDir.asPath());
            Session.logout();
        } catch (AdminApiException e) {
            throw new InfraRuntimeException("Backup failed. " + e.getMessage(), e);
        }
    }

}

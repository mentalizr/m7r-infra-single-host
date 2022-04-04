package org.mentalizr.infra.taskAgent;

import org.mentalizr.cli.adminApi.AdminApiException;
import org.mentalizr.cli.adminApi.DatabaseStatus;
import org.mentalizr.cli.adminApi.Recover;
import org.mentalizr.cli.adminApi.Session;
import org.mentalizr.commons.paths.host.hostDir.BackupDefaultDir;
import org.mentalizr.infra.InfraRuntimeException;

public class RecoverUserDB {

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

    public static void recoverDefault() {
        BackupDefaultDir backupDefaultDir = BackupDefaultDir.createInstance();
        try {
            Session.loginWithLocalConfiguration();
            Recover.executeForDirectory(backupDefaultDir.asPath());
            Session.logout();
        } catch (AdminApiException e) {
            throw new InfraRuntimeException("Recover from default failed. " + e.getMessage(), e);
        }
    }

}

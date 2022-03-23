package org.mentalizr.infra.buildEntities.connections;

import org.mentalizr.backend.config.Configuration;
import org.mentalizr.infra.InfraRuntimeException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;

public class ConnectionMaria {

    private static final int timeoutSeconds = 30;

    private static final String username = Configuration.getUserDbUser();
    private static final String password = Configuration.getUserDbPassword();

    private static final String database = Configuration.getUserDbName();
    private static final String rootPassword = Configuration.getUserDbRootPassword();

    public static boolean probe() {
        try {
            DriverManager.getConnection("jdbc:mariadb://localhost:3306/?user=" + username + "&password=" + password);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static Connection getConnectionToDbAsRoot() {
        try {
            return DriverManager.getConnection("jdbc:mariadb://localhost:3306/" + database + "?user=root&password=" + rootPassword);
        } catch (SQLException e) {
            throw new InfraRuntimeException("Exception when creating connection to maria as root: " + e.getMessage(), e);
        }
    }

    public static void awaitUp() {
        boolean success;
        LocalDateTime startTimestamp = LocalDateTime.now();

        while (true) {
            success = probe();
            if (success) return;

            LocalDateTime current = LocalDateTime.now();
            Duration duration = Duration.between(startTimestamp, current);
            if (duration.getSeconds() >= timeoutSeconds) throw new InfraRuntimeException("Connection probe timeout.");

            try {
                //noinspection BusyWait
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new InfraRuntimeException(e);
            }

        }
    }

}

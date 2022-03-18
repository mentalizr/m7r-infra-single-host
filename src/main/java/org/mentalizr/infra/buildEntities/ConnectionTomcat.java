package org.mentalizr.infra.buildEntities;

import org.mentalizr.infra.InfraRuntimeException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;

public class ConnectionTomcat {

    private static final int timeoutSeconds = 30;

    public static boolean probe() {
        try {
            URL url = new URL("http://localhost:8080");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) return false;
            return true;
        } catch (IOException e) {
            return false;
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

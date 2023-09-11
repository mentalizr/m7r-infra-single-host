package org.mentalizr.infra.buildEntities.connections;

import de.arthurpicht.utils.io.InputStreams;
import org.mentalizr.infra.InfraRuntimeException;
import org.mentalizr.infra.appInit.ApplicationContext;
import org.mentalizr.infra.build.Backend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class ConnectionTomcat {

    private static final Logger logger = LoggerFactory.getLogger(ConnectionTomcat.class);

    private static final int defaultTimeoutSec = 30;

    private static final int timeoutSec;

    static {
        timeoutSec = ApplicationContext.getTimeout().getTimeoutSec(defaultTimeoutSec);
    }

    public static boolean probe() {
        try {
            URL url = new URL("http://localhost:8080/m7r/service/v1");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            return responseCode == 200;
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean probeGeneric() {
        try {
            URL url = new URL("http://localhost:8080");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            return responseCode == 200;
        } catch (IOException e) {
            return false;
        }
    }

    public static void awaitUp() {
        boolean success;
        LocalDateTime startTimestamp = LocalDateTime.now();

        while (true) {
            success = probeGeneric();

            LocalDateTime current = LocalDateTime.now();
            Duration duration = Duration.between(startTimestamp, current);

            if (success) {
                logger.info("Probing tomcat http connector succeeded." +
                        " Duration=" + duration.getSeconds() + " sec.");
                return;
            }

            if (duration.getSeconds() >= timeoutSec) throw new InfraRuntimeException("Connection probe timeout.");

            try {
                logger.info("Probing tomcat http connector failed." +
                        " Duration=" + duration.getSeconds() + " sec. Timeout=" + timeoutSec + " sec");
                //noinspection BusyWait
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new InfraRuntimeException(e);
            }
        }
    }

    public static boolean hasBuildId(String buildIdString) {
        String message = "Check if running webApp has BuildId [" + buildIdString + "].";
        try {
            URL url = new URL("http://localhost:8080/m7r/service/v1");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() != 200) {
                logger.debug(message + " Failed. ResponseCode is: " + connection.getResponseCode() + ".");
                return false;
            }
            List<String> strings = InputStreams.toStrings(connection.getInputStream());
            if (strings.isEmpty()) {
                logger.debug(message + " Failed. No response.");
                return false;
            }
            boolean success = (strings.get(0).endsWith(buildIdString));
            if (success) {
                logger.debug(message + " Success.");
                return true;
            } else {
                logger.debug(message + " Failed. Received string is: [" + strings.get(0) + "]");
                return false;
            }
        } catch (IOException e) {
            logger.debug(message + " Failed. IOException: " + e.getMessage());
            return false;
        }
    }

    public static void awaitDeployment() {
        boolean isDeployed;
        String buildIdString = Backend.getBuildIdString();
        LocalDateTime startTimestamp = LocalDateTime.now();

        while (true) {
            isDeployed = hasBuildId(buildIdString);

            LocalDateTime current = LocalDateTime.now();
            Duration duration = Duration.between(startTimestamp, current);

            if (isDeployed) {
                logger.info("Deployment of webApp finished successfully." +
                        " Duration=" + duration.getSeconds() + " sec.");
                return;
            }

            if (duration.getSeconds() >= timeoutSec){
                logger.error("Deployment of webApp failed. Timeout.");
                throw new InfraRuntimeException("Deployment failed. Timeout.");
            }

            try {
                logger.info("Deployment of webApp pending." +
                        " Duration=" + duration.getSeconds() + " sec. Timeout=" + timeoutSec + " sec");
                //noinspection BusyWait
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new InfraRuntimeException(e);
            }
        }
    }

}

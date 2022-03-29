package org.mentalizr.infra.executors;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import de.arthurpicht.cli.CliCall;
import de.arthurpicht.cli.CommandExecutor;
import de.arthurpicht.cli.CommandExecutorException;
import de.arthurpicht.utils.core.strings.Strings;
import org.mentalizr.infra.ExecutionContext;
import org.mentalizr.infra.Const;
import org.mentalizr.infra.buildEntities.connections.ConnectionMaria;
import org.mentalizr.infra.buildEntities.connections.ConnectionMongo;
import org.mentalizr.infra.buildEntities.connections.ConnectionTomcat;
import org.mentalizr.infra.buildEntities.ports.PortMaria;
import org.mentalizr.infra.buildEntities.ports.PortMongo;
import org.mentalizr.infra.buildEntities.ports.PortTomcat;
import org.mentalizr.infra.docker.m7r.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class StatusExecutor implements CommandExecutor {

    private static final int minLengthString = 33;

    private static final String UP = Ansi.colorize("UP", Attribute.GREEN_TEXT());
    private static final String DOWN = Ansi.colorize("--", Attribute.RED_TEXT());
    private static final String RUNNING = Ansi.colorize("RUNNING", Attribute.GREEN_TEXT());
    private static final String STOPPED = Ansi.colorize("STOPPED", Attribute.RED_TEXT());
    private static final String OPEN = Ansi.colorize("OPEN", Attribute.GREEN_TEXT());
    private static final String CLOSED = Ansi.colorize("CLOSED", Attribute.RED_TEXT());
    private static final String SUCCESS = Ansi.colorize("SUCCESS", Attribute.GREEN_TEXT());
    private static final String SKIPPED = Ansi.colorize("SKIPPED", Attribute.YELLOW_TEXT());
    private static final String FAILED = Ansi.colorize("FAILED", Attribute.RED_TEXT());


    @Override
    public void execute(CliCall cliCall) throws CommandExecutorException {
        ExecutionContext.initialize(cliCall);

        System.out.println("mentalizr infrastructure status on " + Ansi.colorize(getHostname(), Attribute.WHITE_TEXT(), Attribute.BOLD()));

        String networkString = Strings.fillUpAfter("Network [" + Const.NETWORK + "]: ", ' ', minLengthString);
        if (M7rNetwork.exists()) {
            System.out.println(networkString + UP);
        } else {
            System.out.println(networkString + DOWN);
        }

        String volumeMongoString = Strings.fillUpAfter("MongoDB volume [" + Const.VOLUME_MONGO + "]: ", ' ', minLengthString);
        if (M7rVolumeMongo.exists()) {
            System.out.println(volumeMongoString + UP);
        } else {
            System.out.println(volumeMongoString + DOWN);
        }

        String containerMongoString = Strings.fillUpAfter("MongoDB container [" + Const.CONTAINER_MONGO + "]: ", ' ', minLengthString);
        if (M7rContainerMongo.exists()) {
            if (M7rContainerMongo.isRunning()) {
                System.out.println(containerMongoString + UP + " " + RUNNING);
            } else {
                System.out.println(containerMongoString + UP + " " + STOPPED);
            }
        } else {
            System.out.println(containerMongoString + "--");
        }

        String portMongoString = Strings.fillUpAfter("MongoDB port 27017: ", ' ', minLengthString);
        boolean mongoPortIsListening = PortMongo.isListening();
        if (mongoPortIsListening) {
            System.out.println(portMongoString + OPEN);
        } else {
            System.out.println(portMongoString + CLOSED);
        }

        String connectionMongoString = Strings.fillUpAfter("MongoDB probe client connection: ", ' ', minLengthString);
        System.out.print(connectionMongoString);
        if (mongoPortIsListening) {
            if (ConnectionMongo.connectionSuccessful()) {
                System.out.println(SUCCESS);
            } else {
                System.out.println(FAILED);
            }
        } else {
            System.out.println(SKIPPED);
        }

        String volumeMariaString = Strings.fillUpAfter("MariaDB volume [" + Const.VOLUME_MARIA + "]: ", ' ', minLengthString);
        if (M7rVolumeMaria.exists()) {
            System.out.println(volumeMariaString + UP);
        } else {
            System.out.println(volumeMariaString + DOWN);
        }

        String containerMariaString = Strings.fillUpAfter("MariaDB container [" + Const.CONTAINER_MARIA + "]: ", ' ', minLengthString);
        if (M7rContainerMaria.exists()) {
            if (M7rContainerMaria.isRunning()) {
                System.out.println(containerMariaString + UP + " " + RUNNING);
            } else {
                System.out.println(containerMariaString + UP + " " + STOPPED);
            }
        } else {
            System.out.println(containerMariaString + DOWN);
        }

        String portMariaString = Strings.fillUpAfter("MariaDB port 3306: ", ' ', minLengthString);
        boolean mariaPortIsListening = PortMaria.isListening();
        if (mariaPortIsListening) {
            System.out.println(portMariaString + OPEN);
        } else {
            System.out.println(portMariaString + CLOSED);
        }

        String connectionMariaString = Strings.fillUpAfter("MariaDB probe client connection: ", ' ', minLengthString);
        System.out.print(connectionMariaString);
        if (mariaPortIsListening) {
            if (ConnectionMaria.probe()) {
                System.out.println(SUCCESS);
            } else {
                System.out.println(FAILED);
            }
        } else {
            System.out.println(SKIPPED);
        }

        String volumeTomcatString = Strings.fillUpAfter("Tomcat volume [" + Const.VOLUME_TOMCAT + "]: ", ' ', minLengthString);
        if (M7rVolumeTomcat.exists()) {
            System.out.println(volumeTomcatString + UP);
        } else {
            System.out.println(volumeTomcatString + DOWN);
        }

        String containerTomcatString = Strings.fillUpAfter("Tomcat container [" + Const.CONTAINER_TOMCAT + "]: ", ' ', minLengthString);
        if (M7rContainerTomcat.exists()) {
            if (M7rContainerTomcat.isRunning()) {
                System.out.println(containerTomcatString + UP + " " + RUNNING);
            } else {
                System.out.println(containerTomcatString + UP + " " + STOPPED);
            }
        } else {
            System.out.println(containerTomcatString + DOWN);
        }

        String portTomcatString = Strings.fillUpAfter("Tomcat port 8080: ", ' ', minLengthString);
        boolean tomcatPortIsListening = PortTomcat.isListening();
        if (tomcatPortIsListening) {
            System.out.println(portTomcatString + OPEN);
        } else {
            System.out.println(portTomcatString + CLOSED);
        }

        String connectionTomcatString = Strings.fillUpAfter("Tomcat probe web content: ", ' ', minLengthString);
        System.out.print(connectionTomcatString);
        if (tomcatPortIsListening) {
            if (ConnectionTomcat.probe()) {
                System.out.println(SUCCESS);
            } else {
                System.out.println(FAILED);
            }
        } else {
            System.out.println(SKIPPED);
        }

        String containerNginxString = Strings.fillUpAfter("nginx container [" + Const.CONTAINER_NGINX + "]: ", ' ', minLengthString);
        if (M7rContainerNginx.exists()) {
            if (M7rContainerNginx.isRunning()) {
                System.out.println(containerNginxString + UP + " " + RUNNING);
            } else {
                System.out.println(containerNginxString + UP + " " + STOPPED);
            }
        } else {
            System.out.println(containerNginxString + DOWN);
        }

        String portNginxString = Strings.fillUpAfter("nginx port 443: ", ' ', minLengthString);
        boolean nginxPortIsListening = PortTomcat.isListening();
        if (nginxPortIsListening) {
            System.out.println(portNginxString + OPEN);
        } else {
            System.out.println(portNginxString + CLOSED);
        }

    }

    private static String getHostname() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return "UNKNOWN";
        }

    }

}

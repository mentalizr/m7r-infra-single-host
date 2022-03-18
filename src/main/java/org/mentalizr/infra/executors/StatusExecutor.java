package org.mentalizr.infra.executors;

import de.arthurpicht.cli.CliCall;
import de.arthurpicht.cli.CommandExecutor;
import de.arthurpicht.cli.CommandExecutorException;
import de.arthurpicht.utils.core.strings.Strings;
import org.mentalizr.commons.files.host.M7rInfraUserConfigFile;
import org.mentalizr.infra.ApplicationContext;
import org.mentalizr.infra.Const;
import org.mentalizr.infra.buildEntities.*;
import org.mentalizr.infra.docker.m7r.*;

public class StatusExecutor implements CommandExecutor {

    private static final int minLengthString = 33;

    @Override
    public void execute(CliCall cliCall) throws CommandExecutorException {
        ApplicationContext.initialize(cliCall);

        System.out.println("mentalizr infrastructure status");

        System.setProperty(
                "m7r.config",
                M7rInfraUserConfigFile.createInstance().toAbsolutePathString());

        String networkString = Strings.fillUpAfter("Network [" + Const.NETWORK + "]: ", ' ', minLengthString);
        if (M7rNetwork.exists()) {
            System.out.println(networkString + "UP");
        } else {
            System.out.println(networkString + "--");
        }

        String volumeMongoString = Strings.fillUpAfter("MongoDB volume [" + Const.VOLUME_MONGO + "]: ", ' ', minLengthString);
        if (M7rVolumeMongo.exists()) {
            System.out.println(volumeMongoString + "UP");
        } else {
            System.out.println(volumeMongoString + "--");
        }

        String containerMongoString = Strings.fillUpAfter("MongoDB container [" + Const.CONTAINER_MONGO + "]: ", ' ', minLengthString);
        if (M7rContainerMongo.exists()) {
            if (M7rContainerMongo.isRunning()) {
                System.out.println(containerMongoString + "UP Running");
            } else {
                System.out.println(containerMongoString + "UP Stopped");
            }
        } else {
            System.out.println(containerMongoString + "--");
        }

        String portMongoString = Strings.fillUpAfter("MongoDB port 27017: ", ' ', minLengthString);
        boolean mongoPortIsListening = PortMongo.isListening();
        if (mongoPortIsListening) {
            System.out.println(portMongoString + "OPEN");
        } else {
            System.out.println(portMongoString + "CLOSED");
        }

        String connectionMongoString = Strings.fillUpAfter("MongoDB probe client connection: ", ' ', minLengthString);
        System.out.print(connectionMongoString);
        if (mongoPortIsListening) {
            if (ConnectionMongo.connectionSuccessful()) {
                System.out.println("SUCCESS");
            } else {
                System.out.println("FAILED");
            }
        } else {
            System.out.println("SKIPPED");
        }

        String volumeMariaString = Strings.fillUpAfter("MariaDB volume [" + Const.VOLUME_MARIA + "]: ", ' ', minLengthString);
        if (M7rVolumeMaria.exists()) {
            System.out.println(volumeMariaString + "UP");
        } else {
            System.out.println(volumeMariaString + "--");
        }

        String containerMariaString = Strings.fillUpAfter("MariaDB container [" + Const.CONTAINER_MARIA + "]: ", ' ', minLengthString);
        if (M7rContainerMaria.exists()) {
            if (M7rContainerMaria.isRunning()) {
                System.out.println(containerMariaString + "UP Running");
            } else {
                System.out.println(containerMariaString + "UP Stopped");
            }
        } else {
            System.out.println(containerMariaString + "--");
        }

        String portMariaString = Strings.fillUpAfter("MariaDB port 3306: ", ' ', minLengthString);
        boolean mariaPortIsListening = PortMaria.isListening();
        if (mariaPortIsListening) {
            System.out.println(portMariaString + "OPEN");
        } else {
            System.out.println(portMariaString + "CLOSED");
        }

        String connectionMariaString = Strings.fillUpAfter("MariaDB probe client connection: ", ' ', minLengthString);
        System.out.print(connectionMariaString);
        if (mariaPortIsListening) {
            if (ConnectionMaria.probe()) {
                System.out.println("SUCCESS");
            } else {
                System.out.println("FAILED");
            }
        } else {
            System.out.println("SKIPPED");
        }

        String volumeTomcatString = Strings.fillUpAfter("Tomcat volume [" + Const.VOLUME_TOMCAT + "]: ", ' ', minLengthString);
        if (M7rVolumeTomcat.exists()) {
            System.out.println(volumeTomcatString + "UP");
        } else {
            System.out.println(volumeTomcatString + "--");
        }

        String containerTomcatString = Strings.fillUpAfter("Tomcat container [" + Const.CONTAINER_TOMCAT + "]: ", ' ', minLengthString);
        if (M7rContainerTomcat.exists()) {
            if (M7rContainerTomcat.isRunning()) {
                System.out.println(containerTomcatString + "UP Running");
            } else {
                System.out.println(containerTomcatString + "UP Stopped");
            }
        } else {
            System.out.println(containerTomcatString + "--");
        }

        String portTomcatString = Strings.fillUpAfter("Tomcat port 8080: ", ' ', minLengthString);
        boolean tomcatPortIsListening = PortTomcat.isListening();
        if (tomcatPortIsListening) {
            System.out.println(portTomcatString + "OPEN");
        } else {
            System.out.println(portTomcatString + "CLOSED");
        }

//        String connectionMariaString = Strings.fillUpAfter("MariaDB probe client connection: ", ' ', minLengthString);
//        System.out.print(connectionMariaString);
//        if (mariaPortIsListening) {
//            if (ConnectionMaria.probe()) {
//                System.out.println("SUCCESS");
//            } else {
//                System.out.println("FAILED");
//            }
//        } else {
//            System.out.println("SKIPPED");
//        }

    }

}

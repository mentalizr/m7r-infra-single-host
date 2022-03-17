package org.mentalizr.infra.executors;

import de.arthurpicht.cli.CliCall;
import de.arthurpicht.cli.CommandExecutor;
import de.arthurpicht.cli.CommandExecutorException;
import de.arthurpicht.utils.core.strings.Strings;
import org.mentalizr.commons.M7rDirs;
import org.mentalizr.infra.ApplicationContext;
import org.mentalizr.infra.Const;
import org.mentalizr.infra.InfraConfigFile;
import org.mentalizr.infra.buildEntities.ConnectionMongo;
import org.mentalizr.infra.buildEntities.PortMongo;
import org.mentalizr.infra.docker.m7r.*;

public class StatusExecutor implements CommandExecutor {

    private static final int minLengthString = 33;

    @Override
    public void execute(CliCall cliCall) throws CommandExecutorException {
        ApplicationContext.initialize(cliCall);

        System.out.println("mentalizr infrastructure status");

        InfraConfigFile infraConfigFile = new InfraConfigFile(new M7rDirs());
        String m7rInfraConfigFile = infraConfigFile.getInfraConfigFile().toAbsolutePath().toString();
        System.setProperty("m7r.config", m7rInfraConfigFile);

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

    }

}

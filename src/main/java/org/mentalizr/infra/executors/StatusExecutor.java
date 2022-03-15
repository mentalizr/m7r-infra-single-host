package org.mentalizr.infra.executors;

import de.arthurpicht.cli.CliCall;
import de.arthurpicht.cli.CommandExecutor;
import de.arthurpicht.cli.CommandExecutorException;
import de.arthurpicht.utils.core.strings.Strings;
import org.mentalizr.infra.ApplicationContext;
import org.mentalizr.infra.Const;
import org.mentalizr.infra.InfraException;
import org.mentalizr.infra.docker.Network;
import org.mentalizr.infra.docker.m7r.M7rContainerMongo;
import org.mentalizr.infra.docker.m7r.M7rNetwork;
import org.mentalizr.infra.docker.m7r.M7rVolumeMongo;
import org.mentalizr.infra.process.collect.ProcessCollect;
import org.mentalizr.infra.process.collect.ProcessCollectBuilder;
import org.mentalizr.infra.process.collect.ProcessCollectResult;
import org.mentalizr.infra.process.handleOutput.ConsoleOutputHandler;
import org.mentalizr.infra.process.handleOutput.ProcessWithOutputHandler;
import org.mentalizr.infra.process.handleOutput.ProcessWithOutputHandlerBuilder;
import org.mentalizr.infra.process.passThrough.ProcessPassThroughBuilder;
import org.mentalizr.infra.process.passThrough.ProcessPassThrough;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class StatusExecutor implements CommandExecutor {

    private static final int minLengthString = 30;

    @Override
    public void execute(CliCall cliCall) throws CommandExecutorException {
        ApplicationContext.initialize(cliCall);

        System.out.println("mentalizr infrastructure status");

        String networkString = Strings.fillUpAfter("Network [" + Const.NETWORK + "]: ", ' ', minLengthString);
        if (M7rNetwork.exists()) {
            System.out.println(networkString + "UP");
        } else {
            System.out.println(networkString + "--");
        }

        String volumeMongoString = Strings.fillUpAfter("Mongo volume [" + Const.VOLUME_MONGO + "]: ", ' ', minLengthString);
        if (M7rVolumeMongo.exists()) {
            System.out.println(volumeMongoString + "UP");
        } else {
            System.out.println(volumeMongoString + "--");
        }

        String containerMongoString = Strings.fillUpAfter("Mongo container [" + Const.CONTAINER_MONGO + "]: ", ' ', minLengthString);
        if (M7rContainerMongo.exists()) {
            if (M7rContainerMongo.isRunning()) {
                System.out.println(containerMongoString + "UP Running");
            } else {
                System.out.println(containerMongoString + "UP Stopped");
            }
        } else {
            System.out.println(containerMongoString + "--");
        }

    }

}

package org.mentalizr.infra.executors;

//import com.github.dockerjava.api.DockerClient;
import de.arthurpicht.cli.CliCall;
import de.arthurpicht.cli.CommandExecutor;
import de.arthurpicht.cli.CommandExecutorException;
import org.mentalizr.infra.Const;
import org.mentalizr.infra.InfraException;
import org.mentalizr.infra.docker.Network;
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

    @Override
    public void execute(CliCall cliCall) throws CommandExecutorException {
        System.out.println("Status called!");

        boolean existsNetwork = existsNetwork();

        System.out.print("Network [" + Const.NETWORK + "]: ");
        if (existsNetwork) {
            System.out.println("UP");
        } else {
            System.out.println("--");
        }

//        executeAsProcessWithCallback();

//        executeAsProcessCollect();

//        executeJoe();

//        DockerClient dockerClient = Client.getDockerClient();
//
//        System.out.println("has network m7r?     " + DockerNetwork.exists(dockerClient, "m7r"));
//        System.out.println("has network m7r_dev? " + DockerNetwork.exists(dockerClient, "m7r_dev"));

    }

    private boolean existsNetwork() throws CommandExecutorException {
        try {
            return Network.exists(Const.NETWORK);
        } catch (InfraException e) {
            throw new CommandExecutorException(e);
        }
    }

    private void executeAsProcessWithCallback() {
        List<String> commands = Arrays.asList("dothat");

        ProcessWithOutputHandlerBuilder processWithOutputHandlerBuilder = new ProcessWithOutputHandlerBuilder(commands);
        processWithOutputHandlerBuilder.withOutputHandler(new ConsoleOutputHandler());
        ProcessWithOutputHandler processWithOutputHandler = processWithOutputHandlerBuilder.build();

        try {
            int exitCode = processWithOutputHandler.call();
            System.out.println("Exit-Code: " + exitCode);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void executeAsProcessCollect() {
        List<String> commands = Arrays.asList("dothat");

        try {
            ProcessCollectBuilder processCollectBuilder = new ProcessCollectBuilder(commands);
            ProcessCollect processCollect = processCollectBuilder.build();
            ProcessCollectResult result = processCollect.call();

            System.out.println("Exit-Code: " + result.getExitCode());
            System.out.println("Out:");
            result.getStandardOut().forEach(System.out::println);
            System.out.println("Error:");
            result.getErrorOut().forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void executeJoe() {
        List<String> commands = Arrays.asList("joe", "~/temp.txt");
        ProcessPassThroughBuilder processPassThroughBuilder = new ProcessPassThroughBuilder(commands);
        ProcessPassThrough processPassThrough = processPassThroughBuilder.build();

        try {
            processPassThrough.call();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

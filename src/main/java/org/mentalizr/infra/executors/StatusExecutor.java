package org.mentalizr.infra.executors;

import com.github.dockerjava.api.DockerClient;
import de.arthurpicht.cli.CliCall;
import de.arthurpicht.cli.CommandExecutor;
import de.arthurpicht.cli.CommandExecutorException;
import org.mentalizr.infra.docker.Client;
import org.mentalizr.infra.docker.DockerNetwork;

public class StatusExecutor implements CommandExecutor {

    @Override
    public void execute(CliCall cliCall) throws CommandExecutorException {
        System.out.println("Status called!");

        DockerClient dockerClient = Client.getDockerClient();

        System.out.println("has network m7r?     " + DockerNetwork.exists(dockerClient, "m7r"));
        System.out.println("has network m7r_dev? " + DockerNetwork.exists(dockerClient, "m7r_dev"));

    }

}

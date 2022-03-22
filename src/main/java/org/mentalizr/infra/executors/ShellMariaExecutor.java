package org.mentalizr.infra.executors;

import de.arthurpicht.cli.CliCall;
import de.arthurpicht.cli.CommandExecutor;
import de.arthurpicht.cli.CommandExecutorException;
import org.mentalizr.infra.ExecutionContext;
import org.mentalizr.infra.Const;
import org.mentalizr.infra.DockerExecutionException;
import org.mentalizr.infra.IllegalInfraStateException;
import org.mentalizr.infra.docker.DockerExecutionContext;
import org.mentalizr.infra.docker.Shell;

public class ShellMariaExecutor implements CommandExecutor {

    @Override
    public void execute(CliCall cliCall) throws CommandExecutorException {
        ExecutionContext.initialize(cliCall);

        System.out.println("open shell on container [" + Const.CONTAINER_MARIA + "] ...");

        DockerExecutionContext dockerExecutionContext = ExecutionContext.getDockerExecutionContext();
        try {
            Shell.open(dockerExecutionContext, Const.CONTAINER_MARIA);
        } catch (DockerExecutionException | IllegalInfraStateException e) {
            throw new CommandExecutorException(e.getMessage(), e);
        }

    }

}

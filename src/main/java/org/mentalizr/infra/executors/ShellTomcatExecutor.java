package org.mentalizr.infra.executors;

import de.arthurpicht.cli.CliCall;
import de.arthurpicht.cli.CommandExecutor;
import de.arthurpicht.cli.CommandExecutorException;
import org.mentalizr.infra.Const;
import org.mentalizr.infra.DockerExecutionException;
import org.mentalizr.infra.IllegalInfraStateException;
import org.mentalizr.infra.appInit.ApplicationContext;
import org.mentalizr.infra.docker.DockerExecutionContext;
import org.mentalizr.infra.docker.Shell;

public class ShellTomcatExecutor implements CommandExecutor {

    @Override
    public void execute(CliCall cliCall) throws CommandExecutorException {
        System.out.println("open shell on container [" + Const.CONTAINER_TOMCAT + "] ...");

        DockerExecutionContext dockerExecutionContext = ApplicationContext.getDockerExecutionContext();
        try {
            Shell.openBashShell(dockerExecutionContext, Const.CONTAINER_TOMCAT);
        } catch (DockerExecutionException | IllegalInfraStateException e) {
            throw new CommandExecutorException(e.getMessage(), e);
        }

    }

}

package org.mentalizr.infra.executors;

import de.arthurpicht.cli.CliCall;
import de.arthurpicht.cli.CommandExecutor;
import de.arthurpicht.cli.CommandExecutorException;
import de.arthurpicht.taskRunner.TaskRunner;
import de.arthurpicht.taskRunner.runner.TaskRunnerResult;
import org.mentalizr.infra.ApplicationContext;
import org.mentalizr.infra.Const;
import org.mentalizr.infra.docker.m7r.M7rContainerMongo;
import org.mentalizr.infra.docker.m7r.M7rNetwork;
import org.mentalizr.infra.docker.m7r.M7rVolumeMongo;
import org.mentalizr.infra.tasks.InfraTaskRunner;

public class StartExecutor implements CommandExecutor {

    @Override
    public void execute(CliCall cliCall) throws CommandExecutorException {
        ApplicationContext.initialize(cliCall);

        System.out.println("Start mentalizr infrastructure ...");

        TaskRunner taskRunner = InfraTaskRunner.create(cliCall);
        TaskRunnerResult result = taskRunner.run("start");
    }

}

package org.mentalizr.infra.executors;

import de.arthurpicht.cli.CliCall;
import de.arthurpicht.cli.CommandExecutor;
import de.arthurpicht.cli.CommandExecutorException;
import de.arthurpicht.taskRunner.TaskRunner;
import de.arthurpicht.taskRunner.runner.TaskRunnerResult;
import org.mentalizr.infra.tasks.InfraTaskRunner;

public class StopExecutor implements CommandExecutor {

    @Override
    public void execute(CliCall cliCall) throws CommandExecutorException {
        System.out.println("Stop mentalizr infrastructure ...");

        TaskRunner taskRunner = InfraTaskRunner.create(cliCall);
        TaskRunnerResult result = taskRunner.run("stop");
    }

}

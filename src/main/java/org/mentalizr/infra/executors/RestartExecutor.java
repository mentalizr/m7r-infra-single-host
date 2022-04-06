package org.mentalizr.infra.executors;

import de.arthurpicht.cli.CliCall;
import de.arthurpicht.cli.CommandExecutor;
import de.arthurpicht.cli.CommandExecutorException;
import de.arthurpicht.taskRunner.TaskRunner;
import de.arthurpicht.taskRunner.runner.TaskRunnerResult;
import org.mentalizr.infra.ExecutionContext;
import org.mentalizr.infra.tasks.InfraTaskRunner;

public class RestartExecutor implements CommandExecutor {

    @Override
    public void execute(CliCall cliCall) throws CommandExecutorException {
        ExecutionContext.initialize(cliCall);

        System.out.println("Restart mentalizr infrastructure ...");

        TaskRunner taskRunner = InfraTaskRunner.create(cliCall);

        TaskRunnerResult result = taskRunner.run("stop");
        if (!result.isSuccess()) throw new CommandExecutorException();

        System.out.println("[wait]");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new CommandExecutorException(e);
        }

        result = taskRunner.run("start");
        if (!result.isSuccess()) throw new CommandExecutorException();
    }

}

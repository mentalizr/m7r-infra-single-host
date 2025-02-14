package org.mentalizr.infra.executors;

import de.arthurpicht.cli.CliCall;
import de.arthurpicht.cli.CommandExecutor;
import de.arthurpicht.cli.CommandExecutorException;
import de.arthurpicht.taskRunner.TaskRunner;
import de.arthurpicht.taskRunner.runner.TaskRunnerResult;
import org.mentalizr.infra.ExecutionContext;
import org.mentalizr.infra.taskAgent.RecoverSpecificOptions;
import org.mentalizr.infra.tasks.InfraTaskRunner;

public class RecoverExecutor implements CommandExecutor {

    @Override
    public void execute(CliCall cliCall) throws CommandExecutorException {
        ExecutionContext.initialize(cliCall);

        System.out.println("Recover");

        TaskRunner taskRunner = InfraTaskRunner.create(cliCall);
        TaskRunnerResult result;
        if (RecoverSpecificOptions.isRecoverDev(cliCall)) {
            result = taskRunner.run("recover-dev");
        } else {
            result = taskRunner.run("recover-latest");
        }
        if (!result.isSuccess()) throw new CommandExecutorException();
    }

}

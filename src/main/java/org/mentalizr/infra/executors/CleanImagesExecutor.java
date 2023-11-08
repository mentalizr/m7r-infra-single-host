package org.mentalizr.infra.executors;

import de.arthurpicht.cli.CliCall;
import de.arthurpicht.cli.CommandExecutor;
import de.arthurpicht.cli.CommandExecutorException;
import de.arthurpicht.taskRunner.TaskRunner;
import de.arthurpicht.taskRunner.runner.TaskRunnerResult;
import org.mentalizr.infra.ExecutionContext;
import org.mentalizr.infra.tasks.InfraTaskRunner;
import org.mentalizr.infra.tasks.cleanImages.CleanImages;

public class CleanImagesExecutor implements CommandExecutor {

    @Override
    public void execute(CliCall cliCall) throws CommandExecutorException {
        ExecutionContext.initialize(cliCall);

        System.out.println("Clean images");

        TaskRunner taskRunner = InfraTaskRunner.create(cliCall);
        TaskRunnerResult taskRunnerResult = taskRunner.run(CleanImages.NAME);

        if (!taskRunnerResult.isSuccess()) throw new CommandExecutorException();
    }

}

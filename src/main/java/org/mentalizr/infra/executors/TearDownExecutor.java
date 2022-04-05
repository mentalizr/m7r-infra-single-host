package org.mentalizr.infra.executors;

import de.arthurpicht.cli.CliCall;
import de.arthurpicht.cli.CommandExecutor;
import de.arthurpicht.cli.CommandExecutorException;
import de.arthurpicht.taskRunner.TaskRunner;
import de.arthurpicht.taskRunner.runner.TaskRunnerResult;
import org.mentalizr.infra.ExecutionContext;
import org.mentalizr.infra.tasks.InfraTaskRunner;
import org.mentalizr.infra.utils.ListUtils;

import java.util.List;

public class TearDownExecutor implements CommandExecutor {

    @Override
    public void execute(CliCall cliCall) throws CommandExecutorException {
        ExecutionContext.initialize(cliCall);

        System.out.println("tear down mentalizr infrastructure.");

        TaskRunner taskRunner = InfraTaskRunner.create(cliCall);
        List<TaskRunnerResult> taskRunnerResults = taskRunner.run("stop", "remove");

        if (!ListUtils.getLastElement(taskRunnerResults).isSuccess())
            throw new CommandExecutorException();
    }

}

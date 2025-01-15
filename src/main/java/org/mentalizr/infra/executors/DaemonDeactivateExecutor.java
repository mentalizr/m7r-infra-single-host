package org.mentalizr.infra.executors;

import de.arthurpicht.cli.CliCall;
import de.arthurpicht.cli.CommandExecutor;
import de.arthurpicht.cli.CommandExecutorException;
import de.arthurpicht.taskRunner.TaskRunner;
import de.arthurpicht.taskRunner.runner.TaskRunnerResult;
import org.mentalizr.infra.ExecutionContext;
import org.mentalizr.infra.tasks.InfraTaskRunner;
import org.mentalizr.infra.tasks.daemon.DeactivateDaemon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("StringConcatenationArgumentToLogCall")
public class DaemonDeactivateExecutor implements CommandExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(DaemonDeactivateExecutor.class);

    @Override
    public void execute(CliCall cliCall) throws CommandExecutorException {
        ExecutionContext.initialize(cliCall);
        LOGGER.info(DaemonActivateExecutor.class.getSimpleName() + " invoked.");
        TaskRunner taskRunner = InfraTaskRunner.create(cliCall);
        TaskRunnerResult result = taskRunner.run(DeactivateDaemon.NAME);
        if (!result.isSuccess()) throw new CommandExecutorException();
    }

}

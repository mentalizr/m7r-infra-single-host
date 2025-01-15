package org.mentalizr.infra.executors;

import de.arthurpicht.cli.CliCall;
import de.arthurpicht.cli.CommandExecutor;
import de.arthurpicht.cli.CommandExecutorException;
import de.arthurpicht.taskRunner.TaskRunner;
import de.arthurpicht.taskRunner.runner.TaskRunnerResult;
import org.mentalizr.infra.ExecutionContext;
import org.mentalizr.infra.tasks.InfraTaskRunner;
import org.mentalizr.infra.tasks.daemon.ActivateDaemon;
import org.mentalizr.infra.tasks.daemon.RestartDaemon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("StringConcatenationArgumentToLogCall")
public class DaemonActivateExecutor implements CommandExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(DaemonActivateExecutor.class);

    @Override
    public void execute(CliCall cliCall) throws CommandExecutorException {
        ExecutionContext.initialize(cliCall);
        LOGGER.info(DaemonActivateExecutor.class.getSimpleName() + " invoked.");
        TaskRunner taskRunner = InfraTaskRunner.create(cliCall);
        TaskRunnerResult result = taskRunner.run(ActivateDaemon.NAME);
        if (!result.isSuccess()) throw new CommandExecutorException();
    }

}

package org.mentalizr.infra.executors;

import de.arthurpicht.cli.CliCall;
import de.arthurpicht.cli.CommandExecutor;
import de.arthurpicht.cli.CommandExecutorException;
import de.arthurpicht.taskRunner.TaskRunner;
import de.arthurpicht.taskRunner.runner.TaskRunnerResult;
import org.mentalizr.infra.ExecutionContext;
import org.mentalizr.infra.tasks.InfraTaskRunner;

import static org.mentalizr.infra.executors.notification.InfraEmailNotification.notifyOnFailure;
import static org.mentalizr.infra.executors.notification.InfraEmailNotification.notifyOnSuccess;

public class RestartExecutor implements CommandExecutor {

    @Override
    public void execute(CliCall cliCall) throws CommandExecutorException {
        ExecutionContext.initialize(cliCall);

        System.out.println("Restart mentalizr infrastructure ...");

        TaskRunner taskRunner = InfraTaskRunner.create(cliCall);

        TaskRunnerResult result = taskRunner.run("stop");
        RestartNotificationMessage notificationMessage = new RestartNotificationMessage();
        if (!result.isSuccess()) {
            notifyOnFailure(notificationMessage);
            throw new CommandExecutorException();
        }

        System.out.println("[wait]");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            notifyOnFailure(notificationMessage);
            throw new CommandExecutorException(e);
        }

        result = taskRunner.run("start");
        if (result.isSuccess()) {
            notifyOnSuccess(notificationMessage);
        } else {
            notifyOnFailure(notificationMessage);
            throw new CommandExecutorException();
        }
    }

}

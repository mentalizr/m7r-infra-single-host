package org.mentalizr.infra.executors;

import de.arthurpicht.cli.CliCall;
import de.arthurpicht.cli.CommandExecutor;
import de.arthurpicht.cli.CommandExecutorException;
import de.arthurpicht.taskRunner.TaskRunner;
import de.arthurpicht.taskRunner.runner.TaskRunnerResult;
import org.mentalizr.infra.tasks.InfraTaskRunner;

import static org.mentalizr.infra.executors.notification.InfraEmailNotification.notifyOnFailure;
import static org.mentalizr.infra.executors.notification.InfraEmailNotification.notifyOnSuccess;

public class StartExecutor implements CommandExecutor {

    @Override
    public void execute(CliCall cliCall) throws CommandExecutorException {
        System.out.println("Start mentalizr infrastructure ...");

        TaskRunner taskRunner = InfraTaskRunner.create(cliCall);
        TaskRunnerResult result = taskRunner.run("start");

        StartNotificationMessage notificationMessage = new StartNotificationMessage();
        if (result.isSuccess()) {
            notifyOnSuccess(notificationMessage);
        } else {
            notifyOnFailure(notificationMessage);
            throw new CommandExecutorException();
        }
    }

}

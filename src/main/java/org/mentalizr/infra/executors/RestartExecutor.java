package org.mentalizr.infra.executors;

import de.arthurpicht.cli.CliCall;
import de.arthurpicht.cli.CommandExecutor;
import de.arthurpicht.cli.CommandExecutorException;

import static org.mentalizr.infra.executors.notification.InfraEmailNotification.notifyOnFailure;

public class RestartExecutor implements CommandExecutor {

    @Override
    public void execute(CliCall cliCall) throws CommandExecutorException {

        System.out.println("Restart mentalizr infrastructure ...");

        RestartNotificationMessage notificationMessage = new RestartNotificationMessage();

        try {
            Restart.perform();
        } catch (Restart.RestartException e) {
            notifyOnFailure(notificationMessage);
            throw new CommandExecutorException("Restart failed", e);
        }
    }

}

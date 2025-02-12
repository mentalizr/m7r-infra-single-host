package org.mentalizr.infra.executors;

import de.arthurpicht.cli.CliCall;
import de.arthurpicht.cli.CommandExecutor;
import de.arthurpicht.cli.CommandExecutorException;
import de.arthurpicht.taskRunner.TaskRunner;
import de.arthurpicht.taskRunner.runner.TaskRunnerResult;
import org.mentalizer.mailer.notifier.MailNotifier;
import org.mentalizr.infra.ExecutionContext;
import org.mentalizr.infra.executors.notification.InfraMailNotifierCallback;
import org.mentalizr.infra.tasks.InfraTaskRunner;
import org.mentalizr.infra.utils.LocalHost;

public class StartExecutor implements CommandExecutor {

    @Override
    public void execute(CliCall cliCall) throws CommandExecutorException {
        ExecutionContext.initialize(cliCall);

        System.out.println("Start mentalizr infrastructure ...");

        TaskRunner taskRunner = InfraTaskRunner.create(cliCall);
        TaskRunnerResult result = taskRunner.run("start");

        if (result.isSuccess()) {
            sendMailNotificationSuccess();
        } else {
            sendMailNotificationFailure();
            throw new CommandExecutorException();
        }
    }

    private static void sendMailNotificationSuccess() {
        if (ExecutionContext.isNotify()) {
            StartNotificationMessage message = new StartNotificationMessage();
            MailNotifier.sendNotification(
                    message.getSubjectOnSuccess(),
                    message.getTextOnSuccess(),
                    new InfraMailNotifierCallback());
        }
    }

    private static void sendMailNotificationFailure() {
        if (ExecutionContext.isNotify()) {
            StartNotificationMessage message = new StartNotificationMessage();
            MailNotifier.sendNotification(
                    message.getSubjectOnFailure(),
                    message.getTextOnFailure(),
                    new InfraMailNotifierCallback());
        }
    }

}

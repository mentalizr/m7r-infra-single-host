package org.mentalizr.infra.executors.notification;

import org.mentalizer.mailer.notifier.MailNotifier;
import org.mentalizr.infra.ExecutionContext;
import org.mentalizr.infra.utils.LocalHost;

public class InfraEmailNotification {

    public static void sendOnSuccess(ExecutionContext executionContext, NotificationMessage notificationMessage) {
        if (ExecutionContext.isNotify()) {
            MailNotifier.sendNotification(
                    notificationMessage.getSubjectOnSuccess(),
                    notificationMessage.getTextOnSuccess(),
                    new InfraMailNotifierCallback());
        }
    }



}

package org.mentalizr.infra.executors.notification;

import org.mentalizer.mailer.notifier.MailNotifier;
import org.mentalizr.infra.ExecutionContext;

public class InfraEmailNotification {

    public static void notifyOnSuccess(NotificationMessage notificationMessage) {
        if (ExecutionContext.isNotify()) {
            MailNotifier.sendNotification(
                    notificationMessage.getSubjectOnSuccess(),
                    notificationMessage.getTextOnSuccess(),
                    new InfraMailNotifierCallback());
        }
    }

    public static void notifyOnFailure(NotificationMessage notificationMessage) {
        if (ExecutionContext.isNotify()) {
            MailNotifier.sendNotification(
                    notificationMessage.getSubjectOnFailure(),
                    notificationMessage.getTextOnFailure(),
                    new InfraMailNotifierCallback());
        }
    }

}

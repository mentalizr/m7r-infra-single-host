package org.mentalizr.infra.executors.notification;

import org.mentalizer.mailer.notifier.MailNotifier;
import org.mentalizr.infra.appInit.ApplicationContext;

public class InfraEmailNotification {

    public static void notifyOnSuccess(NotificationMessage notificationMessage) {
        if (ApplicationContext.isNotify()) {
            MailNotifier.sendNotification(
                    notificationMessage.getSubjectOnSuccess(),
                    notificationMessage.getTextOnSuccess(),
                    new InfraMailNotifierCallback());
        }
    }

    public static void notifyOnFailure(NotificationMessage notificationMessage) {
        if (ApplicationContext.isNotify()) {
            MailNotifier.sendNotification(
                    notificationMessage.getSubjectOnFailure(),
                    notificationMessage.getTextOnFailure(),
                    new InfraMailNotifierCallback());
        }
    }

}

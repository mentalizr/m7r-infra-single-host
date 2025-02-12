package org.mentalizr.infra.executors.notification;

import org.mentalizer.mailer.notifier.MailNotifierCallback;
import org.mentalizer.mailer.notifier.MailNotifierResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InfraMailNotifierCallback implements MailNotifierCallback {

    private static final Logger logger = LoggerFactory.getLogger(InfraMailNotifierCallback.class);

    @Override
    public void onSuccess(MailNotifierResult result) {
        logger.info("Sent mail notification: [{}].", result.getMailNotification().getSubject());
    }

    @Override
    public void onFailure(MailNotifierResult result) {
        logger.error("Failed to send mail notification: [{}].",
                result.getMailNotification().getSubject(),
                result.getThrowable());
    }

}

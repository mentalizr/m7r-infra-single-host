package org.mentalizr.infra.executors;

import org.mentalizr.infra.executors.notification.NotificationMessage;
import org.mentalizr.infra.utils.LocalHost;

public class RestartNotificationMessage implements NotificationMessage {

    @Override
    public String getSubjectOnSuccess() {
        String hostname = LocalHost.getHostname();
        return "[" + hostname + "] mentalizr restarted";
    }

    @Override
    public String getTextOnSuccess() {
        String hostname = LocalHost.getHostname();
        return "mentalizr restarted on [" + hostname + "]." +
                "\n\nThis message was automatically generated.";
    }

    @Override
    public String getSubjectOnFailure() {
        String hostname = LocalHost.getHostname();
        return "[" + hostname + "] mentalizr FAILED to restart.";
    }

    @Override
    public String getTextOnFailure() {
        String hostname = LocalHost.getHostname();
        return "FAILED to restart mentalizr on [" + hostname + "]." +
                "\n\nThis message was automatically generated.";
    }

}

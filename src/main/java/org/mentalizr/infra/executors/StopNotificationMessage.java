package org.mentalizr.infra.executors;

import org.mentalizr.infra.executors.notification.NotificationMessage;
import org.mentalizr.infra.utils.LocalHost;

public class StopNotificationMessage implements NotificationMessage {

    @Override
    public String getSubjectOnSuccess() {
        String hostname = LocalHost.getHostname();
        return "[" + hostname + "] mentalizr stopped";
    }

    @Override
    public String getTextOnSuccess() {
        String hostname = LocalHost.getHostname();
        return "mentalizr stopped on [" + hostname + "]." +
                "\n\nThis message was automatically generated.";
    }

    @Override
    public String getSubjectOnFailure() {
        String hostname = LocalHost.getHostname();
        return "[" + hostname + "] mentalizr FAILED to stop";
    }

    @Override
    public String getTextOnFailure() {
        String hostname = LocalHost.getHostname();
        return "FAILED to stop mentalizr on [" + hostname + "]." +
                "\n\nThis message was automatically generated.";
    }

}

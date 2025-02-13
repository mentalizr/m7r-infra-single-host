package org.mentalizr.infra.executors;

import org.mentalizr.infra.executors.notification.NotificationMessage;
import org.mentalizr.infra.utils.LocalHost;

public class StartNotificationMessage implements NotificationMessage {

    @Override
    public String getSubjectOnSuccess() {
        String hostname = LocalHost.getHostname();
        return "[" + hostname + "] mentalizr started";
    }

    @Override
    public String getTextOnSuccess() {
        String hostname = LocalHost.getHostname();
        return "Successfully started mentalizr on [" + hostname + "]." +
                "\n\nThis message was automatically generated.";
    }

    @Override
    public String getSubjectOnFailure() {
        String hostname = LocalHost.getHostname();
        return "[" + hostname + "] mentalizr FAILED to start.";
    }

    @Override
    public String getTextOnFailure() {
        String hostname = LocalHost.getHostname();
        return "FAILED to start mentalizr on [" + hostname + "]." +
                "\n\nThis message was automatically generated.";
    }

}

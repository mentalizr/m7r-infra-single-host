package org.mentalizr.infra.executors.notification;

public interface NotificationMessage {

    public String getSubjectOnSuccess();

    public String getTextOnSuccess();

    public String getSubjectOnFailure();

    public String getTextOnFailure();

}

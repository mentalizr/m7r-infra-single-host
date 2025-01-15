package org.mentalizr.infra.tasks.daemon;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.daemon.Daemon;

public class ActivateDaemon {

    public static final String NAME = "activate-daemon";

    public static Task create() {
        return new TaskBuilder()
                .withName(NAME)
                .withDescription("activate daemon")
                .asTarget()
                .isUpToDate(Daemon::isActive)
                .execute(Daemon::activate)
                .build();
    }

}

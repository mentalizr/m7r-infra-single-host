package org.mentalizr.infra.tasks.daemon;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.daemon.Daemon;

public class DeactivateDaemon {

    public static final String NAME = "deactivate-daemon";

    public static Task create() {
        return new TaskBuilder()
                .withName(NAME)
                .withDescription("deactivate daemon")
                .asTarget()
                .isUpToDate(Daemon::isNotActive)
                .execute(Daemon::deactivate)
                .build();
    }

}

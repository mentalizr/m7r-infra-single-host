package org.mentalizr.infra.tasks.daemon;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.daemon.Daemon;

public class StopDaemon {

    public static final String NAME = "stop-daemon";

    public static Task create() {
        return new TaskBuilder()
                .withName(NAME)
                .withDescription("stop daemon")
                .asTarget()
                .isUpToDate(Daemon::isStopped)
                .execute(Daemon::stop)
                .build();
    }

}

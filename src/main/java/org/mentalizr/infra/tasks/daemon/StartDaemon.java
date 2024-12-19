package org.mentalizr.infra.tasks.daemon;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.daemon.Daemon;

public class StartDaemon {

    public static final String NAME = "start-daemon";

    public static Task create() {
        return new TaskBuilder()
                .withName(NAME)
                .withDescription("start daemon")
                .asTarget()
                .isUpToDate(Daemon::isRunning)
                .execute(Daemon::start)
                .build();
    }

}

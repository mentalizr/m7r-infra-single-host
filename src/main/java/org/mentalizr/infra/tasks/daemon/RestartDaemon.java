package org.mentalizr.infra.tasks.daemon;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.daemon.Daemon;

public class RestartDaemon {

    public static final String NAME = "restart-daemon";

    public static Task create() {
        return new TaskBuilder()
                .withName(NAME)
                .withDescription("restart daemon")
                .asTarget()
                .withDependencies("stop-daemon")
                .execute(Daemon::start)
                .build();
    }

}

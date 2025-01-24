package org.mentalizr.infra.tasks.scheduler;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.scheduler.Scheduler;

public class RestartScheduler {

    public static final String NAME = "restart-scheduler";

    public static Task create() {
        return new TaskBuilder()
                .withName(NAME)
                .withDescription("restart scheduler")
                .asTarget()
                .withDependencies("stop-scheduler")
                .execute(Scheduler::start)
                .build();
    }

}

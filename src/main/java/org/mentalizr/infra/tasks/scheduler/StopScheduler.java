package org.mentalizr.infra.tasks.scheduler;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.scheduler.Scheduler;

public class StopScheduler {

    public static final String NAME = "stop-scheduler";

    public static Task create() {
        return new TaskBuilder()
                .withName(NAME)
                .withDescription("stop scheduler")
                .asTarget()
                .isUpToDate(Scheduler::isStopped)
                .execute(Scheduler::stop)
                .build();
    }

}

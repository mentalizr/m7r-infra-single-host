package org.mentalizr.infra.tasks.scheduler;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.scheduler.Scheduler;

public class StartScheduler {

    public static final String NAME = "start-scheduler";

    public static Task create() {
        return new TaskBuilder()
                .withName(NAME)
                .withDescription("start scheduler")
                .asTarget()
                .isUpToDate(Scheduler::isRunning)
                .execute(Scheduler::start)
                .build();
    }

}

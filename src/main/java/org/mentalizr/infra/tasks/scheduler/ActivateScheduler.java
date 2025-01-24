package org.mentalizr.infra.tasks.scheduler;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.scheduler.Scheduler;

public class ActivateScheduler {

    public static final String NAME = "activate-scheduler";

    public static Task create() {
        return new TaskBuilder()
                .withName(NAME)
                .withDescription("activate scheduler")
                .asTarget()
                .isUpToDate(Scheduler::isActive)
                .execute(Scheduler::activate)
                .build();
    }

}

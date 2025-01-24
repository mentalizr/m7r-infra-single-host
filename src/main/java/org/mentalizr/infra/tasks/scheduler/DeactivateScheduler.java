package org.mentalizr.infra.tasks.scheduler;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.scheduler.Scheduler;

public class DeactivateScheduler {

    public static final String NAME = "deactivate-scheduler";

    public static Task create() {
        return new TaskBuilder()
                .withName(NAME)
                .withDescription("deactivate scheduler")
                .asTarget()
                .isUpToDate(Scheduler::isNotActive)
                .execute(Scheduler::deactivate)
                .build();
    }

}

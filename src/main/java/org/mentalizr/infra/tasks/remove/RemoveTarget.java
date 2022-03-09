package org.mentalizr.infra.tasks.remove;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;

public class RemoveTarget {

    public static Task create() {
        return new TaskBuilder()
                .isTarget()
                .name("remove")
                .description("remove docker infrastructure")
                .dependencies("remove-network")
                .execute(()-> {})
                .build();
    }

}

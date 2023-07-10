package org.mentalizr.infra.tasks.remove;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;

public class RemoveTarget {

    public static Task create() {
        return new TaskBuilder()
                .asTarget()
                .withName("remove")
                .withDescription("remove docker infrastructure")
                .withDependencies("remove-network")
                .execute(()-> {})
                .build();
    }

}

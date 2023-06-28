package org.mentalizr.infra.tasks.remove.maria;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;

public class RemoveMaria {

    public static Task create() {
        return new TaskBuilder()
                .withName("remove-maria")
                .withDescription("remove mariaDB")
                .withDependencies("remove-volume-maria")
                .execute(() -> {})
                .build();
    }

}

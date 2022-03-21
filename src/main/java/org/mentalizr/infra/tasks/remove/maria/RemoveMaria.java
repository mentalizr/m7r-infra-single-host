package org.mentalizr.infra.tasks.remove.maria;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;

public class RemoveMaria {

    public static Task create() {
        return new TaskBuilder()
                .name("remove-maria")
                .description("remove mariaDB")
                .dependencies("remove-volume-maria")
                .execute(() -> {})
                .build();
    }

}

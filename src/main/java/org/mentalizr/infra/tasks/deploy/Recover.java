package org.mentalizr.infra.tasks.deploy;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;

public class Recover {

    public static Task create() {
        return new TaskBuilder()
                .name("recover")
                .description("recover")
                .dependencies("init-db-admin")
                .execute(() -> {})
                .build();
    }

}

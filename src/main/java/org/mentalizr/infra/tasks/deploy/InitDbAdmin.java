package org.mentalizr.infra.tasks.deploy;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;

public class InitDbAdmin {

    public static Task create() {
        return new TaskBuilder()
                .name("init-db-admin")
                .description("init database admin user")
                .dependencies("init-db-schema")
                .execute(() -> {})
                .build();
    }

}

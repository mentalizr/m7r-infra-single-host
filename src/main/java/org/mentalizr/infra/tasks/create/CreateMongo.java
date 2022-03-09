package org.mentalizr.infra.tasks.create;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;

public class CreateMongo {

    public static Task create() {
        return new TaskBuilder()
                .isTarget()
                .name("create-mongo")
                .description("create mongo")
                .dependencies("initialize-container-mongo")
                .execute(()-> {})
                .build();
    }

}

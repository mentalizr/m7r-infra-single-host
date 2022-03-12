package org.mentalizr.infra.tasks.create;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rContainerMongo;

public class InitializeContainerMaria {

    public static Task create() {
        return new TaskBuilder()
                .name("initialize-container-maria")
                .description("initialize container maria")
                .dependencies("create-container-maria")
                .inputChanged(() -> false)
//                .outputExists(M7rContainerMongo::exists)
                .execute(() -> {}) // TODO
                .build();
    }

}

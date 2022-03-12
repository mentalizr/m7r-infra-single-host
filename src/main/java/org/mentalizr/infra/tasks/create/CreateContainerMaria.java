package org.mentalizr.infra.tasks.create;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rContainerMongo;

public class CreateContainerMaria {

    public static Task create() {
        return new TaskBuilder()
                .name("create-container-maria")
                .description("create container maria")
                .dependencies("create-volume-maria")
                .inputChanged(() -> false)
//                .outputExists(M7rContainerMongo::exists)
//                .execute(M7rContainerMongo::create)       // TODO
                .execute(() -> {})
                .build();
    }

}

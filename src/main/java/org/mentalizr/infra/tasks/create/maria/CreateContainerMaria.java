package org.mentalizr.infra.tasks.create.maria;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rContainerMaria;
import org.mentalizr.infra.docker.m7r.M7rContainerMongo;

public class CreateContainerMaria {

    public static Task create() {
        return new TaskBuilder()
                .name("create-container-maria")
                .description("create container maria")
                .dependencies("create-volume-maria")
                .inputChanged(() -> false)
                .outputExists(M7rContainerMaria::exists)
                .execute(M7rContainerMaria::create)
                .build();
    }

}

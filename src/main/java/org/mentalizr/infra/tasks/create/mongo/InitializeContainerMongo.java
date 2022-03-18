package org.mentalizr.infra.tasks.create.mongo;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rContainerMongo;

public class InitializeContainerMongo {

    public static Task create() {
        return new TaskBuilder()
                .name("initialize-container-mongo")
                .description("initialize container mongo")
                .dependencies("create-container-mongo")
                .execute(M7rContainerMongo::initialize)
                .build();
    }

}

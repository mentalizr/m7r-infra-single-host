package org.mentalizr.infra.tasks.create.maria;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rContainerMongo;

public class InitializeContainerMaria {

    public static Task create() {
        return new TaskBuilder()
                .withName("initialize-container-maria")
                .withDescription("initialize container maria")
                .withDependencies("create-container-maria")
                .execute(() -> {})
                .build();
    }

}

package org.mentalizr.infra.tasks.start.maria;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rContainerMaria;
import org.mentalizr.infra.docker.m7r.M7rContainerMongo;

public class StartMaria {

    public static Task create() {
        return new TaskBuilder()
                .withName("start-maria")
                .withDescription("start maria")
                .withDependencies("await-up-maria")
                .execute(() -> {})
                .build();
    }

}

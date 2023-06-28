package org.mentalizr.infra.tasks.start.mongo;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rContainerMongo;

public class StartMongo {

    public static Task create() {
        return new TaskBuilder()
                .withName("start-mongo")
                .withDescription("start mongo")
                .withDependencies("await-up-mongo")
                .execute(() -> {})
                .build();
    }

}

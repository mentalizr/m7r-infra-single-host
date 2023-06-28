package org.mentalizr.infra.tasks.create.mongo;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;

public class CreateMongo {

    public static Task create() {
        return new TaskBuilder()
                .asTarget()
                .withName("create-mongo")
                .withDescription("create mongo")
                .withDependencies("initialize-container-mongo")
                .execute(()-> {})
                .build();
    }

}

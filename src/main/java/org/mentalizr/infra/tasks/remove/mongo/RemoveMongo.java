package org.mentalizr.infra.tasks.remove.mongo;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.Const;
import org.mentalizr.infra.docker.m7r.M7rNetwork;

public class RemoveMongo {

    public static Task create() {
        return new TaskBuilder()
                .withName("remove-mongo")
                .withDescription("remove mongoDB")
                .withDependencies("remove-volume-mongo")
                .execute(() -> {})
                .build();
    }

}

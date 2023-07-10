package org.mentalizr.infra.tasks.createImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rImageMongo;

public class CreateImageMongo {

    public static Task create() {
        return new TaskBuilder()
                .withName("create-image-mongo")
                .execute(M7rImageMongo::pull)
                .build();
    }

}

package org.mentalizr.infra.tasks.createImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rImageMongo;

public class CreateImageMongo {

    public static final String NAME = "create-image-mongo";

    public static Task create() {
        return new TaskBuilder()
                .withName(NAME)
                .execute(M7rImageMongo::pull)
                .build();
    }

}

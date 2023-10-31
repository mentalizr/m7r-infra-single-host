package org.mentalizr.infra.tasks.createImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rImageMaria;

public class CreateImageMaria {

    public static final String NAME = "create-image-maria";

    public static Task create() {
        return new TaskBuilder()
                .withName(NAME)
                .execute(M7rImageMaria::pull)
                .build();
    }

}

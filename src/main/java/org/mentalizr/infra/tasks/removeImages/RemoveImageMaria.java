package org.mentalizr.infra.tasks.removeImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rImageMaria;

public class RemoveImageMaria {

    public static final String NAME = "remove-image-maria";

    public static Task create() {
        return new TaskBuilder()
                .withName(NAME)
                .isUpToDate(() -> !M7rImageMaria.exists())
                .execute(M7rImageMaria::remove)
                .build();
    }

}

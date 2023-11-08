package org.mentalizr.infra.tasks.cleanImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rImageMaria;

public class CleanImagesMaria {

    public static final String NAME = "clean-images-maria";

    public static Task create() {
        return new TaskBuilder()
                .withName(NAME)
                .isUpToDate(() -> !M7rImageMaria.existsAny())
                .execute(M7rImageMaria::clean)
                .build();
    }

}

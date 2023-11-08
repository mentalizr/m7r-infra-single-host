package org.mentalizr.infra.tasks.cleanImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rImageNginx;

public class CleanImagesNginx {

    public static final String NAME = "clean-images-nginx";

    public static Task create() {
        return new TaskBuilder()
                .withName(NAME)
                .isUpToDate(() -> !M7rImageNginx.existsAny())
                .execute(M7rImageNginx::clean)
                .build();
    }

}

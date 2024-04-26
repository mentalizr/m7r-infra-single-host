package org.mentalizr.infra.tasks.cleanImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.BaseImageNginx;

public class RemoveBaseImageNginx {

    // Debian Base Image is only pulled when executing task create-images.

    public static final String NAME = "remove-base-image-nginx";

    public static Task create() {
        return new TaskBuilder()
                .withName(NAME)
                .isUpToDate(() -> !BaseImageNginx.exists())
                .withDependencies(CleanImagesNginx.NAME)
                .execute(BaseImageNginx::remove)
                .build();
    }

}

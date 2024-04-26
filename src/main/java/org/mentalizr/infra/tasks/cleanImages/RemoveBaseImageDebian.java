package org.mentalizr.infra.tasks.cleanImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.BaseImageDebian;

public class RemoveBaseImageDebian {

    // Debian Base Image is only pulled when executing task create-images.

    public static final String NAME = "remove-base-image-debian";

    public static Task create() {
        return new TaskBuilder()
                .withName(NAME)
                .withDependencies(CleanImagesDebian.NAME)
                .isUpToDate(() -> !BaseImageDebian.exists())
                .execute(BaseImageDebian::remove)
                .build();
    }

}
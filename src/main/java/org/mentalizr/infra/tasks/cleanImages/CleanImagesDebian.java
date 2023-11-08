package org.mentalizr.infra.tasks.cleanImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rImageDebian;

public class CleanImagesDebian {

    public static final String NAME = "clean-images-debian";

    public static Task create() {
        return new TaskBuilder()
                .withName(NAME)
                .isUpToDate(() -> !M7rImageDebian.existsAny())
                .execute(M7rImageDebian::clean)
                .build();
    }

}

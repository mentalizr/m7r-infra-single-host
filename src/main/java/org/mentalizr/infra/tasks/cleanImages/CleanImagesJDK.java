package org.mentalizr.infra.tasks.cleanImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rImageJDK;

public class CleanImagesJDK {

    public static final String NAME = "clean-images-jdk";

    public static Task create() {
        return new TaskBuilder()
                .withName(NAME)
                .isUpToDate(() -> !M7rImageJDK.existsAny())
                .execute(M7rImageJDK::clean)
                .build();
    }

}

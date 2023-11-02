package org.mentalizr.infra.tasks.removeImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rImageJDK;

public class RemoveImageJDKAll {

    public static final String NAME = "remove-image-jdk-all";

    public static Task create() {
        return new TaskBuilder()
                .withName(NAME)
                .isUpToDate(() -> !M7rImageJDK.existsAny())
                .execute(M7rImageJDK::removeAll)
                .build();
    }

}

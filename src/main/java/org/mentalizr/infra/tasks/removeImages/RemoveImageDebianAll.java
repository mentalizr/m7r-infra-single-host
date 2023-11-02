package org.mentalizr.infra.tasks.removeImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rImageDebian;

public class RemoveImageDebianAll {

    public static final String NAME = "remove-image-debian-all";

    public static Task create() {
        return new TaskBuilder()
                .withName(NAME)
                .isUpToDate(() -> !M7rImageDebian.existsAny())
                .execute(M7rImageDebian::removeAll)
                .build();
    }

}

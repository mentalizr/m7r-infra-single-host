package org.mentalizr.infra.tasks.removeImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rImageDebian;
import org.mentalizr.infra.docker.m7r.M7rImageTomcat;

public class RemoveImageDebian {

    public static final String NAME = "remove-image-debian";

    public static Task create() {
        return new TaskBuilder()
                .withName(NAME)
                .isUpToDate(() -> !M7rImageDebian.exists())
                .execute(M7rImageDebian::remove)
                .build();
    }

}

package org.mentalizr.infra.tasks.removeImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rImageMaria;

public class RemoveImageMariaAll {

    public static final String NAME = "remove-image-maria-all";

    public static Task create() {
        return new TaskBuilder()
                .withName(NAME)
                .isUpToDate(() -> !M7rImageMaria.existsAny())
                .execute(M7rImageMaria::removeAll)
                .build();
    }

}

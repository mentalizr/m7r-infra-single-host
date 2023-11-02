package org.mentalizr.infra.tasks.removeImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rImageNginx;

public class RemoveImageNginxAll {

    public static final String NAME = "remove-image-nginx-all";

    public static Task create() {
        return new TaskBuilder()
                .withName(NAME)
                .isUpToDate(() -> !M7rImageNginx.existsAny())
                .execute(M7rImageNginx::removeAll)
                .build();
    }

}

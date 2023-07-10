package org.mentalizr.infra.tasks.removeImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rImageNginx;

public class RemoveImageNginx {

    public static Task create() {
        return new TaskBuilder()
                .withName("remove-image-nginx")
                .withDependencies("create-backup-tag-nginx")
                .isUpToDate(() -> !M7rImageNginx.exists())
                .execute(M7rImageNginx::remove)
                .build();
    }

}

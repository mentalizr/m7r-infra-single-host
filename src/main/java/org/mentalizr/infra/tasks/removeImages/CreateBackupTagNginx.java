package org.mentalizr.infra.tasks.removeImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rImageNginx;

public class CreateBackupTagNginx {

    public static final String NAME = "create-backup-tag-nginx";

    public static Task create() {
        return new TaskBuilder()
                .withName(NAME)
                .skip(() -> !M7rImageNginx.exists())
                .execute(M7rImageNginx::createBackupTag)
                .build();
    }

}

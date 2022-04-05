package org.mentalizr.infra.tasks.removeImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rImageMaria;

public class CreateBackupTagMaria {

    public static Task create() {
        return new TaskBuilder()
                .name("create-backup-tag-maria")
                .skip(() -> !M7rImageMaria.exists())
                .execute(M7rImageMaria::createBackupTag)
                .build();
    }

}

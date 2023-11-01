package org.mentalizr.infra.tasks.removeImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rImageMaria;

public class CreateBackupTagMaria {

    public static final String NAME = "create-backup-tag-maria";

    public static Task create() {
        return new TaskBuilder()
                .withName(NAME)
                .skip(() -> !M7rImageMaria.exists())
                .execute(M7rImageMaria::createBackupTag)
                .build();
    }

}

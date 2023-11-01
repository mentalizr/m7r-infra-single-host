package org.mentalizr.infra.tasks.removeImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rImageDebian;

public class CreateBackupTagDebian {

    public static final String NAME = "create-backup-tag-debian";

    public static Task create() {
        return new TaskBuilder()
                .withName(NAME)
                .skip(() -> !M7rImageDebian.exists())
                .execute(M7rImageDebian::createBackupTag)
                .build();
    }

}

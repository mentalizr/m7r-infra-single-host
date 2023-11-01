package org.mentalizr.infra.tasks.removeImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;

public class CreateBackups {

    public static final String NAME = "create-backups";

    public static Task create() {
        return new TaskBuilder()
                .withName(NAME)
                .withDependencies(CreateBackupTagMongo.NAME, CreateBackupTagMaria.NAME, CreateBackupTagDebian.NAME, CreateBackupTagJDK.NAME, CreateBackupTagTomcat.NAME, CreateBackupTagNginx.NAME)
                .asTarget()
                .execute(() -> {})
                .build();
    }

}

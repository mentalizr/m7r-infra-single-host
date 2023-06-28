package org.mentalizr.infra.tasks.backup;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.taskAgent.BackupTaskAgent;

public class Backup {

    public static Task create() {
        return new TaskBuilder()
                .withName("backup")
                .withDescription("backup")
                .asTarget()
                .execute(BackupTaskAgent::backup)
                .build();
    }

}

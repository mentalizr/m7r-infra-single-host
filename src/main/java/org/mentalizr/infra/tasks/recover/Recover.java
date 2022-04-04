package org.mentalizr.infra.tasks.recover;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.cli.adminApi.Backup;
import org.mentalizr.cli.adminApi.DatabaseStatus;
import org.mentalizr.infra.taskAgent.RecoverUserDB;

public class Recover {

    public static Task create() {
        return new TaskBuilder()
                .name("recover")
                .description("recover")
                .isTarget()
                .isUpToDate(RecoverUserDB::isDatabaseNotEmpty)
                .execute(RecoverUserDB::recoverDefault)
                .build();
    }

}

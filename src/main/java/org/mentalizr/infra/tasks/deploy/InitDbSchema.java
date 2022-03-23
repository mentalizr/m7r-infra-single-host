package org.mentalizr.infra.tasks.deploy;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.buildEntities.dbSchema.DbSchema;

public class InitDbSchema {

    public static Task create() {
        return new TaskBuilder()
                .name("init-db-schema")
                .description("init database schema")
                .dependencies("deploy-resrc")
                .inputChanged(() -> false)
                .outputExists(DbSchema::hasDbCreatedTables)
                .execute(DbSchema::deploy)
                .build();
    }

}

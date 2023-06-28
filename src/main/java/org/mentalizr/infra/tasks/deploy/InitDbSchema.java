package org.mentalizr.infra.tasks.deploy;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.buildEntities.dbSchema.DbSchema;

public class InitDbSchema {

    public static Task create() {
        return new TaskBuilder()
                .withName("init-db-schema")
                .withDescription("init database schema")
                .withDependencies("deploy-resrc")
                .isUpToDate(DbSchema::hasDbCreatedTables)
                .execute(DbSchema::deploy)
                .build();
    }

}

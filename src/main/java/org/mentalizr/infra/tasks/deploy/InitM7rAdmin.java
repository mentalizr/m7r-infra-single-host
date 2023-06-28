package org.mentalizr.infra.tasks.deploy;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.buildEntities.M7rAdmin;

public class InitM7rAdmin {

    public static Task create() {
        return new TaskBuilder()
                .withName("init-m7r-admin")
                .withDescription("init database admin user")
                .withDependencies("init-db-schema")
                .isUpToDate(M7rAdmin::isAdminUserInitialized)
                .execute(M7rAdmin::init)
                .build();
    }

}

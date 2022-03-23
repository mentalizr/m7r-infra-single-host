package org.mentalizr.infra.tasks.deploy;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.buildEntities.M7rAdmin;

public class InitM7rAdmin {

    public static Task create() {
        return new TaskBuilder()
                .name("init-m7r-admin")
                .description("init database admin user")
                .dependencies("init-db-schema")
                .inputChanged(() -> false)
                .outputExists(M7rAdmin::isAdminUserInitialized)
                .execute(M7rAdmin::init)
                .build();
    }

}

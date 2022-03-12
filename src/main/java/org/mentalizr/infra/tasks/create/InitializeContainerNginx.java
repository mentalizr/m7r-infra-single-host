package org.mentalizr.infra.tasks.create;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;

public class InitializeContainerNginx {

    public static Task create() {
        return new TaskBuilder()
                .name("initialize-container-nginx")
                .description("initialize container nginx")
                .dependencies("create-container-nginx")
                .inputChanged(() -> false)
//                .outputExists(M7rContainerMongo::exists)
                .execute(() -> {}) // TODO
                .build();
    }

}

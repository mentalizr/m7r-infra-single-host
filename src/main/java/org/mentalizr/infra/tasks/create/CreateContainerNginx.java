package org.mentalizr.infra.tasks.create;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;

public class CreateContainerNginx {

    public static Task create() {
        return new TaskBuilder()
                .name("create-container-nginx")
                .description("create container nginx")
                .inputChanged(() -> false)
//                .outputExists(M7rContainerMongo::exists)
//                .execute(M7rContainerMongo::create)       // TODO
                .execute(() -> {})
                .build();
    }

}

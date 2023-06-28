package org.mentalizr.infra.tasks.create.nginx;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;

public class CreateNginx {

    public static Task create() {
        return new TaskBuilder()
                .asTarget()
                .withName("create-nginx")
                .withDescription("create nginx")
                .withDependencies("initialize-container-nginx")
                .execute(()-> {})
                .build();
    }

}

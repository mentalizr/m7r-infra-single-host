package org.mentalizr.infra.tasks.create.nginx;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;

public class CreateNginx {

    public static Task create() {
        return new TaskBuilder()
                .isTarget()
                .name("create-nginx")
                .description("create nginx")
                .dependencies("initialize-container-tomcat", "create-tomcat")
                .execute(()-> {})
                .build();
    }

}

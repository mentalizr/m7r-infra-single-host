package org.mentalizr.infra.tasks.create.nginx;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rContainerNginx;

public class InitializeContainerNginx {

    public static Task create() {
        return new TaskBuilder()
                .name("initialize-container-nginx")
                .description("initialize container nginx")
                .dependencies("create-container-nginx")
                .execute(M7rContainerNginx::initialize)
                .build();
    }

}

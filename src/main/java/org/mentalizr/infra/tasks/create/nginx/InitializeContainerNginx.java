package org.mentalizr.infra.tasks.create.nginx;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rContainerNginx;

public class InitializeContainerNginx {

    public static Task create() {
        return new TaskBuilder()
                .withName("initialize-container-nginx")
                .withDescription("initialize container nginx")
                .withDependencies("create-container-nginx")
                .execute(M7rContainerNginx::initialize)
                .build();
    }

}

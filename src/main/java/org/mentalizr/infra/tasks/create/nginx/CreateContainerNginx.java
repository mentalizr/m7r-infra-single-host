package org.mentalizr.infra.tasks.create.nginx;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rContainerNginx;

public class CreateContainerNginx {

    public static Task create() {
        return new TaskBuilder()
                .name("create-container-nginx")
                .description("create container nginx")
                .inputChanged(() -> false)
                .outputExists(M7rContainerNginx::exists)
                .execute(M7rContainerNginx::create)
                .build();
    }

}

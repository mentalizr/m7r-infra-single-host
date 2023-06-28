package org.mentalizr.infra.tasks.create.nginx;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rContainerNginx;

public class CreateContainerNginx {

    public static Task create() {
        return new TaskBuilder()
                .withName("create-container-nginx")
                .withDescription("create container nginx")
                .withDependencies("create-network")
                .isUpToDate(M7rContainerNginx::exists)
                .execute(M7rContainerNginx::create)
                .build();
    }

}

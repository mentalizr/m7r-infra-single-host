package org.mentalizr.infra.tasks.remove.nginx;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rContainerNginx;
import org.mentalizr.infra.docker.m7r.M7rContainerTomcat;

public class RemoveContainerNginx {

    public static Task create() {
        return new TaskBuilder()
                .withName("remove-container-nginx")
                .withDescription("remove container nginx")
                .isUpToDate(() -> !M7rContainerNginx.exists())
                .execute(M7rContainerNginx::remove)
                .build();
    }

}

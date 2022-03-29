package org.mentalizr.infra.tasks.start.nginx;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.Const;
import org.mentalizr.infra.docker.m7r.M7rContainerMongo;
import org.mentalizr.infra.docker.m7r.M7rContainerNginx;

public class StartContainerNginx {

    public static Task create() {
        return new TaskBuilder()
                .name("start-container-nginx")
                .description("start [" + Const.CONTAINER_NGINX + "]")
                .isUpToDate(M7rContainerNginx::isRunning)
                .execute(M7rContainerNginx::start)
                .build();
    }

}

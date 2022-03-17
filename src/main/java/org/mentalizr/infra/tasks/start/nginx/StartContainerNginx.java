package org.mentalizr.infra.tasks.start.nginx;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.Const;

public class StartContainerNginx {

    public static Task create() {
        return new TaskBuilder()
                .name("start-container-nginx")
                .description("start [" + Const.CONTAINER_NGINX)
                .execute(() -> {})
                .build();
    }

}

package org.mentalizr.infra.tasks.start.nginx;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.Const;

public class AwaitUpNginx {

    public static Task create() {
        return new TaskBuilder()
                .withName("await-up-nginx")
                .withDescription("await up [" + Const.CONTAINER_NGINX + "]")
                .withDependencies("start-container-nginx")
                .execute(() -> {})
                .build();
    }

}

package org.mentalizr.infra.tasks.start.nginx;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.Const;

public class AwaitUpNginx {

    public static Task create() {
        return new TaskBuilder()
                .name("await-up-nginx")
                .description("await up [" + Const.CONTAINER_NGINX + "]")
                .dependencies("start-container-nginx")
                .execute(() -> {})
                .build();
    }

}

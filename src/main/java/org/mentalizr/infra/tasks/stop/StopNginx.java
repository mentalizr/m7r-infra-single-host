package org.mentalizr.infra.tasks.stop;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;

public class StopNginx {

    public static Task create() {
        return new TaskBuilder()
                .name("stop-nginx")
                .description("stop nginx")
                .execute(() -> {})
                .build();
    }

}

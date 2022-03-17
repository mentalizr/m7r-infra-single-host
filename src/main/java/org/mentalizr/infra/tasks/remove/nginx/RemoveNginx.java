package org.mentalizr.infra.tasks.remove.nginx;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;

public class RemoveNginx {

    public static Task create() {
        return new TaskBuilder()
                .name("remove-nginx")
                .description("remove nginx")
//                .dependencies("remove-nginx")
                .execute(() -> {})
                .build();
    }

}

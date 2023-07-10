package org.mentalizr.infra.tasks.remove.nginx;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;

public class RemoveNginx {

    public static Task create() {
        return new TaskBuilder()
                .withName("remove-nginx")
                .withDescription("remove nginx")
                .withDependencies("remove-container-nginx")
                .execute(() -> {})
                .build();
    }

}

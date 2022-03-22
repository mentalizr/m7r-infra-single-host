package org.mentalizr.infra.tasks.deploy;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;

public class Deploy {

    public static Task create() {
        return new TaskBuilder()
                .name("deploy")
                .description("deploy")
                .dependencies("recover")
                .isTarget()
                .execute(() -> {})
                .build();
    }

}

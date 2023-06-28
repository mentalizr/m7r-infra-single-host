package org.mentalizr.infra.tasks.deploy;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;

public class Deploy {

    public static Task create() {
        return new TaskBuilder()
                .withName("deploy")
                .withDescription("deploy")
                .withDependencies("init-m7r-admin")
                .asTarget()
                .execute(() -> {})
                .build();
    }

}

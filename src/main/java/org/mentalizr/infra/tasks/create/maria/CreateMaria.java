package org.mentalizr.infra.tasks.create.maria;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;

public class CreateMaria {

    public static Task create() {
        return new TaskBuilder()
                .asTarget()
                .withName("create-maria")
                .withDescription("create maria")
                .withDependencies("initialize-container-maria")
                .execute(()-> {})
                .build();
    }

}

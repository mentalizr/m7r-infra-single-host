package org.mentalizr.infra.tasks.start.maria;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rContainerMaria;
import org.mentalizr.infra.docker.m7r.M7rContainerMongo;

public class StartMaria {

    public static Task create() {
        return new TaskBuilder()
                .name("start-maria")
                .description("start maria")
                .dependencies("await-up-maria")
                .execute(() -> {})
                .build();
    }

}

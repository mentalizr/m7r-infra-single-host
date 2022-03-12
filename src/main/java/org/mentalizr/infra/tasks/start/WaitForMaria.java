package org.mentalizr.infra.tasks.start;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rContainerMongo;

public class WaitForMaria {

    public static Task create() {
        return new TaskBuilder()
                .name("wait-for-maria")
                .description("wait for maria to come up")
                .dependencies("start-nginx")
                .execute(() -> {})
                .build();
    }

}

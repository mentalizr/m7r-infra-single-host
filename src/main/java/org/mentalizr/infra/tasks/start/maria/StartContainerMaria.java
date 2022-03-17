package org.mentalizr.infra.tasks.start.maria;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.Const;
import org.mentalizr.infra.docker.m7r.M7rContainerMaria;

public class StartContainerMaria {

    public static Task create() {
        return new TaskBuilder()
                .name("start-container-maria")
                .description("start container [" + Const.CONTAINER_MARIA + "]")
                .inputChanged(() -> true)
                .outputExists(M7rContainerMaria::isRunning)
                .execute(M7rContainerMaria::start)
                .build();
    }

}

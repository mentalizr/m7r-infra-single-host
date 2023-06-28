package org.mentalizr.infra.tasks.remove.maria;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.Const;
import org.mentalizr.infra.docker.m7r.M7rContainerMaria;
import org.mentalizr.infra.docker.m7r.M7rContainerMongo;

public class RemoveContainerMaria {

    public static Task create() {
        return new TaskBuilder()
                .withName("remove-container-maria")
                .withDescription("remove container [" + Const.CONTAINER_MARIA + "]")
                .isUpToDate(() -> !M7rContainerMaria.exists())
                .execute(M7rContainerMaria::remove)
                .build();
    }

}

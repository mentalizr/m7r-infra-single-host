package org.mentalizr.infra.tasks.remove.mongo;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.Const;
import org.mentalizr.infra.docker.m7r.M7rContainerMongo;
import org.mentalizr.infra.docker.m7r.M7rNetwork;

public class RemoveContainerMongo {

    public static Task create() {
        return new TaskBuilder()
                .withName("remove-container-mongo")
                .withDescription("remove container mongo")
                .isUpToDate(() -> !M7rContainerMongo.exists())
                .execute(M7rContainerMongo::remove)
                .build();
    }

}

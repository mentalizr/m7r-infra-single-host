package org.mentalizr.infra.tasks.start.mongo;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.Const;
import org.mentalizr.infra.docker.m7r.M7rContainerMongo;

public class StartContainerMongo {

    public static Task create() {
        return new TaskBuilder()
                .name("start-container-mongo")
                .description("start container [" + Const.CONTAINER_MONGO + "]")
                .isUpToDate(M7rContainerMongo::isRunning)
                .execute(M7rContainerMongo::start)
                .build();
    }

}

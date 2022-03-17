package org.mentalizr.infra.tasks.start.mongo;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.Const;
import org.mentalizr.infra.buildEntities.ConnectionMongo;
import org.mentalizr.infra.docker.m7r.M7rContainerMongo;

public class AwaitUpMongo {

    public static Task create() {
        return new TaskBuilder()
                .name("await-up-mongo")
                .description("await up [" + Const.CONTAINER_MONGO + "]")
                .dependencies("start-container-mongo")
                .execute(ConnectionMongo::probe)
                .build();
    }

}

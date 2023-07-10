package org.mentalizr.infra.tasks.start.mongo;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.Const;
import org.mentalizr.infra.buildEntities.connections.ConnectionMongo;

public class AwaitUpMongo {

    public static Task create() {
        return new TaskBuilder()
                .withName("await-up-mongo")
                .withDescription("await up [" + Const.CONTAINER_MONGO + "]")
                .withDependencies("start-container-mongo")
                .execute(ConnectionMongo::probe)
                .build();
    }

}

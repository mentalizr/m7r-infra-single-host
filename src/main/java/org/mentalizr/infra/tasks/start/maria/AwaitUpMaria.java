package org.mentalizr.infra.tasks.start.maria;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.Const;
import org.mentalizr.infra.buildEntities.connections.ConnectionMaria;

public class AwaitUpMaria {

    public static Task create() {
        return new TaskBuilder()
                .withName("await-up-maria")
                .withDescription("await up [" + Const.CONTAINER_MARIA + "]")
                .withDependencies("start-container-maria")
                .execute(ConnectionMaria::awaitUp)
                .build();
    }

}

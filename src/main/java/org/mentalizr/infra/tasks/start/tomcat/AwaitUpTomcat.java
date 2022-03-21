package org.mentalizr.infra.tasks.start.tomcat;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.Const;
import org.mentalizr.infra.buildEntities.connections.ConnectionTomcat;

public class AwaitUpTomcat {

    public static Task create() {
        return new TaskBuilder()
                .name("await-up-tomcat")
                .description("await up [" + Const.CONTAINER_TOMCAT + "]")
                .dependencies("start-container-tomcat")
                .execute(ConnectionTomcat::awaitUp)
                .build();
    }

}

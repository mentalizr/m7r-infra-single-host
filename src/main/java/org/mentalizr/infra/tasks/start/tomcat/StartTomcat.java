package org.mentalizr.infra.tasks.start.tomcat;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.Const;
import org.mentalizr.infra.docker.m7r.M7rContainerMongo;

public class StartTomcat {

    public static Task create() {
        return new TaskBuilder()
                .withName("start-tomcat")
                .withDescription("start [" + Const.CONTAINER_TOMCAT + "]")
                .withDependencies("await-up-tomcat")
                .execute(() -> {})
                .build();
    }

}

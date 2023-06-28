package org.mentalizr.infra.tasks.start.tomcat;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.Const;
import org.mentalizr.infra.docker.m7r.M7rContainerTomcat;

public class StartContainerTomcat {

    public static Task create() {
        return new TaskBuilder()
                .withName("start-container-tomcat")
                .withDescription("start [" + Const.CONTAINER_TOMCAT + "]")
                .withDependencies("start-maria", "start-mongo")
                .isUpToDate(M7rContainerTomcat::isRunning)
                .execute(M7rContainerTomcat::start)
                .build();
    }

}

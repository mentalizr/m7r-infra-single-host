package org.mentalizr.infra.tasks.start.tomcat;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.Const;
import org.mentalizr.infra.docker.m7r.M7rContainerTomcat;

public class StartContainerTomcat {

    public static Task create() {
        return new TaskBuilder()
                .name("start-container-tomcat")
                .description("start [" + Const.CONTAINER_TOMCAT + "]")
                .dependencies("start-maria", "start-mongo")
                .isUpToDate(M7rContainerTomcat::isRunning)
                .execute(M7rContainerTomcat::start)
                .build();
    }

}

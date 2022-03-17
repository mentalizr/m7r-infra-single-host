package org.mentalizr.infra.tasks.start.tomcat;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.Const;

public class StartContainerTomcat {

    public static Task create() {
        return new TaskBuilder()
                .name("start-container-tomcat")
                .description("start [" + Const.CONTAINER_TOMCAT + "]")
                .dependencies("start-maria", "start-mongo")
                .execute(() -> {})
                .build();
    }

}

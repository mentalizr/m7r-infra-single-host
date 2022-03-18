package org.mentalizr.infra.tasks.stop;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rContainerTomcat;

public class StopTomcat {

    public static Task create() {
        return new TaskBuilder()
                .name("stop-tomcat")
                .description("stop tomcat")
                .dependencies("stop-nginx")
                .execute(M7rContainerTomcat::stop)
                .build();
    }

}

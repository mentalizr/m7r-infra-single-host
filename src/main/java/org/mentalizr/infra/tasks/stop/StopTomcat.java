package org.mentalizr.infra.tasks.stop;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rContainerTomcat;

public class StopTomcat {

    public static Task create() {
        return new TaskBuilder()
                .withName("stop-tomcat")
                .withDescription("stop tomcat")
                .withDependencies("stop-nginx")
                .execute(M7rContainerTomcat::stop)
                .build();
    }

}

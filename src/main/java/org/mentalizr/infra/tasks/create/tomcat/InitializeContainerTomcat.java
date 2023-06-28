package org.mentalizr.infra.tasks.create.tomcat;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rContainerTomcat;

public class InitializeContainerTomcat {

    public static Task create() {
        return new TaskBuilder()
                .withName("initialize-container-tomcat")
                .withDescription("initialize container tomcat")
                .withDependencies("create-container-tomcat")
                .execute(M7rContainerTomcat::initialize)
                .build();
    }

}

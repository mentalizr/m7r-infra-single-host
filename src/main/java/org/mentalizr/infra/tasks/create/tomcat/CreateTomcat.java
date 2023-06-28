package org.mentalizr.infra.tasks.create.tomcat;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;

public class CreateTomcat {

    public static Task create() {
        return new TaskBuilder()
                .asTarget()
                .withName("create-tomcat")
                .withDescription("create tomcat")
                .withDependencies("initialize-container-tomcat")
                .execute(()-> {})
                .build();
    }

}

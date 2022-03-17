package org.mentalizr.infra.tasks.create.tomcat;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;

public class CreateTomcat {

    public static Task create() {
        return new TaskBuilder()
                .isTarget()
                .name("create-tomcat")
                .description("create tomcat")
                .dependencies("initialize-container-tomcat", "create-maria", "create-mongo")
                .execute(()-> {})
                .build();
    }

}

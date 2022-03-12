package org.mentalizr.infra.tasks.create;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;

public class CreateContainerTomcat {

    public static Task create() {
        return new TaskBuilder()
                .name("create-container-tomcat")
                .description("create container tomcat")
                .dependencies("create-volume-tomcat")
                .inputChanged(() -> false)
//                .outputExists(M7rContainerMongo::exists)
//                .execute(M7rContainerMongo::create)       // TODO
                .execute(() -> {})
                .build();
    }

}

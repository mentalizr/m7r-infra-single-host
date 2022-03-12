package org.mentalizr.infra.tasks.create;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;

public class InitializeContainerTomcat {

    public static Task create() {
        return new TaskBuilder()
                .name("initialize-container-tomcat")
                .description("initialize container tomcat")
                .dependencies("create-container-tomcat")
                .inputChanged(() -> false)
//                .outputExists(M7rContainerMongo::exists)
                .execute(() -> {}) // TODO
                .build();
    }

}

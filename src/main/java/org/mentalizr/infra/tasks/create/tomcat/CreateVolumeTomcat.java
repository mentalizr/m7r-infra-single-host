package org.mentalizr.infra.tasks.create.tomcat;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;

public class CreateVolumeTomcat {

    public static Task create() {
        return new TaskBuilder()
                .name("create-volume-tomcat")
                .description("create docker volume for tomcat")
                .inputChanged(() -> false)
//                .outputExists(M7rVolumeMongo::exists)   // TODO
//                .execute(M7rVolumeMongo::create)        // TODO
                .execute(() -> {})
                .build();
    }

}

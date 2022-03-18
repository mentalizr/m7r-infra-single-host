package org.mentalizr.infra.tasks.remove.tomcat;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;

public class RemoveTomcat {

    public static Task create() {
        return new TaskBuilder()
                .name("remove-tomcat")
                .description("remove tomcat")
                .dependencies("remove-nginx", "remove-volume-tomcat")
                .execute(() -> {})
                .build();
    }

}

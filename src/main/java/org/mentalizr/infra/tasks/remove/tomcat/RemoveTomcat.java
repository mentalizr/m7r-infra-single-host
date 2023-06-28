package org.mentalizr.infra.tasks.remove.tomcat;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;

public class RemoveTomcat {

    public static Task create() {
        return new TaskBuilder()
                .withName("remove-tomcat")
                .withDescription("remove tomcat")
                .withDependencies("remove-volume-tomcat")
                .execute(() -> {})
                .build();
    }

}

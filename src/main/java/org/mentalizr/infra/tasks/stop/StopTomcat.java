package org.mentalizr.infra.tasks.stop;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;

public class StopTomcat {

    public static Task create() {
        return new TaskBuilder()
                .name("stop-tomcat")
                .description("stop tomcat")
                .dependencies("stop-nginx")
                .execute(() -> {})
                .build();
    }

}

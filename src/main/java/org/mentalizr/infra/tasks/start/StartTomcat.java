package org.mentalizr.infra.tasks.start;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rContainerMongo;

public class StartTomcat {

    public static Task create() {
        return new TaskBuilder()
                .name("start-tomcat")
                .description("start tomcat")
                .dependencies("start-maria")
                .execute(() -> {})
                .build();
    }

}

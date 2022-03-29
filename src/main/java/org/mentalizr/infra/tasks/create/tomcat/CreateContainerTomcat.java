package org.mentalizr.infra.tasks.create.tomcat;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rContainerTomcat;

public class CreateContainerTomcat {

    public static Task create() {
        return new TaskBuilder()
                .name("create-container-tomcat")
                .description("create container tomcat")
                .dependencies("create-volume-tomcat")
                .isUpToDate(M7rContainerTomcat::exists)
                .execute(M7rContainerTomcat::create)
                .build();
    }

}

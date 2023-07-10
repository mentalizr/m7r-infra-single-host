package org.mentalizr.infra.tasks.remove.tomcat;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rContainerTomcat;

public class RemoveContainerTomcat {

    public static Task create() {
        return new TaskBuilder()
                .withName("remove-container-tomcat")
                .withDescription("remove container tomcat")
                .isUpToDate(() -> !M7rContainerTomcat.exists())
                .execute(M7rContainerTomcat::remove)
                .build();
    }

}

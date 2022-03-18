package org.mentalizr.infra.tasks.remove.tomcat;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rContainerTomcat;

public class RemoveContainerTomcat {

    public static Task create() {
        return new TaskBuilder()
                .name("remove-container-tomcat")
                .description("remove container tomcat")
                .inputChanged(() -> false)
                .outputExists(() -> !M7rContainerTomcat.exists())
                .execute(M7rContainerTomcat::remove)
                .build();
    }

}

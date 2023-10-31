package org.mentalizr.infra.tasks.createImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rImageTomcat;

public class CreateImageTomcat {

    public static final String NAME = "create-image-tomcat";

    public static Task create() {
        return new TaskBuilder()
                .withName(NAME)
                .withDependencies(CreateImageJDK.NAME)
                .execute(M7rImageTomcat::build)
                .build();
    }

}

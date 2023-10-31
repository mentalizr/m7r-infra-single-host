package org.mentalizr.infra.tasks.createImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rImageDebian;
import org.mentalizr.infra.docker.m7r.M7rImageJDK;

public class CreateImageJDK {

    public static String NAME = "create-image-jdk";

    public static Task create() {
        return new TaskBuilder()
                .withName(NAME)
                .withDependencies(CreateImageDebian.NAME)
                .execute(M7rImageJDK::build)
                .build();
    }

}

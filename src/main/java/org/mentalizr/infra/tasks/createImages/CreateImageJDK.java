package org.mentalizr.infra.tasks.createImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rImageDebian;
import org.mentalizr.infra.docker.m7r.M7rImageJDK;

public class CreateImageJDK {

    public static Task create() {
        return new TaskBuilder()
                .withName("create-image-jdk")
                .withDependencies("create-image-debian")
                .execute(M7rImageJDK::build)
                .build();
    }

}

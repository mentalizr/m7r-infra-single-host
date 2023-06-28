package org.mentalizr.infra.tasks.createImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rImageNginx;

public class CreateImageNginx {

    public static Task create() {
        return new TaskBuilder()
                .withName("create-image-nginx")
                .execute(M7rImageNginx::build)
                .build();
    }

}

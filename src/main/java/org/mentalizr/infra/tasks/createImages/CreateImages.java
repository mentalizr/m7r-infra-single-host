package org.mentalizr.infra.tasks.createImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rImageNginx;

public class CreateImages {

    public static Task create() {
        return new TaskBuilder()
                .name("create-images")
                .dependencies("create-image-mongo", "create-image-maria", "create-image-tomcat", "create-image-nginx")
                .isTarget()
                .execute(() -> {})
                .build();
    }

}

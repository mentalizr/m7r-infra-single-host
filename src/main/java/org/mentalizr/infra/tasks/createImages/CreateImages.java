package org.mentalizr.infra.tasks.createImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rImageNginx;

public class CreateImages {

    public static final String NAME = "create-images";

    public static Task create() {
        return new TaskBuilder()
                .withName(NAME)
                .withDependencies(CreateImageMongo.NAME, CreateImageMaria.NAME, CreateImageTomcat.NAME, CreateImageNginx.NAME)
                .asTarget()
                .execute(() -> {})
                .build();
    }

}

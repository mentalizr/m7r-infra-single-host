package org.mentalizr.infra.tasks.createImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.tasks.pullImages.PullImageMaria;
import org.mentalizr.infra.tasks.pullImages.PullImageMongo;

public class CreateImages {

    public static final String NAME = "create-images";

    public static Task create() {
        return new TaskBuilder()
                .withName(NAME)
                .withDependencies(PullImageMongo.NAME, PullImageMaria.NAME, CreateImageTomcat.NAME, CreateImageNginx.NAME)
                .asTarget()
                .execute(() -> {})
                .build();
    }

}

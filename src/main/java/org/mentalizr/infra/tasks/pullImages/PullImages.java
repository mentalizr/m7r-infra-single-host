package org.mentalizr.infra.tasks.pullImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;

public class PullImages {

    public static final String NAME = "pull-images";

    public static Task create() {
        return new TaskBuilder()
                .withName(NAME)
                .withDependencies(PullImageMongo.NAME, PullImageMaria.NAME, PullImageTomcat.NAME, PullImageNginx.NAME)
                .asTarget()
                .execute(() -> {})
                .build();
    }

}

package org.mentalizr.infra.tasks.removeImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;

public class RemoveImages {

    public static final String NAME = "remove-images";

    public static Task create() {
        return new TaskBuilder()
                .withName(NAME)
                .withDependencies(RemoveImageMongo.NAME, RemoveImageMaria.NAME, RemoveImageDebian.NAME,
                        RemoveImageJDK.NAME, RemoveImageTomcat.NAME, RemoveImageNginx.NAME)
                .asTarget()
                .execute(() -> {})
                .build();
    }

}

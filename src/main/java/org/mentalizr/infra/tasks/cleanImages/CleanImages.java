package org.mentalizr.infra.tasks.cleanImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;

public class CleanImages {

    public static final String NAME = "clean-images";

    public static Task create() {
        return new TaskBuilder()
                .withName(NAME)
                .withDependencies(
                        CleanImagesMongo.NAME,
                        CleanImagesMaria.NAME,
                        RemoveBaseImageNginx.NAME,
                        RemoveBaseImageDebian.NAME)
                .asTarget()
                .execute(() -> {})
                .build();
    }

}

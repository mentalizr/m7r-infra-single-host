package org.mentalizr.infra.tasks.removeImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;

public class RemoveImagesAll {

    public static final String NAME = "remove-images-all";

    public static Task create() {
        return new TaskBuilder()
                .withName(NAME)
                .withDependencies(RemoveImageMongoAll.NAME, RemoveImageMariaAll.NAME, RemoveImageDebianAll.NAME, RemoveImageJDKAll.NAME, RemoveImageTomcatAll.NAME, RemoveImageNginxAll.NAME)
                .asTarget()
                .execute(() -> {})
                .build();
    }

}

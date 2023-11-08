package org.mentalizr.infra.tasks.cleanImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rImageMongo;

public class CleanImagesMongo {

    public static final String NAME = "clean-images-mongo";

    public static Task create() {
        return new TaskBuilder()
                .withName(NAME)
                .isUpToDate(() -> !M7rImageMongo.existsAny())
                .execute(M7rImageMongo::clean)
                .build();
    }

}

package org.mentalizr.infra.tasks.removeImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rImageMongo;

public class RemoveImageMongoAll {

    public static final String NAME = "remove-image-mongo-all";

    public static Task create() {
        return new TaskBuilder()
                .withName(NAME)
                .isUpToDate(() -> !M7rImageMongo.existsAny())
                .execute(M7rImageMongo::removeAll)
                .build();
    }

}

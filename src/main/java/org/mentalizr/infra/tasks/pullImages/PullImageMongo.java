package org.mentalizr.infra.tasks.pullImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rImageMongo;

public class PullImageMongo {

    public static Task create() {
        return new TaskBuilder()
                .withName("pull-image-mongo")
                .execute(M7rImageMongo::pull)
                .build();
    }

}

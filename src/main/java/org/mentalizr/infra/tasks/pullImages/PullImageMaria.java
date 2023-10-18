package org.mentalizr.infra.tasks.pullImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rImageMaria;

public class PullImageMaria {

    public static Task create() {
        return new TaskBuilder()
                .withName("pull-image-maria")
                .execute(M7rImageMaria::pull)
                .build();
    }

}

package org.mentalizr.infra.tasks.createImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rImageDebian;

public class CreateImageDebian {

    public static final String NAME = "create-image-debian";

    public static Task create() {
        return new TaskBuilder()
                .withName(NAME)
                .execute(M7rImageDebian::buildLatest)
                .build();
    }

}

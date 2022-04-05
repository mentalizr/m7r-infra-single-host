package org.mentalizr.infra.tasks.createImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rImageDebian;
import org.mentalizr.infra.docker.m7r.M7rImageTomcat;

public class CreateImageDebian {

    public static Task create() {
        return new TaskBuilder()
                .name("create-image-debian")
                .execute(M7rImageDebian::build)
                .build();
    }

}

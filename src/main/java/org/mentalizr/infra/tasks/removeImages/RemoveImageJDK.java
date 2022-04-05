package org.mentalizr.infra.tasks.removeImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rImageDebian;
import org.mentalizr.infra.docker.m7r.M7rImageJDK;

public class RemoveImageJDK {

    public static Task create() {
        return new TaskBuilder()
                .name("remove-image-jdk")
                .isUpToDate(() -> !M7rImageJDK.exists())
                .execute(M7rImageJDK::remove)
                .build();
    }

}

package org.mentalizr.infra.tasks.cleanImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rImageTomcat;

public class CleanImagesTomcat {

    public static final String NAME = "clean-images-tomcat";

    public static Task create() {
        return new TaskBuilder()
                .withName(NAME)
                .isUpToDate(() -> !M7rImageTomcat.existsAny())
                .execute(M7rImageTomcat::clean)
                .build();
    }

}

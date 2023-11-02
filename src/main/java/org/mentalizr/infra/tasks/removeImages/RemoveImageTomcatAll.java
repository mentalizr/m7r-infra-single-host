package org.mentalizr.infra.tasks.removeImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rImageTomcat;

public class RemoveImageTomcatAll {

    public static final String NAME = "remove-image-tomcat-all";

    public static Task create() {
        return new TaskBuilder()
                .withName(NAME)
                .isUpToDate(() -> !M7rImageTomcat.existsAny())
                .execute(M7rImageTomcat::removeAll)
                .build();
    }

}

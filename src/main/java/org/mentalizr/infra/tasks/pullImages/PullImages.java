package org.mentalizr.infra.tasks.pullImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;

public class PullImages {

    public static Task create() {
        return new TaskBuilder()
                .withName("pull-images")
                .withDependencies("pull-image-mongo", "pull-image-maria", "pull-image-tomcat", "pull-image-nginx")
                .asTarget()
                .execute(() -> {})
                .build();
    }

}

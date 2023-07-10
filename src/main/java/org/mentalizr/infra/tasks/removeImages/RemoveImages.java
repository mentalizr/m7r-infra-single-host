package org.mentalizr.infra.tasks.removeImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;

public class RemoveImages {

    public static Task create() {
        return new TaskBuilder()
                .withName("remove-images")
                .withDependencies("remove-image-mongo", "remove-image-maria", "remove-image-debian", "remove-image-jdk", "remove-image-tomcat", "remove-image-nginx")
                .asTarget()
                .execute(() -> {})
                .build();
    }

}

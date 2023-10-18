package org.mentalizr.infra.tasks.pullImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rImageTomcat;

public class PullImageTomcat {

    public static Task create() {
        return new TaskBuilder()
                .withName("pull-image-tomcat")
                .execute(M7rImageTomcat::pull)
                .build();
    }

}

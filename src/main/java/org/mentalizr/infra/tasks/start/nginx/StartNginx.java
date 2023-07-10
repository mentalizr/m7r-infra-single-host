package org.mentalizr.infra.tasks.start.nginx;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rContainerMongo;
import org.mentalizr.infra.docker.m7r.M7rContainerNginx;

public class StartNginx {

    public static Task create() {
        return new TaskBuilder()
                .withName("start-nginx")
                .withDescription("start nginx")
                .withDependencies("start-tomcat", "await-up-nginx")
                .execute(() -> {})
                .build();
    }

}

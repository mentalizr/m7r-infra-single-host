package org.mentalizr.infra.tasks.stop;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rContainerMaria;

public class StopMaria {

    public static Task create() {
        return new TaskBuilder()
                .withName("stop-maria")
                .withDescription("stop maria")
                .withDependencies("stop-tomcat")
                .execute(M7rContainerMaria::stop)
                .build();
    }

}

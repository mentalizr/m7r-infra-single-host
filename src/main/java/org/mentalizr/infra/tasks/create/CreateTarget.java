package org.mentalizr.infra.tasks.create;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.Const;
import org.mentalizr.infra.docker.m7r.M7rNetwork;

public class CreateTarget {

    public static Task create() {
        return new TaskBuilder()
                .asTarget()
                .withName("create")
                .withDescription("create docker infrastructure")
                .withDependencies("create-maria", "create-nginx", "create-mongo", "create-tomcat")
                .execute(()-> {})
                .build();
    }

}

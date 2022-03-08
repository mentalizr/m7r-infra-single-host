package org.mentalizr.infra.tasks;

import de.arthurpicht.taskRunner.taskRegistry.TaskRegistry;
import de.arthurpicht.taskRunner.taskRegistry.TaskRegistryBuilder;
import org.mentalizr.infra.tasks.create.CreateNetwork;
import org.mentalizr.infra.tasks.create.CreateTarget;
import org.mentalizr.infra.tasks.create.RemoveNetwork;
import org.mentalizr.infra.tasks.create.RemoveTarget;

public class InfraTaskRegistry {

    public static TaskRegistry create() {
        TaskRegistryBuilder taskRegistryBuilder = new TaskRegistryBuilder();

        taskRegistryBuilder.withTask(CreateNetwork.create());
        taskRegistryBuilder.withTask(CreateTarget.create());

        taskRegistryBuilder.withTask(RemoveNetwork.create());
        taskRegistryBuilder.withTask(RemoveTarget.create());

        return taskRegistryBuilder.build();
    }

}

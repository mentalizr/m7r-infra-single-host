package org.mentalizr.infra.tasks;

import de.arthurpicht.taskRunner.taskRegistry.TaskRegistry;
import de.arthurpicht.taskRunner.taskRegistry.TaskRegistryBuilder;
import org.mentalizr.infra.tasks.create.*;
import org.mentalizr.infra.tasks.remove.RemoveNetwork;
import org.mentalizr.infra.tasks.remove.RemoveTarget;
import org.mentalizr.infra.tasks.remove.RemoveVolumeMongo;

public class InfraTaskRegistry {

    public static TaskRegistry create() {
        TaskRegistryBuilder taskRegistryBuilder = new TaskRegistryBuilder();

        taskRegistryBuilder.withTask(CreateDirs.create());
        taskRegistryBuilder.withTask(CreateNetwork.create());
        taskRegistryBuilder.withTask(CreateMaria.create());
        taskRegistryBuilder.withTask(CreateVolumeMongo.create());
        taskRegistryBuilder.withTask(CreateContainerMongo.create());
        taskRegistryBuilder.withTask(InitializeContainerMongo.create());
        taskRegistryBuilder.withTask(CreateMongo.create());
        taskRegistryBuilder.withTask(CreateTomcat.create());
        taskRegistryBuilder.withTask(CreateNginx.create());
        taskRegistryBuilder.withTask(CreateTarget.create());

        taskRegistryBuilder.withTask(RemoveVolumeMongo.create());
        taskRegistryBuilder.withTask(RemoveNetwork.create());
        taskRegistryBuilder.withTask(RemoveTarget.create());

        return taskRegistryBuilder.build();
    }

}

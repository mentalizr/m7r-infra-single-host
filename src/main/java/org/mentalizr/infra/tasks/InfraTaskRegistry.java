package org.mentalizr.infra.tasks;

import de.arthurpicht.taskRunner.taskRegistry.TaskRegistry;
import de.arthurpicht.taskRunner.taskRegistry.TaskRegistryBuilder;
import org.mentalizr.infra.tasks.create.*;
import org.mentalizr.infra.tasks.remove.RemoveContainerMongo;
import org.mentalizr.infra.tasks.remove.RemoveNetwork;
import org.mentalizr.infra.tasks.remove.RemoveTarget;
import org.mentalizr.infra.tasks.remove.RemoveVolumeMongo;
import org.mentalizr.infra.tasks.start.*;
import org.mentalizr.infra.tasks.stop.*;

public class InfraTaskRegistry {

    public static TaskRegistry create() {
        TaskRegistryBuilder taskRegistryBuilder = new TaskRegistryBuilder();

        taskRegistryBuilder.withTask(CreateTarget.create());

        taskRegistryBuilder
                .withTask(CreateNginx.create())
                .withTask(CreateContainerNginx.create());

        taskRegistryBuilder
                .withTask(CreateTomcat.create())
                .withTask(InitializeContainerTomcat.create())
                .withTask(CreateContainerTomcat.create())
                .withTask(CreateVolumeTomcat.create());

        taskRegistryBuilder
                .withTask(CreateMaria.create())
                .withTask(InitializeContainerMaria.create())
                .withTask(CreateContainerMaria.create())
                .withTask(CreateVolumeMaria.create());

        taskRegistryBuilder.withTask(CreateMongo.create())
                .withTask(InitializeContainerMongo.create())
                .withTask(CreateContainerMongo.create())
                .withTask(CreateVolumeMongo.create());

        taskRegistryBuilder.withTask(CreateNetwork.create());
        taskRegistryBuilder.withTask(CreateDirs.create());

        taskRegistryBuilder.withTask(RemoveTarget.create());
        taskRegistryBuilder.withTask(RemoveVolumeMongo.create());
        taskRegistryBuilder.withTask(RemoveContainerMongo.create());

        taskRegistryBuilder.withTask(RemoveNetwork.create());

        taskRegistryBuilder
                .withTask(StartTarget.create())
                .withTask(WaitForMaria.create())
                .withTask(StartNginx.create())
                .withTask(StartTomcat.create())
                .withTask(StartMaria.create())
                .withTask(StartMongo.create());

        taskRegistryBuilder
                .withTask(StopTarget.create())
                .withTask(StopMongo.create())
                .withTask(StopMaria.create())
                .withTask(StopTomcat.create())
                .withTask(StopNginx.create());

        return taskRegistryBuilder.build();
    }

}

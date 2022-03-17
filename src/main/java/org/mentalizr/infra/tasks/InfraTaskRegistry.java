package org.mentalizr.infra.tasks;

import de.arthurpicht.taskRunner.taskRegistry.TaskRegistry;
import de.arthurpicht.taskRunner.taskRegistry.TaskRegistryBuilder;
import org.mentalizr.infra.tasks.create.*;
import org.mentalizr.infra.tasks.create.maria.CreateContainerMaria;
import org.mentalizr.infra.tasks.create.maria.CreateMaria;
import org.mentalizr.infra.tasks.create.maria.CreateVolumeMaria;
import org.mentalizr.infra.tasks.create.maria.InitializeContainerMaria;
import org.mentalizr.infra.tasks.create.mongo.CreateContainerMongo;
import org.mentalizr.infra.tasks.create.mongo.CreateMongo;
import org.mentalizr.infra.tasks.create.mongo.CreateVolumeMongo;
import org.mentalizr.infra.tasks.create.mongo.InitializeContainerMongo;
import org.mentalizr.infra.tasks.create.nginx.CreateContainerNginx;
import org.mentalizr.infra.tasks.create.nginx.CreateNginx;
import org.mentalizr.infra.tasks.create.tomcat.CreateContainerTomcat;
import org.mentalizr.infra.tasks.create.tomcat.CreateTomcat;
import org.mentalizr.infra.tasks.create.tomcat.CreateVolumeTomcat;
import org.mentalizr.infra.tasks.create.tomcat.InitializeContainerTomcat;
import org.mentalizr.infra.tasks.remove.*;
import org.mentalizr.infra.tasks.remove.maria.RemoveContainerMaria;
import org.mentalizr.infra.tasks.remove.maria.RemoveMaria;
import org.mentalizr.infra.tasks.remove.maria.RemoveVolumeMaria;
import org.mentalizr.infra.tasks.remove.mongo.RemoveContainerMongo;
import org.mentalizr.infra.tasks.remove.mongo.RemoveMongo;
import org.mentalizr.infra.tasks.remove.mongo.RemoveVolumeMongo;
import org.mentalizr.infra.tasks.remove.nginx.RemoveNginx;
import org.mentalizr.infra.tasks.remove.tomcat.RemoveTomcat;
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
        taskRegistryBuilder.withTask(RemoveMongo.create());
        taskRegistryBuilder.withTask(RemoveVolumeMongo.create());
        taskRegistryBuilder.withTask(RemoveContainerMongo.create());
        taskRegistryBuilder.withTask(RemoveMaria.create());
        taskRegistryBuilder.withTask(RemoveVolumeMaria.create());
        taskRegistryBuilder.withTask(RemoveContainerMaria.create());
        taskRegistryBuilder.withTask(RemoveTomcat.create());
        taskRegistryBuilder.withTask(RemoveNginx.create());

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

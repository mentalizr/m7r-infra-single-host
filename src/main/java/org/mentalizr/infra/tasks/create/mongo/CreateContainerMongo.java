package org.mentalizr.infra.tasks.create.mongo;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rContainerMongo;
import org.mentalizr.infra.docker.m7r.M7rVolumeMongo;

public class CreateContainerMongo {

    public static Task create() {
        return new TaskBuilder()
                .name("create-container-mongo")
                .description("create container mongo")
                .dependencies("create-volume-mongo")
                .isUpToDate(M7rContainerMongo::exists)
                .execute(M7rContainerMongo::create)
                .build();
    }

}

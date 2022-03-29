package org.mentalizr.infra.tasks.create.mongo;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.Const;
import org.mentalizr.infra.docker.m7r.M7rNetwork;
import org.mentalizr.infra.docker.m7r.M7rVolumeMongo;

public class CreateVolumeMongo {

    public static Task create() {
        return new TaskBuilder()
                .name("create-volume-mongo")
                .description("create docker volume for mongo")
                .dependencies("create-network")
                .isUpToDate(M7rVolumeMongo::exists)
                .execute(M7rVolumeMongo::create)
                .build();
    }

}

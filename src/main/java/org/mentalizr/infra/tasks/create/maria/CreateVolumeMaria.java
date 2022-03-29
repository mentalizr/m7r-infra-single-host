package org.mentalizr.infra.tasks.create.maria;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rVolumeMaria;
import org.mentalizr.infra.docker.m7r.M7rVolumeMongo;

public class CreateVolumeMaria {

    public static Task create() {
        return new TaskBuilder()
                .name("create-volume-maria")
                .description("create docker volume for maria")
                .dependencies("create-network")
                .isUpToDate(M7rVolumeMaria::exists)
                .execute(M7rVolumeMaria::create)
                .build();
    }

}

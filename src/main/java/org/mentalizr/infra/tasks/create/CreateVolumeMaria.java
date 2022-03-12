package org.mentalizr.infra.tasks.create;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rVolumeMongo;

public class CreateVolumeMaria {

    public static Task create() {
        return new TaskBuilder()
                .name("create-volume-maria")
                .description("create docker volume for maria")
                .dependencies("create-network")
                .inputChanged(() -> false)
//                .outputExists(M7rVolumeMongo::exists)   // TODO
//                .execute(M7rVolumeMongo::create)        // TODO
                .execute(() -> {})
                .build();
    }

}

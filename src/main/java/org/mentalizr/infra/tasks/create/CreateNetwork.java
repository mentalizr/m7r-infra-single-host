package org.mentalizr.infra.tasks.create;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.Const;
import org.mentalizr.infra.docker.m7r.M7rNetwork;

public class CreateNetwork {

    public static Task create() {
        return new TaskBuilder()
                .name("create-network")
                .description("create docker network [" + Const.NETWORK + "]")
                .dependencies("create-dirs")
                .isUpToDate(M7rNetwork::exists)
                .execute(M7rNetwork::create)
                .build();
    }

}

package org.mentalizr.infra.tasks.remove;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.Const;
import org.mentalizr.infra.docker.m7r.M7rNetwork;

public class RemoveNetwork {

    public static Task create() {
        return new TaskBuilder()
                .withName("remove-network")
                .withDescription("remove docker network [" + Const.NETWORK + "]")
                .withDependencies("remove-mongo", "remove-maria", "remove-tomcat", "remove-nginx")
                .isUpToDate(() -> !M7rNetwork.exists())
                .execute(M7rNetwork::remove)
                .build();
    }

}

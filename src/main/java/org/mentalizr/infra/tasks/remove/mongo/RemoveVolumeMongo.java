package org.mentalizr.infra.tasks.remove.mongo;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.Const;
import org.mentalizr.infra.docker.m7r.M7rNetwork;
import org.mentalizr.infra.docker.m7r.M7rVolumeMongo;

public class RemoveVolumeMongo {

    public static Task create() {
        return new TaskBuilder()
                .withName("remove-volume-mongo")
                .withDescription("remove docker volume [" + Const.VOLUME_MONGO + "]")
                .withDependencies("remove-container-mongo")
                .isUpToDate(() -> !M7rVolumeMongo.exists())
                .execute(M7rVolumeMongo::remove)
                .build();
    }

}

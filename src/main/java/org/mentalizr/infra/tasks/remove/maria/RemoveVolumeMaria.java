package org.mentalizr.infra.tasks.remove.maria;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.Const;
import org.mentalizr.infra.docker.m7r.M7rVolumeMaria;
import org.mentalizr.infra.docker.m7r.M7rVolumeMongo;

public class RemoveVolumeMaria {

    public static Task create() {
        return new TaskBuilder()
                .name("remove-volume-maria")
                .description("remove docker volume [" + Const.VOLUME_MARIA + "]")
                .dependencies("remove-container-maria")
                .isUpToDate(() -> !M7rVolumeMaria.exists())
                .execute(M7rVolumeMaria::remove)
                .build();
    }

}

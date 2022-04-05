package org.mentalizr.infra.tasks.removeImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rImageDebian;
import org.mentalizr.infra.docker.m7r.M7rImageTomcat;

public class RemoveImageDebian {

    public static Task create() {
        return new TaskBuilder()
                .name("remove-image-debian")
                .dependencies("create-backup-tag-debian")
                .isUpToDate(() -> !M7rImageDebian.exists())
                .execute(M7rImageDebian::remove)
                .build();
    }

}

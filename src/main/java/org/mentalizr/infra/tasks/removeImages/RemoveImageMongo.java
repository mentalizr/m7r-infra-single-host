package org.mentalizr.infra.tasks.removeImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rImageMongo;

public class RemoveImageMongo {

    public static Task create() {
        return new TaskBuilder()
                .name("remove-image-mongo")
                .dependencies("create-backup-tag-mongo")
                .isUpToDate(() -> !M7rImageMongo.exists())
                .execute(M7rImageMongo::remove)
                .build();
    }

}

package org.mentalizr.infra.tasks.create;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.Const;
import org.mentalizr.infra.buildEntities.LogDirs;
import org.mentalizr.infra.docker.m7r.M7rNetwork;

public class CreateDirs {

    public static Task create() {
        return new TaskBuilder()
                .name("create-dirs")
                .description("create directories")
                .inputChanged(() -> false)
                .outputExists(LogDirs::existsAllLogDirs)
                .execute(LogDirs::createAllLogDirs)
                .build();
    }

}

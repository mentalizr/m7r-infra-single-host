package org.mentalizr.infra.tasks.deploy;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;

public class UpdateHtml {

    public static Task create() {
        return new TaskBuilder()
                .name("update-html")
                .description("deploy html")
                .dependencies("deploy-war")
                .execute(() -> {})
                .build();
    }

}

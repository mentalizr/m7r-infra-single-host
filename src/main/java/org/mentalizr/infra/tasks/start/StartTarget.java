package org.mentalizr.infra.tasks.start;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;

public class StartTarget {

    public static Task create() {
        return new TaskBuilder()
                .isTarget()
                .name("start")
                .description("start docker infrastructure")
                .dependencies("wait-for-maria")
                .execute(()-> {})
                .build();
    }

}

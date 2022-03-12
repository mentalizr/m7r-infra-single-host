package org.mentalizr.infra.tasks.stop;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;

public class StopTarget {

    public static Task create() {
        return new TaskBuilder()
                .isTarget()
                .name("stop")
                .description("stop docker infrastructure")
                .dependencies("stop-mongo")
                .execute(()-> {})
                .build();
    }

}

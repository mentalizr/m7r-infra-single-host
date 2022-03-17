package org.mentalizr.infra.tasks.start.maria;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.Const;

public class AwaitUpMaria {

    public static Task create() {
        return new TaskBuilder()
                .name("await-up-maria")
                .description("await up [" + Const.CONTAINER_MARIA + "]")
                .dependencies("start-container-maria")
                .execute(() -> {})
                .build();
    }

}

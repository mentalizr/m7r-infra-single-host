package org.mentalizr.infra.tasks.recover;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.taskAgent.RecoverTaskAgent;

public class RecoverLatest {

    public static Task create() {
        return new TaskBuilder()
                .name("recover-latest")
                .description("recover latest backup")
                .isTarget()
                .isUpToDate(RecoverTaskAgent::isDatabaseNotEmpty)
                .execute(RecoverTaskAgent::recoverLatest)
                .build();

    }

}

package org.mentalizr.infra.tasks.recover;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.taskAgent.RecoverTaskAgent;

public class RecoverDev {

    public static Task create() {
        return new TaskBuilder()
                .name("recover-dev")
                .description("recover database for dev")
                .isTarget()
                .isUpToDate(RecoverTaskAgent::isDatabaseNotEmpty)
                .execute(RecoverTaskAgent::recoverDev)
                .build();

    }

}

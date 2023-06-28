package org.mentalizr.infra.tasks.recover;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.taskAgent.RecoverTaskAgent;

public class RecoverDev {

    public static Task create() {
        return new TaskBuilder()
                .withName("recover-dev")
                .withDescription("recover database for dev")
                .asTarget()
                .isUpToDate(RecoverTaskAgent::isDatabaseNotEmpty)
                .execute(RecoverTaskAgent::recoverDev)
                .build();

    }

}

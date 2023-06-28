package org.mentalizr.infra.tasks.deploy;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.taskAgent.WebAppResources;

public class DeployResrc {

    public static Task create() {
        return new TaskBuilder()
                .withName("deploy-resrc")
                .withDescription("deploy resources")
                .withDependencies("update-html")
                .isUpToDate(WebAppResources::isDeployed)
                .execute(WebAppResources::deploy)
                .build();
    }

}

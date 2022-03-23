package org.mentalizr.infra.tasks.deploy;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.buildEntities.webAppResources.WebAppResources;

public class DeployResrc {

    public static Task create() {
        return new TaskBuilder()
                .name("deploy-resrc")
                .description("deploy resources")
                .dependencies("update-html")
                .execute(WebAppResources::deploy)
                .build();
    }

}

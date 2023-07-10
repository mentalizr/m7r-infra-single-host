package org.mentalizr.infra.tasks.deploy;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.taskAgent.HtmlFiles;

public class UpdateHtml {

    public static Task create() {
        return new TaskBuilder()
                .withName("update-html")
                .withDescription("deploy html")
                .withDependencies("deploy-war")
                .isUpToDate(HtmlFiles::isDeployed)
                .execute(HtmlFiles::deploy)
                .build();
    }

}

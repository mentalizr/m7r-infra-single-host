package org.mentalizr.infra.tasks.deploy;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.build.Backend;
import org.mentalizr.infra.buildEntities.connections.ConnectionTomcat;
import org.mentalizr.infra.buildEntities.html.HtmlChecksum;
import org.mentalizr.infra.docker.m7r.M7rContainerMaria;
import org.mentalizr.infra.docker.m7r.M7rContainerTomcat;
import org.mentalizr.infra.taskEntities.HtmlFilesTE;

public class DeployWar {

    public static Task create() {
        return new TaskBuilder()
                .name("deploy-war")
                .description("deploy war")
                .inputChanged(() -> false)
                .precondition(M7rContainerTomcat::assertIsRunning)
                .outputExists(Backend::isCurrentBuildAlreadyDeployed)
                .execute(() -> {
                    M7rContainerTomcat.deployWar();
                    HtmlFilesTE.writeChecksumToContainer();
                    ConnectionTomcat.awaitDeployment();
                })
                .build();
    }

}

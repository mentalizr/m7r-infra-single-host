package org.mentalizr.infra.tasks.remove.tomcat;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.Const;
import org.mentalizr.infra.docker.m7r.M7rVolumeTomcat;

public class RemoveVolumeTomcat {

    public static Task create() {
        return new TaskBuilder()
                .name("remove-volume-tomcat")
                .description("remove docker volume [" + Const.VOLUME_TOMCAT + "]")
                .dependencies("remove-container-tomcat")
                .inputChanged(() -> false)
                .outputExists(() -> !M7rVolumeTomcat.exists())
                .execute(M7rVolumeTomcat::remove)
                .build();
    }

}

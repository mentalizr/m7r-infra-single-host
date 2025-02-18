package org.mentalizr.infra.docker.m7r;

import org.mentalizr.infra.Const;
import org.mentalizr.infra.InfraException;
import org.mentalizr.infra.InfraRuntimeException;
import org.mentalizr.infra.appInit.ApplicationContext;
import org.mentalizr.infra.docker.DockerExecutionContext;
import org.mentalizr.infra.docker.Volume;

public class M7rVolumeMongo {

    public static boolean exists() {
        DockerExecutionContext context = ApplicationContext.getDockerExecutionContext();
        try {
            return Volume.exists(context, Const.VOLUME_MONGO);
        } catch (InfraException e) {
            throw new InfraRuntimeException(e);
        }
    }

    public static void create() {
        DockerExecutionContext context = ApplicationContext.getDockerExecutionContext();
        try {
            Volume.create(context, Const.VOLUME_MONGO);
        } catch (InfraException e) {
            throw new InfraRuntimeException(e);
        }
    }

    public static void remove() {
        DockerExecutionContext context = ApplicationContext.getDockerExecutionContext();
        try {
            Volume.remove(context, Const.VOLUME_MONGO);
        } catch (InfraException e) {
            throw new InfraRuntimeException(e);
        }
    }

}

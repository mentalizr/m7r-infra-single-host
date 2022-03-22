package org.mentalizr.infra.docker.m7r;

import org.mentalizr.infra.ExecutionContext;
import org.mentalizr.infra.Const;
import org.mentalizr.infra.InfraException;
import org.mentalizr.infra.InfraRuntimeException;
import org.mentalizr.infra.docker.DockerExecutionContext;
import org.mentalizr.infra.docker.Network;

public class M7rNetwork {

    public static boolean exists() {
        DockerExecutionContext context = ExecutionContext.getDockerExecutionContext();
        try {
            return Network.exists(context, Const.NETWORK);
        } catch (InfraException e) {
            throw new InfraRuntimeException(e);
        }
    }

    public static void create() {
        DockerExecutionContext context = ExecutionContext.getDockerExecutionContext();
        try {
            Network.create(context, Const.NETWORK);
        } catch (InfraException e) {
            throw new InfraRuntimeException(e);
        }
    }

    public static void remove() {
        DockerExecutionContext context = ExecutionContext.getDockerExecutionContext();
        try {
            Network.remove(context, Const.NETWORK);
        } catch (InfraException e) {
            throw new InfraRuntimeException(e);
        }
    }

}

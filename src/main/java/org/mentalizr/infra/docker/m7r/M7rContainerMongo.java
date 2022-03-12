package org.mentalizr.infra.docker.m7r;

import org.mentalizr.backend.config.Configuration;
import org.mentalizr.infra.*;
import org.mentalizr.infra.docker.Container;
import org.mentalizr.infra.docker.Docker;
import org.mentalizr.infra.linux.LinuxExecutionException;
import org.mentalizr.infra.linux.User;

public class M7rContainerMongo {

    public static boolean exists() {
        try {
            return Container.exists(Const.CONTAINER_MONGO);
        } catch (DockerExecutionException e) {
            throw new InfraRuntimeException(e);
        }
    }

    public static boolean isRunning() {
        try {
            return Container.isRunning(Const.CONTAINER_MONGO);
        } catch (DockerExecutionException e) {
            throw new InfraRuntimeException(e);
        }
    }

    public static boolean isStopped() {
        try {
            return !Container.isRunning(Const.CONTAINER_MONGO);
        } catch (DockerExecutionException e) {
            throw new InfraRuntimeException(e);
        }
    }

    public static void create() {
        //docker create \
        //    --name $NAME_MONGO \
        //	--network $NETWORK \
        //	--network-alias mongo \
        //	--mount source=$VOL_MONGO,target=/data/db \
        //	-e MONGO_INITDB_ROOT_USERNAME=admin \
        //	-e MONGO_INITDB_ROOT_PASSWORD=geheim \
        //	-e MONGO_INITDB_DATABASE=admin \
        //	-p 27017:27017 \
        //	$MONGO_IMAGE >> /dev/null

        if (exists())
            throw new InfraRuntimeException("Cannot create container [" + Const.CONTAINER_MONGO + "]." +
                    " Already existing.");

        int userId;
        try {
            userId = User.getUserId();
        } catch (LinuxExecutionException e) {
            throw new InfraRuntimeException(e);
        }

        try {
            Docker.call(
                    "docker", "create",
                    "--name", Const.CONTAINER_MONGO,
                    "--user", Integer.toString(userId),
                    "--network", Const.NETWORK,
                    "--network-alias", "mongo",
                    "--mount", "source=" + Const.VOLUME_MONGO +",target=/data/db",
                    "-e", "MONGO_INITDB_ROOT_USERNAME=" + Configuration.getUserDbUser(),
                    "-e", "MONGO_INITDB_ROOT_PASSWORD=" + Configuration.getDocumentDbPassword(),
                    "-e", "MONGO_INITDB_DATABASE=admin",
                    "-p", "27017:27017",
                    Const.IMAGE_MONGO);
        } catch (DockerExecutionException e) {
            throw new InfraRuntimeException(e);
        }
    }

    public static void start() {
        try {
            Container.start(Const.CONTAINER_MONGO);
        } catch (DockerExecutionException | IllegalInfraStateException e) {
            throw new InfraRuntimeException(e);
        }
    }

    public static void stop() {
        try {
            Container.stop(Const.CONTAINER_MONGO);
        } catch (DockerExecutionException | IllegalInfraStateException e) {
            throw new InfraRuntimeException(e);
        }
    }

    public static void remove() {
        try {
            Docker.call(
                    "docker", "container", "rm", Const.CONTAINER_MONGO);
        } catch (DockerExecutionException e) {
            throw new InfraRuntimeException(e);
        }
    }

}

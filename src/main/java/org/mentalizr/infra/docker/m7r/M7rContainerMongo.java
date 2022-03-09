package org.mentalizr.infra.docker.m7r;

import org.mentalizr.backend.config.Configuration;
import org.mentalizr.infra.*;
import org.mentalizr.infra.docker.Container;
import org.mentalizr.infra.docker.Docker;

public class M7rContainerMongo {

    public static boolean exists() {
        try {
            return Container.exists(Const.CONTAINER_MONGO);
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

        try {
            Docker.call(
                    "docker", "create",
                    "--name", Const.CONTAINER_MONGO,
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

}

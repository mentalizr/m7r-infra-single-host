package org.mentalizr.infra.docker.m7r;

import org.mentalizr.backend.config.Configuration;
import org.mentalizr.infra.Const;
import org.mentalizr.infra.IllegalInfraStateException;
import org.mentalizr.infra.InfraException;
import org.mentalizr.infra.docker.Container;
import org.mentalizr.infra.docker.Docker;

public class M7rMongoContainer {

    private static final String name = "mongo";

    public static void create(Configuration configuration) throws InfraException {

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

        if (Container.exists(name)) throw new IllegalInfraStateException("Cannot create container [" + name + "]. Already existing.");
        Docker.call(
                "docker", "create",
                "--name", Const.CONTAINER_MONGO_NAME,
                "--network", Const.NETWORK,
                "--network-alias", "mongo",
                "--mount", "source=" + Const.VOL_MONGO_NAME +",target=/data/db",
                "-e", "MONGO_INITDB_ROOT_USERNAME=" + Configuration.getUserDbUser(),
                "-e", "MONGO_INITDB_ROOT_PASSWORD=" + Configuration.getDocumentDbPassword(),
                "-e", "MONGO_INITDB_DATABASE=admin",
                "-p", "27017:27017",
                Const.IMAGE_MONGO);

    }

}

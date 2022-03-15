package org.mentalizr.infra.docker;

import org.mentalizr.infra.IllegalInfraStateException;
import org.mentalizr.infra.InfraException;
import org.mentalizr.infra.process.collect.ProcessCollectResult;
import org.mentalizr.infra.processExecutor.ProcessResultCollection;

import static org.mentalizr.infra.docker.Docker.call;

public class Volume {

    public static boolean exists(DockerExecutionContext context, String name) throws InfraException {
        ProcessResultCollection result = call(context, "docker", "volume", "ls", "--format", "{{.Name}}");
        return result.getStandardOut().contains(name);
    }

    public static void create(DockerExecutionContext context, String name) throws InfraException {
        if (exists(context, name)) throw new IllegalInfraStateException("Cannot create volume [" + name + "]. Already existing.");
        call(context, "docker", "volume", "create", name);
    }

    public static boolean isConnected(DockerExecutionContext context, String name) throws InfraException {
        // docker volume ls -q -f name=vol_m7r_tomcat_dev -f dangling=false
        if (!exists(context, name)) throw new IllegalInfraStateException("Cannot check volume [" + name + "]. Not existing.");
        ProcessResultCollection result = call(context, "docker", "volume", "ls", "-q", "-f", "name=" + name, "-f", "dangling=false");
        return result.getStandardOut().size() > 0;
    }

    public static void remove(DockerExecutionContext context, String name) throws InfraException {
        if (!exists(context, name)) throw new IllegalInfraStateException("Cannot delete volume [" + name + "]. Not existing.");
        if (isConnected(context, name)) throw new IllegalInfraStateException("Cannot delete volume [" + name + "]. Is connected.");
        // TODO: Check no containers connected
        // # RES=$(docker network inspect project-net-demo | jq '.[].Containers')
        // # echo $RES
        // # if [ $RES = "{}" ]; then echo "leer"; else echo "nicht leer"; fi
        call(context, "docker", "volume", "rm", name);
    }

}

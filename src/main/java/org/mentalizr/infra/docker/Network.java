package org.mentalizr.infra.docker;

import org.mentalizr.infra.IllegalInfraStateException;
import org.mentalizr.infra.InfraException;
import org.mentalizr.infra.process.collect.ProcessCollectResult;
import org.mentalizr.infra.processExecutor.ProcessResultCollection;

import static org.mentalizr.infra.docker.Docker.call;

public class Network {

    public static boolean exists(DockerExecutionContext context, String name) throws InfraException {
        ProcessResultCollection result = call(context, "docker", "network", "ls", "--format", "{{.Name}}");
        return result.getStandardOut().contains(name);
    }

    public static void create(DockerExecutionContext context, String name) throws InfraException {
        if (exists(context, name)) throw new IllegalInfraStateException("Network [" + name + "] already existing.");
        call(context, "docker", "network", "create", name);
    }

    public static void remove(DockerExecutionContext context, String name) throws InfraException {
        if (!exists(context, name)) throw new IllegalInfraStateException("Network [" + name + "] not existing.");
        // TODO: Check no containers connected
        // # RES=$(docker network inspect project-net-demo | jq '.[].Containers')
        // # echo $RES
        // # if [ $RES = "{}" ]; then echo "leer"; else echo "nicht leer"; fi
        call(context, "docker", "network", "rm", name);
    }

}

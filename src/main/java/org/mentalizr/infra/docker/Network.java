package org.mentalizr.infra.docker;

import org.mentalizr.infra.IllegalInfraStateException;
import org.mentalizr.infra.InfraException;
import org.mentalizr.infra.process.collect.ProcessCollectResult;

import static org.mentalizr.infra.docker.Docker.call;

public class Network {

    public static boolean exists(String name) throws InfraException {
        ProcessCollectResult result = call("docker", "network", "ls", "--format", "{{.Name}}");
        return result.getStandardOut().contains(name);
    }

    public static void create(String name) throws InfraException {
        if (exists(name)) throw new IllegalInfraStateException("Network [" + name + "] already existing.");
        call("docker", "network", "create", name);
    }

    public static void remove(String name) throws InfraException {
        if (!exists(name)) throw new IllegalInfraStateException("Network [" + name + "] not existing.");
        // TODO: Check no containers connected
        // # RES=$(docker network inspect project-net-demo | jq '.[].Containers')
        // # echo $RES
        // # if [ $RES = "{}" ]; then echo "leer"; else echo "nicht leer"; fi
        call("docker", "network", "rm", name);
    }

}

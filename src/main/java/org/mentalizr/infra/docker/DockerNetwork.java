package org.mentalizr.infra.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Network;

import java.util.List;

public class DockerNetwork {

    public static boolean exists(DockerClient dockerClient, String name) {
        List<Network> networks = dockerClient.listNetworksCmd().exec();
        return networks.stream().anyMatch(n -> n.getName().equals(name));
    }

}

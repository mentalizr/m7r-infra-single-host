package org.mentalizr.infra.docker.m7r;

import org.mentalizr.infra.Const;
import org.mentalizr.infra.InfraException;
import org.mentalizr.infra.InfraRuntimeException;
import org.mentalizr.infra.docker.Network;

public class M7rNetwork {

    public static boolean exists() {
        try {
            return Network.exists(Const.NETWORK);
        } catch (InfraException e) {
            throw new InfraRuntimeException(e);
        }
    }

    public static void create() {
        try {
            Network.create(Const.NETWORK);
        } catch (InfraException e) {
            throw new InfraRuntimeException(e);
        }
    }

    public static void remove() {
        try {
            Network.remove(Const.NETWORK);
        } catch (InfraException e) {
            throw new InfraRuntimeException(e);
        }
    }


}

package org.mentalizr.infra.docker.m7r;

import org.mentalizr.infra.Const;
import org.mentalizr.infra.InfraException;
import org.mentalizr.infra.InfraRuntimeException;
import org.mentalizr.infra.docker.Volume;

public class M7rVolumeMongo {

    public static boolean exists() {
        try {
            return Volume.exists(Const.VOLUME_MONGO);
        } catch (InfraException e) {
            throw new InfraRuntimeException(e);
        }
    }

    public static void create() {
        try {
            Volume.create(Const.VOLUME_MONGO);
        } catch (InfraException e) {
            throw new InfraRuntimeException(e);
        }
    }

    public static void remove() {
        try {
            Volume.remove(Const.VOLUME_MONGO);
        } catch (InfraException e) {
            throw new InfraRuntimeException(e);
        }
    }

}

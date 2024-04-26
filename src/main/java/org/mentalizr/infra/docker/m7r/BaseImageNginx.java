package org.mentalizr.infra.docker.m7r;

import org.mentalizr.infra.Const;

public class BaseImageNginx {

    public static boolean exists() {
        return M7rImage.exists(Const.IMAGE_BASE_NGINX);
    }

    public static void remove() {
        M7rImage.remove(Const.IMAGE_BASE_NGINX);
    }

}

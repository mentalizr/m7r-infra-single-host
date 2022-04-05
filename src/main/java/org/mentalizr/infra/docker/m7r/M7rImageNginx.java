package org.mentalizr.infra.docker.m7r;

import org.mentalizr.infra.Const;

public class M7rImageNginx {

    public static boolean exists() {
        return M7rImage.exists(Const.IMAGE_NGINX);
    }

    public static void build() {
        M7rImage.build(Const.IMAGE_NGINX, Const.IMAGE_NGINX_URL);
    }

    public static void remove() {
        M7rImage.remove(Const.IMAGE_NGINX);
    }

    public static void createBackupTag() {
        String destTaggedImageName = BackupTags.getNameWithBackupTag(Const.IMAGE_NGINX);
        M7rImage.tag(Const.IMAGE_NGINX, destTaggedImageName);
    }

}

package org.mentalizr.infra.docker.m7r;

import org.mentalizr.infra.Const;

public class M7rImageNginx {

    public static boolean exists() {
        return M7rImage.exists(Const.IMAGE_NGINX);
    }

    public static boolean existsAny() {
        return M7rImage.existsAnyIncludingBackups(Const.IMAGE_NGINX);
    }

    public static void pull() {
        M7rImage.pull(Const.IMAGE_NGINX);
    }

    public static void buildLatest() {
        M7rImage.buildLatest(Const.IMAGE_NGINX, Const.IMAGE_NGINX_URL);
    }

    public static void remove() {
        M7rImage.remove(Const.IMAGE_NGINX);
    }

    public static void removeAll() {
        M7rImage.removeIncludingBackups(Const.IMAGE_NGINX);
    }

    public static void createBackupTag() {
        String destTaggedImageName = BackupTags.getNameWithBackupTag(Const.IMAGE_NGINX);
        M7rImage.tag(Const.IMAGE_NGINX, destTaggedImageName);
    }

}

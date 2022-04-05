package org.mentalizr.infra.docker.m7r;

import org.mentalizr.infra.Const;

public class M7rImageMongo {

    public static boolean exists() {
        return M7rImage.exists(Const.IMAGE_MONGO);
    }

    public static void pull() {
        M7rImage.pull(Const.IMAGE_MONGO);
    }

    public static void remove() {
        M7rImage.remove(Const.IMAGE_MONGO);
    }

    public static void createBackupTag() {
        String destTaggedImageName = BackupTags.getNameWithBackupTag(Const.IMAGE_MONGO);
        M7rImage.tag(Const.IMAGE_MONGO, destTaggedImageName);
    }

}

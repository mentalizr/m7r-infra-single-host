package org.mentalizr.infra.docker.m7r;

import org.mentalizr.infra.Const;

public class M7rImageMaria {

    public static boolean exists() {
        return M7rImage.exists(Const.IMAGE_MARIA);
    }

    public static void pull() {
        M7rImage.pull(Const.IMAGE_MARIA);
    }

    public static void remove() {
        M7rImage.remove(Const.IMAGE_MARIA);
    }

    public static void createBackupTag() {
        String destTaggedImageName = BackupTags.getNameWithBackupTag(Const.IMAGE_MARIA);
        M7rImage.tag(Const.IMAGE_MARIA, destTaggedImageName);
    }

}

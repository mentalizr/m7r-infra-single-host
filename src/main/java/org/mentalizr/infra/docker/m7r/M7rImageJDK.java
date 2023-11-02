package org.mentalizr.infra.docker.m7r;

import org.mentalizr.infra.Const;

public class M7rImageJDK {

    public static boolean exists() {
        return M7rImage.exists(Const.IMAGE_JDK);
    }

    public static boolean existsAny() {
        return M7rImage.existsAnyIncludingBackups(Const.IMAGE_JDK);
    }

    public static void build() {
        M7rImage.build(Const.IMAGE_JDK, Const.IMAGE_JDK_URL);
    }

    public static void remove() {
        M7rImage.remove(Const.IMAGE_JDK);
    }

    public static void removeAll() {
        M7rImage.removeIncludingBackups(Const.IMAGE_JDK);
    }

    public static void createBackupTag() {
        String destTaggedImageName = BackupTags.getNameWithBackupTag(Const.IMAGE_JDK);
        M7rImage.tag(Const.IMAGE_JDK, destTaggedImageName);
    }

}

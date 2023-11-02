package org.mentalizr.infra.docker.m7r;

import org.mentalizr.infra.Const;

public class M7rImageTomcat {

    public static boolean exists() {
        return M7rImage.exists(Const.IMAGE_TOMCAT);
    }

    public static boolean existsAny() {
        return M7rImage.existsAnyIncludingBackups(Const.IMAGE_TOMCAT);
    }

    public static void pull() {
        M7rImage.pull(Const.IMAGE_TOMCAT);
    }

    public static void build() {
        M7rImage.build(Const.IMAGE_TOMCAT, Const.IMAGE_TOMCAT_URL);
    }

    public static void remove() {
        M7rImage.remove(Const.IMAGE_TOMCAT);
    }

    public static void removeAll() {
        M7rImage.removeIncludingBackups(Const.IMAGE_TOMCAT);
    }

    public static void createBackupTag() {
        String destTaggedImageName = BackupTags.getNameWithBackupTag(Const.IMAGE_TOMCAT);
        M7rImage.tag(Const.IMAGE_TOMCAT, destTaggedImageName);
    }

}

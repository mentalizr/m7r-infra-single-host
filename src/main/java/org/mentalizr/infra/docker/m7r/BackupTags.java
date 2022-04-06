package org.mentalizr.infra.docker.m7r;

import de.arthurpicht.utils.core.strings.Timestamps;
import org.mentalizr.infra.ExecutionContext;

import java.time.Instant;

public class BackupTags {

    public static String getNameWithBackupTag(String taggedImageName) {
        Instant callTimestamp = ExecutionContext.getCallTimestamp();
        if (taggedImageName.contains(":")) {
            return taggedImageName + "-backup-" + Timestamps.dateHyphenTime(callTimestamp);
        } else {
            return taggedImageName + ":backup-" + Timestamps.dateHyphenTime(callTimestamp);
        }
    }

}

package org.mentalizr.infra.docker.m7r;

import de.arthurpicht.utils.core.dates.TimestampStrings;
import org.mentalizr.infra.ExecutionContext;

import java.time.Instant;

public class BackupTags {

    public static String getNameWithBackupTag(String taggedImageName) {
        Instant callTimestamp = ExecutionContext.getCallTimestamp();
        if (taggedImageName.contains(":")) {
            return taggedImageName + "-backup-" + TimestampStrings.asHyphenTime(callTimestamp);
        } else {
            return taggedImageName + ":backup-" + TimestampStrings.asHyphenTime(callTimestamp);
        }
    }

}

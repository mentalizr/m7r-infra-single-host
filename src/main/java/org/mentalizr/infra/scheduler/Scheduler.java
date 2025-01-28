package org.mentalizr.infra.scheduler;

import de.arthurpicht.linuxWrapper.core.ps.Ps;
import de.arthurpicht.processExecutor.ProcessExecutionException;
import de.arthurpicht.processExecutor.ProcessExecutor;
import de.arthurpicht.processExecutor.ProcessExecutorBuilder;
import de.arthurpicht.processExecutor.ProcessResultCollection;
import de.arthurpicht.utils.core.strings.Strings;
import org.mentalizr.commons.DaemonPidFile;
import org.mentalizr.scheduler.M7rSchedulerException;
import org.mentalizr.scheduler.configuration.JobConfigurations;
import org.mentalizr.scheduler.configuration.SchedulerActiveFlagFile;
import org.mentalizr.infra.utils.Linux;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Scheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(Scheduler.class);
    private static final String SCHEDULER_START_COMMAND = "/home/m7radmin/gitrepos/m7r/core/m7r-scheduler/bin/m7r-scheduler-start.sh";

    public static boolean isRunning() {
        DaemonPidFile pidFile = new DaemonPidFile();
        if (pidFile.exists()) {
            long pid = pidFile.getPid();
            ProcessResultCollection processResultCollection = Ps.execute(Math.toIntExact(pid));
            return !Ps.noProcessForPidFound(processResultCollection);
        } else {
            return false;
        }
    }

    public static boolean isStopped() {
        return !isRunning();
    }

    public static void start() {
        ProcessExecutor processExecutor = new ProcessExecutorBuilder()
                .withCommands(SCHEDULER_START_COMMAND)
                .build();
        try {
            int exitCode = processExecutor.execute();
            if (exitCode != 0)
                throw new RuntimeException("Scheduler start command [" + SCHEDULER_START_COMMAND + " " +
                                           "finished with non-zero exit code: [" + exitCode + "].");
        } catch (ProcessExecutionException e) {
            throw new RuntimeException("Scheduler start command execution failed: " + e.getMessage(), e);
        }
    }

    public static void stop() {
        DaemonPidFile pidFile = new DaemonPidFile();
        if (!pidFile.exists())
            throw new IllegalStateException("PID file does not exist: [" + pidFile + "].");
        int pid = pidFile.getPid();
        ProcessResultCollection processResultCollection = Linux.kill(pid);
        LOGGER.info("Scheduler stopped with PID: " + pid);
        if (processResultCollection.isFail())
            throw new RuntimeException(
                    "Stopping daemon process failed: " + Strings.listing(processResultCollection.getErrorOut(),
                            ", "));
    }

    public static boolean isActive() {
        return SchedulerActiveFlagFile.exists();
    }

    public static boolean isNotActive() {
        return !SchedulerActiveFlagFile.exists();
    }

    public static void activate() {
        try {
            SchedulerActiveFlagFile.create();
        } catch (IOException e) {
            throw new RuntimeException("Error creating scheduler active flag file: " + e.getMessage(), e);
        }
    }

    public static void deactivate() {
        try {
            SchedulerActiveFlagFile.delete();
        } catch (IOException e) {
            throw new RuntimeException("Error deleting scheduler active flag file: " + e.getMessage(), e);
        }
    }

    public static boolean hasConsistentConfiguration() {
        try {
            return JobConfigurations.hasConsistentConfiguration();
        } catch (M7rSchedulerException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}

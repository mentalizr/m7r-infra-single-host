package org.mentalizr.infra.daemon;

import de.arthurpicht.linuxWrapper.core.ps.Ps;
import de.arthurpicht.processExecutor.ProcessExecutionException;
import de.arthurpicht.processExecutor.ProcessExecutor;
import de.arthurpicht.processExecutor.ProcessExecutorBuilder;
import de.arthurpicht.processExecutor.ProcessResultCollection;
import de.arthurpicht.utils.core.strings.Strings;
import org.mentalizr.commons.DaemonPidFile;
import org.mentalizr.infra.executors.DaemonStartExecutor;
import org.mentalizr.infra.utils.Linux;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Daemon {

    private static final Logger LOGGER = LoggerFactory.getLogger(Daemon.class);
    private static final String DAEMON_START_COMMAND = "/home/m7radmin/gitrepos/m7r/core/m7r-daemon/bin/m7r-daemon-start.sh";

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
                .withCommands(DAEMON_START_COMMAND)
                .build();
        try {
            int exitCode = processExecutor.execute();
            if (exitCode != 0)
                throw new RuntimeException("Daemon start command [" + DAEMON_START_COMMAND + " " +
                        "finished with non-zero exit code: [" + exitCode + "].");
        } catch (ProcessExecutionException e) {
            throw new RuntimeException("Daemon start command execution failed: " + e.getMessage(), e);
        }
    }

    public static void stop() {
        DaemonPidFile pidFile = new DaemonPidFile();
        if (!pidFile.exists())
            throw new IllegalStateException("PID file does not exist: [" + pidFile + "].");
        int pid = pidFile.getPid();
        ProcessResultCollection processResultCollection = Linux.kill(pid);
        LOGGER.info("Daemon stopped with PID: " + pid);
        if (processResultCollection.isFail())
            throw new RuntimeException(
                    "Stopping daemon process failed: " + Strings.listing(processResultCollection.getErrorOut(),
                            ", "));
    }

}

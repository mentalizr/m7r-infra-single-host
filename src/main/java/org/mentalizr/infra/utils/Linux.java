package org.mentalizr.infra.utils;

import de.arthurpicht.processExecutor.ProcessExecution;
import de.arthurpicht.processExecutor.ProcessExecutionException;
import de.arthurpicht.processExecutor.ProcessResultCollection;

public class Linux {

    public static ProcessResultCollection kill(int processId) {
        String[] command = {"kill", Integer.toString(processId)};
        try {
            return ProcessExecution.execute(command);
        } catch (ProcessExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}

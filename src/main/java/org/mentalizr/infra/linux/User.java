package org.mentalizr.infra.linux;

import de.arthurpicht.processExecutor.ProcessExecution;
import de.arthurpicht.processExecutor.ProcessExecutionException;
import de.arthurpicht.processExecutor.ProcessResultCollection;

public class User {

    public static int getUserId() throws LinuxExecutionException, ProcessExecutionException {
        ProcessResultCollection result = ProcessExecution.execute("id", "-u");
        if (result.getExitCode() != 0)
            throw new LinuxExecutionException("Unexpected exit of linux process with exit code "
                    + result.getExitCode() + ".");
        if (result.getStandardOut().size() != 1)
            throw new LinuxExecutionException("Unexpected result of linux process. More than one line");
        String resultString = result.getStandardOut().get(0);
        try {
            return Integer.parseInt(resultString);
        } catch (NumberFormatException e) {
            throw new LinuxExecutionException("Unexpected result of linux process. Parsing failed.");
        }
    }

}

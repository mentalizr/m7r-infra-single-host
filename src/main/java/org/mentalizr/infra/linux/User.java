package org.mentalizr.infra.linux;

import org.mentalizr.infra.process.collect.ProcessCollect;
import org.mentalizr.infra.process.collect.ProcessCollectBuilder;
import org.mentalizr.infra.process.collect.ProcessCollectResult;

import java.io.IOException;

public class User {

    public static int getUserId() throws LinuxExecutionException {
        // TODO rework: use ProcessExecutor
        ProcessCollect processCollect = new ProcessCollectBuilder("id", "-u").build();
        ProcessCollectResult result;
        try {
            result = processCollect.call();
        } catch (IOException | InterruptedException e) {
            throw new LinuxExecutionException(e);
        }
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

package org.mentalizr.infra.executors;

import de.arthurpicht.taskRunner.TaskRunner;
import de.arthurpicht.taskRunner.runner.TaskRunnerResult;
import org.mentalizr.infra.tasks.InfraTaskRunner;

public class Restart {

    public static class RestartException extends Exception {
        public RestartException() {
            super();
        }

        public RestartException(Throwable cause) {
            super(cause);
        }
    }

    public static void perform() throws RestartException {

        TaskRunner taskRunner = InfraTaskRunner.create();

        TaskRunnerResult result = taskRunner.run("stop");
        if (!result.isSuccess()) {
            throw new RestartException();
        }

        System.out.println("[wait]");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RestartException(e);
        }

        result = taskRunner.run("start");
        if (!result.isSuccess()) {
            throw new RestartException();
        }
    }


}

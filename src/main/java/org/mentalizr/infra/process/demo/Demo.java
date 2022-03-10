package org.mentalizr.infra.process.demo;

import org.mentalizr.infra.process.collect.ProcessCollect;
import org.mentalizr.infra.process.collect.ProcessCollectBuilder;
import org.mentalizr.infra.process.collect.ProcessCollectResult;
import org.mentalizr.infra.process.handleOutput.ConsoleOutputHandler;
import org.mentalizr.infra.process.handleOutput.ProcessWithOutputHandler;
import org.mentalizr.infra.process.handleOutput.ProcessWithOutputHandlerBuilder;
import org.mentalizr.infra.process.passThrough.ProcessPassThrough;
import org.mentalizr.infra.process.passThrough.ProcessPassThroughBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Demo {

    private void executeAsProcessWithCallback() {
        List<String> commands = Arrays.asList("dothat");

        ProcessWithOutputHandlerBuilder processWithOutputHandlerBuilder = new ProcessWithOutputHandlerBuilder(commands);
        processWithOutputHandlerBuilder.withOutputHandler(new ConsoleOutputHandler());
        ProcessWithOutputHandler processWithOutputHandler = processWithOutputHandlerBuilder.build();

        try {
            int exitCode = processWithOutputHandler.call();
            System.out.println("Exit-Code: " + exitCode);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void executeAsProcessCollect() {
        List<String> commands = Arrays.asList("dothat");

        try {
            ProcessCollectBuilder processCollectBuilder = new ProcessCollectBuilder(commands);
            ProcessCollect processCollect = processCollectBuilder.build();
            ProcessCollectResult result = processCollect.call();

            System.out.println("Exit-Code: " + result.getExitCode());
            System.out.println("Out:");
            result.getStandardOut().forEach(System.out::println);
            System.out.println("Error:");
            result.getErrorOut().forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void executeJoe() {
        List<String> commands = Arrays.asList("joe", "~/temp.txt");
        ProcessPassThroughBuilder processPassThroughBuilder = new ProcessPassThroughBuilder(commands);
        ProcessPassThrough processPassThrough = processPassThroughBuilder.build();

        try {
            processPassThrough.call();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

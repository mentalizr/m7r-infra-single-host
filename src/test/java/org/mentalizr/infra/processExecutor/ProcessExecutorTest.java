package org.mentalizr.infra.processExecutor;

import de.arthurpicht.utils.io.nio2.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProcessExecutorTest {

    @Test
    void singleCommandSimple() throws ProcessExecutionException {
        ProcessExecutor processExecutor = new ProcessExecutorBuilder()
                .withCommands("echo", "Hello World!")
                .build();
        processExecutor.execute();
    }

    @Test
    void singleCommandWithStandardOutCollectionHandlerOnly() throws ProcessExecutionException {
        StandardOutCollectionHandler standardOutCollectionHandler = new StandardOutCollectionHandler();
        ProcessExecutor processExecutor = new ProcessExecutorBuilder()
                .withCommands("echo", "Hello World!")
                .withStandardOutHandler(standardOutCollectionHandler)
                .build();
        processExecutor.execute();

        assertEquals(1, standardOutCollectionHandler.getLines().size());
        assertEquals("Hello World!", standardOutCollectionHandler.getLines().get(0));
    }

    @Test
    void pipedCommandWithStandardOutCollectionHandler() throws ProcessExecutionException {
        StandardOutCollectionHandler standardOutCollectionHandler = new StandardOutCollectionHandler();
        ProcessExecutor processExecutor = new ProcessExecutorBuilder()
                .withCommands("echo", "-e", "Hello World\nhoho")
                .withPipeToCommands("wc", "-l")
                .withStandardOutHandler(standardOutCollectionHandler)
                .build();
        processExecutor.execute();

        assertEquals(1, standardOutCollectionHandler.getLines().size());
        assertEquals("2", standardOutCollectionHandler.getLines().get(0));
    }

    @Test
    void commandWithErrorOut() throws ProcessExecutionException {
        Path workingDir = FileUtils.getWorkingDir().resolve("src/test/testExecutables");
        if (!FileUtils.isExistingDirectory(workingDir))
            throw new RuntimeException("No such directory: [" + workingDir.toAbsolutePath() + "].");
        Path command = workingDir.resolve("outputMessages");
        if (!FileUtils.isExistingRegularFile(command))
            throw new RuntimeException("No such file: [" + command.toAbsolutePath() + "].");

        StandardOutCollectionHandler standardOutCollectionHandler = new StandardOutCollectionHandler();
        StandardErrorCollectionHandler standardErrorCollectionHandler = new StandardErrorCollectionHandler();
        ProcessExecutor processExecutor = new ProcessExecutorBuilder()
                .withCommands("./outputMessages")
                .withWorkingDirectory(workingDir)
                .withStandardOutHandler(standardOutCollectionHandler)
                .withStandardErrorHandler(standardErrorCollectionHandler)
                .build();
        processExecutor.execute();

        assertEquals(1, standardOutCollectionHandler.getLines().size());
        assertEquals("message", standardOutCollectionHandler.getLines().get(0));
        assertEquals(1, standardErrorCollectionHandler.getLines().size());
        assertEquals("error message", standardErrorCollectionHandler.getLines().get(0));
    }

    @Test
    void commandWithErrorOutMergedIntoStandardOut() throws ProcessExecutionException {
        Path workingDir = FileUtils.getWorkingDir().resolve("src/test/testExecutables");
        if (!FileUtils.isExistingDirectory(workingDir))
            throw new RuntimeException("No such directory: [" + workingDir.toAbsolutePath() + "].");
        Path command = workingDir.resolve("outputMessages");
        if (!FileUtils.isExistingRegularFile(command))
            throw new RuntimeException("No such file: [" + command.toAbsolutePath() + "].");

        StandardOutCollectionHandler standardOutCollectionHandler = new StandardOutCollectionHandler();
        StandardErrorCollectionHandler standardErrorCollectionHandler = new StandardErrorCollectionHandler();
        ProcessExecutor processExecutor = new ProcessExecutorBuilder()
                .withCommands("./outputMessages")
                .withWorkingDirectory(workingDir)
                .withStandardErrorMergedIntoStandardOut()
                .withStandardOutHandler(standardOutCollectionHandler)
                .withStandardErrorHandler(standardErrorCollectionHandler)
                .build();
        int exitCode = processExecutor.execute();

        assertEquals(0, exitCode);
        assertEquals(2, standardOutCollectionHandler.getLines().size());
        assertEquals("message", standardOutCollectionHandler.getLines().get(0));
        assertEquals("error message", standardOutCollectionHandler.getLines().get(1));
        assertEquals(0, standardErrorCollectionHandler.getLines().size());
    }

    @Test
    void withInput() throws ProcessExecutionException {
        String inputString = "Hello World!\nHello another world!\n";
        InputStream inputStream = new ByteArrayInputStream(inputString.getBytes());

        StandardOutCollectionHandler standardOutCollectionHandler = new StandardOutCollectionHandler();
        ProcessExecutor processExecutor = new ProcessExecutorBuilder()
                .withInput(inputStream)
                .withCommands("wc", "-l")
                .withStandardErrorMergedIntoStandardOut()
                .withStandardOutHandler(standardOutCollectionHandler)
                .build();
        int exitCode = processExecutor.execute();

        assertEquals(0, exitCode);
        assertEquals(1, standardOutCollectionHandler.getLines().size());
        assertEquals("2", standardOutCollectionHandler.getLines().get(0));
    }

    @Test
    void demoDelayedOutputToConsoleWithControlCharacters() throws ProcessExecutionException {
        Path workingDir = FileUtils.getWorkingDir().resolve("src/test/testExecutables");
        StandardOutToConsoleHandler standardOutToConsoleHandler = new StandardOutToConsoleHandler();
        ProcessExecutor processExecutor = new ProcessExecutorBuilder()
                .withCommands("./delayedOutputWithControl")
                .withWorkingDirectory(workingDir)
                .withStandardOutHandler(standardOutToConsoleHandler)
                .build();
        processExecutor.execute();

    }

}
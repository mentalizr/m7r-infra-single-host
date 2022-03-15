package org.mentalizr.infra.processExecutor.outputHandler.generalOutputHandler;

import org.mentalizr.infra.processExecutor.CollectionHandler;
import org.mentalizr.infra.processExecutor.ProcessExecutor;
import org.slf4j.Logger;
import org.slf4j.event.Level;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static org.slf4j.event.Level.TRACE;

/**
 * Handles standard out of {@link ProcessExecutor} for different destinations:
 *
 * <ul>
 *     <li>writes to console in real time</li>
 *     <li>writes to logger</li>
 *     <li>collects output for further processing</li>
 * </ul>
 */
public abstract class AbstractGeneralOutputHandler implements CollectionHandler {

    private final Logger logger;
    private final Level logLevel;
    private final boolean toConsole;
    private final List<String> lines;

    public AbstractGeneralOutputHandler(Logger logger, Level logLevel, boolean toConsole) {
        this.logger = logger;
        this.logLevel = logLevel;
        this.toConsole = toConsole;
        this.lines = new ArrayList<>();
    }

    public void handleOutput(InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        StringBuilder stringBuilder = new StringBuilder();
        try (inputStreamReader) {
            int data = inputStreamReader.read();
            while(data != -1) {
                char theChar = (char) data;
                if (theChar == '\n') {
                    String line = stringBuilder.toString();
                    log(line);
                    this.lines.add(line);
                    stringBuilder = new StringBuilder();
                } else {
                    if (Character.getType(theChar) != Character.CONTROL) stringBuilder.append(theChar);
                }
                if (this.toConsole) System.out.print(theChar);
                data = inputStreamReader.read();
            }
        }
    }

    private void log(String string) {
        switch (this.logLevel) {
            case TRACE:
                this.logger.trace(string);
                break;
            case DEBUG:
                this.logger.debug(string);
                break;
            case INFO:
                this.logger.info(string);
                break;
            case WARN:
                this.logger.warn(string);
                break;
            case ERROR:
                this.logger.error(string);
                break;
            default:
                throw new RuntimeException("Unknown log level: [" + this.logLevel + "].");
        }
    }

    @Override
    public List<String> getLines() {
        return this.lines;
    }


}

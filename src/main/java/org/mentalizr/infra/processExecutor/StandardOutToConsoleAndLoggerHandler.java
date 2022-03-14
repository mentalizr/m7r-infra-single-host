package org.mentalizr.infra.processExecutor;

import org.slf4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Writes stdOut to console including all control and to logger omitting all control characters.
 */
public class StandardOutToConsoleAndLoggerHandler implements StandardOutHandler {

    private final Logger logger;

    public StandardOutToConsoleAndLoggerHandler(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void handleOutput(InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        StringBuilder stringBuilder = new StringBuilder();
        try (inputStreamReader) {
            int data = inputStreamReader.read();
            while(data != -1) {
                char theChar = (char) data;
                if (theChar == '\n') {
                    this.logger.info(stringBuilder.toString());
                    stringBuilder = new StringBuilder();
                } else {
                    if (Character.getType(theChar) != Character.CONTROL) stringBuilder.append(theChar);
                }
                System.out.print(theChar);
                data = inputStreamReader.read();
            }
        }
    }

}

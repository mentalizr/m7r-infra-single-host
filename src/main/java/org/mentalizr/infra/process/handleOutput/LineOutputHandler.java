package org.mentalizr.infra.process.handleOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Allows for handling the output of a process line by line.
 * Caution: If process produces control characters in output then unexpected things could happen.
 */
public interface LineOutputHandler extends OutputHandler {

    @Override
    default void handleOutput(InputStream inputStream) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                handleLine(line);
            }
        }
    }

    void handleLine(String line);

}

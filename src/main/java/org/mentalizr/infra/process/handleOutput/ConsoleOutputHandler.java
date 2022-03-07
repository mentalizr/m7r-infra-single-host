package org.mentalizr.infra.process.handleOutput;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Writes Output of process to Console including all control characters.
 */
public class ConsoleOutputHandler implements OutputHandler {

    @Override
    public void handleOutput(InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        try (inputStreamReader) {
            int data = inputStreamReader.read();
            while(data != -1) {
                char theChar = (char) data;
                System.out.print(theChar);
                data = inputStreamReader.read();
            }
        }
    }

}

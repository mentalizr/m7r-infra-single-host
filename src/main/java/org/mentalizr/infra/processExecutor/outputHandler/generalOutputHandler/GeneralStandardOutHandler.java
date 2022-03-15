package org.mentalizr.infra.processExecutor.outputHandler.generalOutputHandler;

import org.mentalizr.infra.processExecutor.CollectionHandler;
import org.mentalizr.infra.processExecutor.ProcessExecutor;
import org.mentalizr.infra.processExecutor.StandardOutHandler;
import org.slf4j.Logger;
import org.slf4j.event.Level;

import java.io.IOException;
import java.io.InputStream;

/**
 * Handles standard out of {@link ProcessExecutor} for different destinations:
 *
 * <ul>
 *     <li>writes to console in real time</li>
 *     <li>writes to logger</li>
 *     <li>collects output for further processing</li>
 * </ul>
 */
public class GeneralStandardOutHandler extends AbstractGeneralOutputHandler implements StandardOutHandler, CollectionHandler {

//    private final Logger logger;
//    private final boolean toConsole;
//    private final List<String> lines;

    public GeneralStandardOutHandler(Logger logger, boolean toConsole) {
        super(logger, Level.INFO, toConsole);
//        this.logger = logger;
//        this.toConsole = toConsole;
//        this.lines = new ArrayList<>();
    }

    @Override
    public void handleOutput(InputStream inputStream) throws IOException {
        super.handleOutput(inputStream);
    }

//    @Override
//    public void handleOutput(InputStream inputStream) throws IOException {
//        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//        StringBuilder stringBuilder = new StringBuilder();
//        try (inputStreamReader) {
//            int data = inputStreamReader.read();
//            while(data != -1) {
//                char theChar = (char) data;
//                if (theChar == '\n') {
//                    String line = stringBuilder.toString();
//                    this.logger.info(line);
//                    this.lines.add(line);
//                    stringBuilder = new StringBuilder();
//                } else {
//                    if (Character.getType(theChar) != Character.CONTROL) stringBuilder.append(theChar);
//                }
//                if (this.toConsole) System.out.print(theChar);
//                data = inputStreamReader.read();
//            }
//        }
//    }
//
//    @Override
//    public List<String> getLines() {
//        return this.lines;
//    }

}

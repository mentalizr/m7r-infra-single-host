package org.mentalizr.infra.processExecutor;

import org.mentalizr.infra.utils.InputStreams;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class StandardErrorCollectionHandler implements StandardErrorHandler {

    private List<String> lines;

    public StandardErrorCollectionHandler() {
        this.lines = new ArrayList<>();
    }

    @Override
    public synchronized void handleOutput(InputStream inputStream) throws IOException {
        this.lines = InputStreams.toStrings(inputStream);
    }

    public synchronized List<String> getLines() {
        return this.lines;
    }

}

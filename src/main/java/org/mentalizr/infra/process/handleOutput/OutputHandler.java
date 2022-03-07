package org.mentalizr.infra.process.handleOutput;

import java.io.IOException;
import java.io.InputStream;

public interface OutputHandler {

    void handleOutput(InputStream inputStream) throws IOException;

}

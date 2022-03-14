package org.mentalizr.infra.processExecutor;

import java.io.IOException;
import java.io.InputStream;

public interface StandardOutHandler {

    void handleOutput(InputStream inputStream) throws IOException;

}

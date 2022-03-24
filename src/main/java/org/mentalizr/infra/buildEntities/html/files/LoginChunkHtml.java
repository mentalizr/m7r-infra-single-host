package org.mentalizr.infra.buildEntities.html.files;

import org.mentalizr.commons.paths.M7rFile;
import org.mentalizr.commons.paths.build.FrontendProjectDir;

public class LoginChunkHtml extends M7rFile {

    public LoginChunkHtml() {
        super(new FrontendProjectDir().asPath().resolve("html/login.chunk.html"));
    }

}

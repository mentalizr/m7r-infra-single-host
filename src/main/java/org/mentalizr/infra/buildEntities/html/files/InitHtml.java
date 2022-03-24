package org.mentalizr.infra.buildEntities.html.files;

import org.mentalizr.commons.paths.M7rFile;
import org.mentalizr.commons.paths.build.FrontendProjectDir;

public class InitHtml extends M7rFile {

    public InitHtml() {
        super(new FrontendProjectDir().asPath().resolve("html/init.html"));
    }

}

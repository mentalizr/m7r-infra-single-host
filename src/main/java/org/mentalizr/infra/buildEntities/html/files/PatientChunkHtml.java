package org.mentalizr.infra.buildEntities.html.files;

import org.mentalizr.commons.paths.M7rFile;
import org.mentalizr.commons.paths.build.FrontendProjectDir;

public class PatientChunkHtml extends M7rFile {

    public PatientChunkHtml() {
        super(new FrontendProjectDir().asPath().resolve("html/patient.chunk.html"));
    }

}

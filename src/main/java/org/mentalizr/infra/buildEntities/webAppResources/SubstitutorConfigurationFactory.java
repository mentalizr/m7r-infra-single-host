package org.mentalizr.infra.buildEntities.webAppResources;

import de.arthurpicht.utils.io.tempDir.TempDir;
import de.arthurpicht.utils.core.strings.StringSubstitutorConfiguration;
import org.mentalizr.commons.paths.build.DockerInfraInitResrcDir;
import org.mentalizr.commons.paths.build.FrontendDir;
import org.mentalizr.commons.paths.build.FrontendProjectDir;
import org.mentalizr.commons.paths.build.WebComponentsDir;

public class SubstitutorConfigurationFactory {

    public static StringSubstitutorConfiguration create(TempDir tempDir) {

        String frontendDirString = new FrontendDir().toAbsolutePathString();
        String frontendProjectDirString = new FrontendProjectDir().toAbsolutePathString();
        String tempDirString = tempDir.asPath().toAbsolutePath().toString();
        String dockerInfraInitResrcDirString = new DockerInfraInitResrcDir().toAbsolutePathString();
        String webComponentsDirString = new WebComponentsDir().toAbsolutePathString();

        return new StringSubstitutorConfiguration.Builder()
                .withPre("$")
                .withPost("$")
                .withSubstitution("M7R_FRONTEND", frontendDirString)
                .withSubstitution("M7R_FRONTEND_PROJECT", frontendProjectDirString)
                .withSubstitution("M7R_WEB_COMPONENTS", webComponentsDirString)
                .withSubstitution("TEMP_DIR", tempDirString)
                .withSubstitution("INIT_RESRC", dockerInfraInitResrcDirString)
                .build();
    }

}

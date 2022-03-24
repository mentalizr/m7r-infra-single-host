package org.mentalizr.infra.buildEntities.html.files;

import org.mentalizr.commons.paths.M7rFile;
import org.mentalizr.commons.paths.build.FrontendProjectDir;

public class LoginVoucherChunkHtml extends M7rFile {

    public LoginVoucherChunkHtml() {
        super(new FrontendProjectDir().asPath().resolve("html/loginVoucher.chunk.html"));
    }

}

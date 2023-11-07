package org.mentalizr.infra.buildEntities.html;

import org.mentalizr.infra.buildEntities.ChecksumExchanger;

public class HtmlChecksum {

    private static final String checksumFileName = "html.checksum";

    public static String forHtmlFiles() {
        HtmlFilesRegistry htmlFilesRegistry = HtmlFilesRegistry.getInstance();
        return htmlFilesRegistry.getChecksum();
    }

    public static void writeToContainer() {
        HtmlFilesRegistry htmlFilesRegistry = HtmlFilesRegistry.getInstance();
        String checksum = htmlFilesRegistry.getChecksum();
        ChecksumExchanger.copyToContainer(checksumFileName, checksum);
    }

    public static String readFromContainer() {
        return ChecksumExchanger.readFromContainer(checksumFileName);
    }

}

package org.mentalizr.infra.processExecutor.internals;

import org.mentalizr.infra.utils.ListUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class ProcessWrapperPipedProcesses implements ProcessWrapper {

    private final List<ProcessBuilder> processBuilderList;
    private List<Process> processList;

    public ProcessWrapperPipedProcesses(List<ProcessBuilder> processBuilderList) {
        this.processBuilderList = processBuilderList;
    }

    @Override
    public void start() throws IOException, InterruptedException {
        this.processList = ProcessBuilder.startPipeline(this.processBuilderList);
    }

    @Override
    public InputStream getStandardOut() {
        return ListUtils.getLastElement(this.processList).getInputStream();
    }

    @Override
    public InputStream getErrorOut() {
        return ListUtils.getLastElement(this.processList).getErrorStream();
    }

    @Override
    public OutputStream getStandardIn() {
        return ListUtils.getFirstElement(this.processList).getOutputStream();
    }

    @Override
    public int waitFor() throws InterruptedException {
        return ListUtils.getLastElement(this.processList).waitFor();
    }

}

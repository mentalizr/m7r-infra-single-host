package org.mentalizr.infra.process.collect;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProcessCollect {

    private final ProcessBuilder processBuilder;
    private final InputStream inputStream;

    public ProcessCollect(ProcessBuilder processBuilder) {
        this.processBuilder = processBuilder;
        this.inputStream = null;
    }

    public ProcessCollect(ProcessBuilder processBuilder, InputStream inputStream) {
        this.processBuilder = processBuilder;
        this.inputStream = inputStream;
    }

    public ProcessCollectResult call() throws IOException, InterruptedException {
        Process process = this.processBuilder.start();

        if (this.inputStream != null) {
            OutputStream processStandardIn = process.getOutputStream();
            this.inputStream.transferTo(processStandardIn);
            processStandardIn.flush();
            processStandardIn.close();
        }

        List<String> standardOut = asStringList(process.getInputStream());
        List<String> errorOut = asStringList(process.getErrorStream());

        int exitCode = process.waitFor();

        return new ProcessCollectResult(standardOut, errorOut, exitCode);
    }

    private static List<String> asStringList(InputStream inputStream) throws IOException {
        List<String> stringList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringList.add(line);
            }
        }
        return stringList;
    }

}

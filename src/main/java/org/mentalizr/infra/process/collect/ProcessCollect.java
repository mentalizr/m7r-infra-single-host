package org.mentalizr.infra.process.collect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProcessCollect {

    private final ProcessBuilder processBuilder;

    public ProcessCollect(ProcessBuilder processBuilder) {
        this.processBuilder = processBuilder;
    }

    public ProcessCollectResult call() throws IOException, InterruptedException {
        Process process = this.processBuilder.start();

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

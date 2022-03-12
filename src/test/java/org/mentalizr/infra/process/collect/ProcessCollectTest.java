package org.mentalizr.infra.process.collect;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.*;

class ProcessCollectTest {

    @Test
    void simple() throws IOException, InterruptedException {
        ProcessCollectBuilder processCollectBuilder = new ProcessCollectBuilder("echo", "Hello world!");
        ProcessCollect processCollect = processCollectBuilder.build();
        ProcessCollectResult result = processCollect.call();

        assertEquals(0, result.getExitCode());
        assertEquals(1, result.getStandardOut().size());
        assertEquals("Hello world!", result.getStandardOut().get(0));
    }

    @Test
    void withInput() throws IOException, InterruptedException {
        String inputString = "Hello World!\nHello another world!\n";
        InputStream inputStream = new ByteArrayInputStream(inputString.getBytes());
        ProcessCollectBuilder processCollectBuilder
                = new ProcessCollectBuilder("wc", "-l")
                .withInputStream(inputStream);
        ProcessCollect processCollect = processCollectBuilder.build();
        ProcessCollectResult result = processCollect.call();

        assertEquals(1, result.getStandardOut().size());
        assertEquals("2", result.getStandardOut().get(0));
    }

}
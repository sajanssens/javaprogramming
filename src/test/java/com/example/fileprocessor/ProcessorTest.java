package com.example.fileprocessor;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class ProcessorTest {

    private static final String filename = "dataRoot.txt";
    private static final String resource = "dataTestResourcePackage.txt";

    private Processor processor = new Processor();

    @Test
    public void assertThatResourceExists() {
        InputStream in = this.getClass().getResourceAsStream(resource);
        assertThat(in).isNotNull();
    }

    @Test
    public void testProcessFile() throws IOException {
        String s = processor.processFile(BufferedReader::readLine, filename);

        assertThat(s).startsWith("Lorem ipsum dolor sit amet");
        assertThat(s).endsWith("Sed a scelerisque mi.");
    }

    @Test
    public void testProcessResource() throws IOException {
        String s = processor.processResource(BufferedReader::readLine, resource);

        assertThat(s).startsWith("Lorem ipsum dolor sit amet");
        assertThat(s).endsWith("Sed a scelerisque mi.");
    }
}
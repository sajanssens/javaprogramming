package com.example.fileprocessor;

import java.io.*;

public class Processor {

    private static final String projectRoot = new File("").getAbsolutePath() + "/";

    public String processFile(BufferedReaderProcessor processor, String filename) throws IOException {
        try (BufferedReader b = new BufferedReader(new FileReader(projectRoot + filename))) {
            return processor.process(b);
        }
    }

    public String processResource(BufferedReaderProcessor processor, String filename) throws IOException {
        InputStream in = this.getClass().getResourceAsStream(filename);

        try (BufferedReader b = new BufferedReader(new InputStreamReader(in))) {
            return processor.process(b);
        }
    }

}

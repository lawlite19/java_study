package com.seu.study.io;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileIOTest {
    File file;

    @Before
    public void init() throws IOException {
        file = new File("/tmp/test.txt");
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
    }

    @Test
    public void testFileIO() throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("hello world");
        fileWriter.flush();

    }
}

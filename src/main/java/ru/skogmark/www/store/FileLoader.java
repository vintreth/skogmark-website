package ru.skogmark.www.store;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author svip
 *         2016-07-15
 */
public class FileLoader {

    private String filePath;

    public FileLoader(String filePath) {
        this.filePath = filePath;
    }

    public String load() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        StringBuilder builder = new StringBuilder();
        String line;
        while (null != (line = reader.readLine())) {
            builder.append(line);
        }

        return builder.toString();
    }

}

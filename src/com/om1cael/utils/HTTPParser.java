package com.om1cael.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class HTTPParser {
    public String getResourceContent(Path resourcePath) {
        try(BufferedReader reader = new BufferedReader(Files.newBufferedReader(resourcePath))) {
            String line;
            StringBuilder content = new StringBuilder();

            while((line = reader.readLine()) != null) {
                content.append(line).append("\r\n");;
            }

            return content.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getContentLength(Path resourcePath) {
        return getResourceContent(resourcePath).getBytes(StandardCharsets.UTF_8).length;
    }
}

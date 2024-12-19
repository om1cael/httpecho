    package com.om1cael.utils;

    import java.io.*;
    import java.nio.charset.StandardCharsets;
    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.util.Arrays;
    import java.util.Base64;

    public class HTTPParser {
        public String getResourceContent(Path resourcePath) {
            try(BufferedReader reader = new BufferedReader(Files.newBufferedReader(resourcePath))) {
                return handleText(reader);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public byte[] getImageContent(Path resourcePath) {
            try {
                return Files.readAllBytes(resourcePath);
            } catch (IOException e) {
                System.err.println("It was not possible to parse the image");
            }

            return null;
        }

        public String handleText(BufferedReader reader) {
            try {
                String line;
                StringBuilder content = new StringBuilder();

                while((line = reader.readLine()) != null) {
                    content.append(line).append("\r\n");;
                }

                return content.toString();
            } catch (IOException e) {
                System.err.println("It was not possible to parse the text");
            }

            return "";
        }

        public int getContentLength(Path resourcePath, boolean isImage) {
            if(!isImage) return getResourceContent(resourcePath).getBytes(StandardCharsets.UTF_8).length;
            return getImageContent(resourcePath).length;
        }
    }

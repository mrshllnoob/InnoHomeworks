package inno.l5.homework.classes.impl;

import inno.l5.homework.classes.ResourceReader;

import java.io.*;

public class LocalTextFilesReader implements ResourceReader {
    private final String link;

    public LocalTextFilesReader(String link) {
        this.link=link;
    }

    @Override
    public String getTextFromResource() {
        StringBuilder result = new StringBuilder();
        File source = new File(link);
        if (!source.exists())
            return " ";
        try (FileReader fin = new FileReader(source);
             BufferedReader in = new BufferedReader(fin)) {
            String line;
            while ((line = in.readLine()) != null)
                result.append(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
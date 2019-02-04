package inno.l5.homework.classes.impl;

import inno.l5.homework.classes.ResourceReader;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class HttpTextReader implements ResourceReader {

    private static String link;

    public HttpTextReader(String link) {
        this.link = link;
    }

    @Override
    public String getTextFromResource() {
        StringBuilder result = new StringBuilder();
        try (Scanner scanner = new Scanner(new URL(link).openStream(),
                StandardCharsets.UTF_8.toString())) {
            scanner.useDelimiter("\\A");
            while (scanner.hasNext())
                result.append(scanner.nextLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}

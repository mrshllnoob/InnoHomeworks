package inno.l5.homework.readers.impl;

import inno.l5.homework.readers.ResourceReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * Класс при помощи которого можно читать текстовые файлы
 * с FTP-серверов.
 *
 * @author Tsapin Anton
 */
public class FtpTextReader implements ResourceReader {

    private final String link;

    public FtpTextReader(String link) {
        this.link = link;
    }

    @Override
    public String getTextFromResource() {
        StringBuilder result = new StringBuilder();
        try(Scanner scanner = new Scanner(new URL(link).openStream())) {
            while(scanner.hasNext())
                result.append(scanner.nextLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}

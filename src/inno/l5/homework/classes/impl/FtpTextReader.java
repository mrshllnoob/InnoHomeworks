package inno.l5.homework.classes.impl;

import inno.l5.homework.classes.ResourceReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

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
        URLConnection conn = null;
        StringBuilder result = new StringBuilder();
        try {
            conn = new URL(link).openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try(InputStream in = conn.getInputStream();
            InputStreamReader is = new InputStreamReader(in);
            BufferedReader reader = new BufferedReader(is)) {
            String line;
            while((line = reader.readLine()) != null)
                result.append(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}

package inno.l5.homework;

import inno.l5.homework.readers.impl.FtpTextReader;
import inno.l5.homework.readers.impl.HttpTextReader;
import inno.l5.homework.readers.impl.LocalTextFilesReader;
import inno.l5.homework.readers.ResourceReader;

/**
 * Класс создающий классы читающие различные текстовые ресурсы
 * в зависимости от переданной ссылки.
 * @author Tsapin Anton
 */
public class ReadersCreator {

    public ResourceReader createReaderOnLink(String link) {
        if ((link.contains("http://") && (link.startsWith("http://")))
                || (link.contains("https://") && link.startsWith("https://"))) {
            return new HttpTextReader(link);
        }
        if (link.contains("ftp://") && link.startsWith("ftp://")) {
            return new FtpTextReader(link);
        }
        else {
            return new LocalTextFilesReader(link);
        }
    }

}

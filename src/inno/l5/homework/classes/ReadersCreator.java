package inno.l5.homework.classes;

import inno.l5.homework.classes.impl.FtpTextReader;
import inno.l5.homework.classes.impl.HttpTextReader;
import inno.l5.homework.classes.impl.LocalTextFilesReader;

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

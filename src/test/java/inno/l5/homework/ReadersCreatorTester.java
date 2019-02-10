package inno.l5.homework;

import inno.l5.homework.readers.impl.FtpTextReader;
import inno.l5.homework.readers.impl.HttpTextReader;
import inno.l5.homework.readers.impl.LocalTextFilesReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ReadersCreatorTester {

    private ReadersCreator rc;

    @BeforeEach
    void setUp() {
        rc = new ReadersCreator();
    }

    @Test
    void testOnReturnHttpReader() {
        Assertions.assertSame(new HttpTextReader("http://").getClass(),
                                        rc.createReaderOnLink("http://").getClass());
    }

    @Test
    void testOnReturnFtpReader() {
        Assertions.assertSame(new FtpTextReader("ftp://").getClass(),
                                        rc.createReaderOnLink("ftp://").getClass());
    }

    @Test
    void testOnReturnLocalFileReader() {
        Assertions.assertSame(new LocalTextFilesReader("localhost--http://ftp://").getClass()
                                        , rc.createReaderOnLink("localhost--http://ftp://").getClass());
    }

}

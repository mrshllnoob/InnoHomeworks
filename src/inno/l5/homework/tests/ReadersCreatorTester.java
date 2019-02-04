package inno.l5.homework.tests;

import inno.l5.homework.classes.ReadersCreator;
import inno.l5.homework.classes.impl.FtpTextReader;
import inno.l5.homework.classes.impl.HttpTextReader;
import inno.l5.homework.classes.impl.LocalTextFilesReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;


public class ReadersCreatorTester {

    private ReadersCreator rc;

    @BeforeEach
    void setUp() {
        rc = Mockito.mock(ReadersCreator.class);
    }

    @Test
    void testOnReturnHttpReader() {
        Mockito.when(rc.createReaderOnLink("http://" + Mockito.anyString()))
                .thenReturn(new HttpTextReader("http://" + Matchers.anyString()));
        Assertions.assertSame(new HttpTextReader("http://").getClass(),
                                        rc.createReaderOnLink("http://").getClass());
    }

    @Test
    void testOnReturnFtpReader() {
        Mockito.when(rc.createReaderOnLink("ftp://" + Mockito.anyString()))
                .thenReturn(new FtpTextReader("ftp://" + Mockito.anyString()));
        Assertions.assertSame(new FtpTextReader("ftp://").getClass(),
                                        rc.createReaderOnLink("ftp://").getClass());
    }

    @Test
    void testOnReturnLocalFileReader() {
        Mockito.when(rc.createReaderOnLink("localhost--http://ftp://" + Mockito.anyString()))
                .thenReturn(new LocalTextFilesReader("localhost--http://ftp://" + Mockito.anyString()));
        Assertions.assertSame(new LocalTextFilesReader("localhost--http://ftp://").getClass()
                                        , rc.createReaderOnLink("localhost--http://ftp://").getClass());
    }

}

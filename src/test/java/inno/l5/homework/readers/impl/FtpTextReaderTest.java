package inno.l5.homework.readers.impl;

import mockit.Mock;
import mockit.MockUp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sun.net.www.protocol.https.HttpsURLConnectionImpl;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

class FtpTextReaderTest {

    @Test
    void getTextFromResource() {
        String src = "source";
        new MockUp<URL>() {
            @Mock
            public URLConnection openConnection() throws IllegalAccessException, InstantiationException {
                HttpsURLConnectionImpl mockedURL = HttpsURLConnectionImpl.class.newInstance();
                return mockedURL;
            }
            @Mock
            public final InputStream openStream() {
                return new ByteArrayInputStream(src.getBytes());
            }
        };

        ByteArrayInputStream bais = Mockito.mock(ByteArrayInputStream.class);
        Mockito.when(bais.read(src.getBytes(), 0, src.getBytes().length))
                .thenReturn(src.getBytes().length);
        Assertions.assertEquals(src, new FtpTextReader("ftp://").getTextFromResource());
    }
}
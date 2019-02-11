package inno.l5.homework.readers.impl;

import mockit.Mock;
import mockit.MockUp;
import mockit.integration.junit4.JMockit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sun.net.www.protocol.https.HttpsURLConnectionImpl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class HttpTextReaderTest {

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
        Assertions.assertEquals(src, new HttpTextReader("http://").getTextFromResource());
    }
}
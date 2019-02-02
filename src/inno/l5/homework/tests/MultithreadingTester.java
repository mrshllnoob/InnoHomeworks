package inno.l5.homework.tests;

import inno.l5.homework.ResourceReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;


class MultithreadingTester {

    private ResourceReader resourceReader;

    @BeforeEach
    void setUp() {
        resourceReader = Mockito.mock(ResourceReader.class);
    }

    @Test
    void getWordsFromFileTestOnNullParam() {
        Mockito.when(resourceReader.getWordsFromFile(null)).thenReturn(new String[]{""});
    }

    @Test
    void getWordsFromFileTestOnEmptyParam() {
        Mockito.when(resourceReader.getWordsFromFile("")).thenReturn(new String[]{""});
    }

    @Test
    void getWordsFromFileTestIOException() {
        Mockito.when(resourceReader.getWordsFromFile("/dev/null/")).thenThrow(IOException.class);
    }

    @Test
    void buildArrayOfSourcesPathsTestOnNullParam() {
        Mockito.when(resourceReader.buildArrayOfSourcesPaths(null)).thenReturn(new String[] {""});
    }

    @Test
    void buildArrayOfSourcesPathsTestOnEmptyParam() {
        Mockito.when(resourceReader.buildArrayOfSourcesPaths("")).thenReturn(new String[] {""});
    }

    @Test
    void storeMatchesTestOnNotReturningNull() {
        Mockito.when(resourceReader.storeMatches(Mockito.anyString(), Mockito.any(String[].class)))
                .thenReturn(Mockito.isNotNull(StringBuilder.class));
    }

    @Test
    void parseResourceTestOnEmptyURL() {
        Mockito.when(resourceReader.parseResource("")).thenThrow(FileNotFoundException.class);
    }

    @Test
    void parseResourceTestOnWrongFtpURL() {
        Mockito.when(resourceReader.parseResource("ftp://" + Mockito.anyString())).thenThrow(IOException.class);
    }

    @Test
    void parseResourceTestOnWrongHttpURL() {
        Mockito.when(resourceReader.parseResource("http://??????"))
                .thenThrow(MalformedURLException.class);
    }

    @Test
    void parseResourceTestOnWrongHttpsURL() {
        Mockito.when(resourceReader.parseResource("https://" + Mockito.anyString()))
                .thenThrow(MalformedURLException.class);
    }

    @Test
    void parseResourceTestOnNotReturningNull() {
        Mockito.when(resourceReader.parseResource(Mockito.anyString()))
                .thenReturn(Mockito.isNotNull(String.class));
    }

}

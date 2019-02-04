package inno.l5.homework.tests;

import inno.l5.homework.classes.ResultWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ResultWriterTester {

    private ResultWriter rwr;

    @BeforeEach
    void setUp() {
        rwr = Mockito.mock(ResultWriter.class);
    }

    @Test
    void testWriteListIntoFileThrowsIOException() {

        Assertions.assertThrows(FileNotFoundException.class, ()->new ResultWriter("").writeListIntoFile(new ArrayList<>()));
    }

}

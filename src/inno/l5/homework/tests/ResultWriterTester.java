package inno.l5.homework.tests;

import inno.l5.homework.classes.ResultWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ResultWriterTester {

    private ResultWriter rwr;

    @BeforeEach
    void setUp() {
        rwr = new ResultWriter(null);
    }

    @Test
    void testWriteListIntoFileOnWrongArg() {
        rwr.setPath(null);
        Assertions.assertDoesNotThrow(()->rwr.writeListIntoFile(new ArrayList<>()));
        rwr.setPath("/wrong/wrong.txt");
        Assertions.assertDoesNotThrow(()->rwr.writeListIntoFile(new ArrayList<>()));
    }

}

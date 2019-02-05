package inno.l5.homework.tests;

import inno.l5.homework.classes.Utilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UtilitiesClassTester {

    private Utilities util;

    @BeforeEach
    void setUp() {
        util = new Utilities();
    }

    @Test
    void buildArrayOfSrcPathsTestOnNotReturningNull() {
        Assertions.assertNotNull(util.buildArrayOfSourcesPaths(null));
        Assertions.assertNotNull(util.buildArrayOfSourcesPaths(""));
        Assertions.assertNotNull(util.buildArrayOfSourcesPaths("wrong path"));
    }

    @Test
    void getWordsFromFileTestOnNotReturningNull() {
        Assertions.assertNotNull(util.getWordsFromFile(null));
        Assertions.assertNotNull(util.getWordsFromFile(""));
        Assertions.assertNotNull(util.getWordsFromFile("wrong.txt"));
    }
}

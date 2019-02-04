package inno.l5.homework.tests;

import inno.l5.homework.classes.Utilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UtilitiesClassTester {

    private Utilities util;

    @BeforeEach
    void setUp() {
        util = Mockito.mock(Utilities.class);
    }

    @Test
    void buildArrayOfSrcTestOnNotReturningNull() {
        Mockito.when(util.buildArrayOfSourcesPaths(Mockito.anyString()))
                .thenReturn(new String[] {});
        Assertions.assertNotNull(util.buildArrayOfSourcesPaths(null));
    }

    @Test
    void getWordsTestOnNotReturningNull() {
        Mockito.when(util.getWordsFromFile(Mockito.anyString()))
                .thenReturn(new String[] {""});
        Assertions.assertNotNull(util.getWordsFromFile(""));
    }

}

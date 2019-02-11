package inno.l5.homework;

import mockit.Mock;
import mockit.MockUp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.*;

class MultithreadingMatchesCollectorTest {

    @Test
    void getOccurenciesUsingCallable() {

    }

    @Test
    void storeMatches() {
        String expected = " not some 1" + System.lineSeparator();
        String result = new MultithreadingMatchesCollector().
                storeMatches("!some! not some 1! anothersome", new String[] {"some", "some1", "some2"})
                .toString();
        Assertions.assertEquals(expected, result);
    }

    @Test
    void collectionToString() {
        List<StringBuilder> sbList = new ArrayList<>();
        sbList.add(new StringBuilder("some"));
        sbList.add(new StringBuilder("string"));
        ArrayList<String> expected = new ArrayList<>();
        expected.add("some");
        expected.add("string");
        Assertions.assertEquals(expected.get(0), new MultithreadingMatchesCollector()
                                                        .collectionToString(sbList).get(0));
        Assertions.assertEquals(expected.get(1), new MultithreadingMatchesCollector()
                .collectionToString(sbList).get(1));
    }
}
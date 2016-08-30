package ru.sbt.streams;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class StreamsImplTest {

    private List<String> checkList;

    @Before
    public void setup() {
        checkList = new ArrayList<>();
        checkList.add("one");
        checkList.add("two");
        checkList.add("three");
        checkList.add("four");
    }

    @Test
    public void filterTest() {
        StreamsImpl testStream = StreamsImpl.of(checkList).filter(s -> s.startsWith("t"));
        assertEquals(true, testStream.toList().contains("two"));
    }

    @Test
    public void filterTestInvert() {
        StreamsImpl testStream = StreamsImpl.of(checkList).filter(s -> s.startsWith("t"));
        assertEquals(false, testStream.toList().contains("one"));
    }

    @Test
    public void tolistTest() {
        List<String> testList = StreamsImpl.of(checkList).toList();
        assertEquals(checkList, testList);
    }

    @Test
    public void toMapTest() {
        Map<Object,Object> testMap = StreamsImpl.of(checkList).toMap(s -> s, s -> s);
        assertEquals(testMap.get("two"), "two");
    }

    @Test
    public void findItemTest() {
        String resultItem = StreamsImpl.of(checkList).findItem("one");
        assertEquals(resultItem, "one");
    }

    @Test
    public void transformTest() {
        List<String> testList = StreamsImpl.of(checkList).transform(s -> s.concat("-d")).toList();
        assertEquals(testList.get(0), "one-d");
    }
}
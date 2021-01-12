import org.junit.jupiter.api.*;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomArrayListTest {

    public static final Logger logger = Logger.getLogger("log.txt");
    private static final CustomArrayList<String> testList = new CustomArrayList<>();
    private static final CustomArrayList<String> addList = new CustomArrayList<>();

    @BeforeAll
    static void beforeAll() {
        logger.info("Init start objects");
        addList.add("test");
    }


    @BeforeEach
    void before() {
        logger.info("start test");
        testList.add("one");
        testList.add("");
        testList.add(null);
    }

    @AfterEach
    void after() {
        testList.clear();
        logger.info("end test");
    }

    //@Order(1)
    @Test
    void size() {
        logger.info("Test size()");
        assertEquals(3, testList.size());
        assertArrayEquals(new String[]{"one", "", null}, testList.toArray());
    }

    //@Order(2)
    @Test
    void add() {
        logger.info("Test add()");
        assertEquals(true, testList.add("test"));
        assertArrayEquals(new String[]{"one", "", null, "test"}, testList.toArray());
    }

    @Test
    void addByIndex() {
        logger.info("Test add(index, Object)");
        testList.add(0, "test");
        assertArrayEquals(new String[]{"test", "one", "", null}, testList.toArray());
    }

    //@Order(3)
    @Test
    void addAll() {
        logger.info("Test addAll(Collection)");
        assertEquals(true, testList.addAll(addList));
        assertArrayEquals(new String[]{"one", "", null, "test"}, testList.toArray());
    }

    @Disabled
    @Test
    void addAllByIndex() {
        logger.info("Test addAll(CollectionByIndex)");
        assertEquals(true, testList.addAll(1, addList));
        assertEquals(false, testList.addAll(-1, addList));
        assertEquals(false, testList.addAll(10, addList));
        assertArrayEquals(new String[]{"one", "test", "", null}, testList.toArray());
    }

    @Test
    void contains() {
        logger.info("Test contains()");
        assertEquals(true, testList.contains("one"));
        assertEquals(true, testList.contains(""));
        assertEquals(true, testList.contains(null));
        assertEquals(false, testList.contains("two"));
    }

    @Test
    void containsAll() {
        logger.info("Test containsAll()");
        assertEquals(true, testList.containsAll(testList));
        assertEquals(false, testList.containsAll(addList));
    }

    @Test
    void clear() {
        logger.info("Test clear()");
        testList.clear();
        assertEquals(0, testList.size());
    }

    @Test
    void get() {
        logger.info("Test get()");
        assertEquals("one", testList.get(0));
        assertEquals(null, testList.get(2));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> testList.get(10));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> testList.get(-1));
    }

    @Test
    void isEmpty() {
        logger.info("Test isEmpty()");
        assertEquals(false, testList.isEmpty());
    }

    @Test
    void lastIndexOf() {
        logger.info("Test lastIndexOf()");
        assertEquals(0, testList.lastIndexOf("one"));
        assertEquals(2, testList.lastIndexOf(null));
        assertEquals(-1, testList.lastIndexOf("non"));
    }

    @Test
    void removeByIndex() {
        logger.info("Test removeByIndex()");
        assertEquals("one", testList.remove(0));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> testList.remove(10));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> testList.remove(-1));
    }

    @Test
    void removeByObject() {
        logger.info("Test removeByObject()");
        assertEquals(true, testList.remove("one"));
        assertEquals(true, testList.remove(null));
        assertEquals(false, testList.remove("two"));
    }

    @Test
    void removeAll() {
        logger.info("Test removeByObject()");
        assertEquals(true, testList.removeAll(testList));
        assertEquals(0, testList.size());
    }

    @Test
    void set() {
        logger.info("Test set()");
        assertEquals("one", testList.set(0, "1"));
        assertEquals(null, testList.set(2, "3"));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> testList.get(10));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> testList.get(-1));
    }

    @Test
    void sort() {
        logger.info("Test sort()");
        testList.set(2, "two");
        testList.sort(String::compareTo);
        assertArrayEquals(new String[]{"", "one", "two"}, testList.toArray());
    }

    @Test
    void spliterator() {
        logger.info("Test spliterator() - not impl");
        assertEquals(null, testList.spliterator());
    }
    @Test
    void listIterator() {
        logger.info("Test listIterator() - not impl");
        assertEquals(null, testList.listIterator());
    }

    @Test
    void listIteratorByIndex() {
        logger.info("Test listIterator() - not impl");
        assertEquals(null, testList.listIterator(0));
    }

    @Test
    void retainAll() {
        logger.info("Test retainAll() - not impl");
        assertEquals(false, testList.retainAll(addList));
    }

    @Test
    void toArray() {
        logger.info("Test toArray()");
        assertArrayEquals(new String[]{"one","",null}, testList.toArray());
    }

    @Test
    void subList() {
        logger.info("Test subList()");
        assertArrayEquals(new String[]{"one",""}, testList.subList(0,1).toArray());
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> testList.subList(-1,10));
    }


    @Test
    void hasNext() {
        logger.info("Test Iterator.hasNext()");
        assertEquals(true, testList.iterator().hasNext());
    }

    @Test
    void next() {
        logger.info("Test Iterator.next()");
        assertEquals("one", testList.iterator().next());
    }

}





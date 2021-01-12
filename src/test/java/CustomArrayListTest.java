import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.Date;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomArrayListTest {

    public static final Logger logger = Logger.getLogger("log.txt");
    private static final CustomArrayList<String> testList = new CustomArrayList<>();
    private static final CustomArrayList<String> addList = new CustomArrayList<>();
    private static final CustomArrayList<String> insList = new CustomArrayList<>();
    private static final CustomArrayList<String> chkList = new CustomArrayList<>();


    @BeforeEach
    void init() {
        logger.info(new Date().toLocaleString());
        testList = new CustomArrayList<>()(Arrays.asList("test1", "test2"));
    }

    @Order(1)
    @Test
    void startSize() {
        assertEquals(0, testList.size());
    }



    @Order(2)
    @Test
    void add() {
        testList.add("1");
        testList.add("2");
        testList.add("3");
        testList.add("");
        testList.add(null);
        assertEquals(5, testList.size());
    }

    @Order(3)
    @Test
    void addAll() {
        addList.add("4");
        addList.add("5");
        addList.add("6");
        addList.add("");
        addList.add(null);
        testList.addAll(addList);
        assertEquals(10, testList.size());
    }

    @Order(4)
    @Test
    void addAllIndex() {
        insList.add("");
        insList.add(null);
        insList.add("---");
        testList.addAll(5, insList);
        assertEquals(13, testList.size());
    }

    @Order(5)
    @Test
    void compare_1() {
        chkList.add("1");
        chkList.add("2");
        chkList.add("3");
        chkList.add("");
        chkList.add(null);
        chkList.add("");
        chkList.add(null);
        chkList.add("---");
        chkList.add("4");
        chkList.add("5");
        chkList.add("6");
        chkList.add("");
        chkList.add(null);
        assertArrayEquals(chkList.toArray(), testList.toArray());
    }


    @Order(6)
    @Test
    void compareAfterAdd() {
        String[] s = new String[]{"1", "2", "3"};
        String[] a = (String[])testList.toArray();
        assertEquals(s, a);
    }

    @Order(6)
    @Test
    void set() {
        assertEquals("1", testList.set(0, "set_1"));
        assertEquals("3", testList.set(2, "set_3"));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> testList.set(10, "set_10"));
        assertThrows(ArrayIndexOutOfBoundsException.class, ()-> testList.set(-1, "set_-1"));
    }

    @Order(6)
    @Test
    void get() {
        assertEquals("set_1", testList.get(0));
        assertEquals("set_3", testList.get(2));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> testList.get(10));
        assertThrows(ArrayIndexOutOfBoundsException.class, ()-> testList.get(-1));
    }

    @Order(6)
    @Test
    void indexOf() {
        assertEquals(0, testList.indexOf("set_1"));
        assertEquals(4, testList.indexOf(null));
        assertEquals(-1, testList.indexOf("ten"));
        assertEquals(3, testList.indexOf(""));
    }

    @Order(7)
    @Test
    void contains() {
        assertEquals(true, testList.contains("set_1"));
        assertEquals(false, testList.contains(null));
        assertEquals(false, testList.contains("ten"));
        assertEquals(false, testList.contains(""));
    }





}
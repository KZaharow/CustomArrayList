import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomArrayListTest {

    private final CustomArrayList<String> list = new CustomArrayList<>();

    @Test
    void add() {
        list.add("one");
        list.add("two");
        list.add("three");
    }
}
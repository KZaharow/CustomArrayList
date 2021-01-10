import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> list = new CustomArrayList<>();
        list.add("one");
        list.add("two");

        List<String> s = new ArrayList<>();
        s.add("-1");
        s.add("-2");
        s.add("-3");

        list.addAll(s);
/*
        list.add(2, "X");
        List<String> lstrSub = list.subList(1, 2);
*/
        List<String> s1 = new ArrayList<>();
        s1.add("ttt");
        s1.add("ttt");
        list.addAll(3, s1);

    }
}

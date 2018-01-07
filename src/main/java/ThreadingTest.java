import threadingtest.ListNumber;

import java.util.ArrayList;
import java.util.List;

public class ThreadingTest {
    public static void main(String[] args) {
        List<Integer> lama = new ArrayList<>();
        lama.add(3);
        lama.add(6);
        lama.add(1);
        lama.add(8);
        lama.add(5);
        lama.add(11);

        ListNumber listNumber = new ListNumber(lama);

        System.out.println(listNumber.toString());
        listNumber = listNumber.nextState();
        System.out.println(listNumber.toString());
        listNumber = listNumber.nextState();
        System.out.println(listNumber.toString());
        listNumber = listNumber.nextState();
        System.out.println(listNumber.toString());
        listNumber = listNumber.nextState();
        System.out.println(listNumber.toString());
    }
}

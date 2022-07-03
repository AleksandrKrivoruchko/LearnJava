import java.util.*;

class Lesson31 {
    public static void main(String[] args) {
        // int day = 29;
        // int month = 9;
        // int year = 1990;
        // Integer[] date = {day, month, year};
        // List<Integer> d = Arrays.asList(date);
        // System.out.println(d);
        // StringBuilder day = new StringBuilder("09");
        // StringBuilder month = new StringBuilder("06");
        // StringBuilder year = new StringBuilder("1998");
        // StringBuilder[] date = { day, month, year };
        // List<StringBuilder> d = Arrays.asList(date);
        // System.out.println(d);
        // date[1] = new StringBuilder("10");
        // System.out.println(d);
        // List<Character> list1 = List.of('S', 'e', 'r', 'g', 'e', 'y');
        // List<Character> list2 = List.copyOf(list1);
        // // list1.remove(5);
        // System.out.println(list1);
        // System.out.println(list2);

        List<Integer> list = List.of(1, 12, 123, 1234, 12345);
        for (int item : list) {
            System.out.println(item);
        }
        Iterator<Integer> col = list.iterator();
        while (col.hasNext()) {
            System.out.println(col.next());
            // col.remove();
            // col.next();
        }
    }
}
//На вход некоторому исполнителю подаётся два числа (a, b). У исполнителя есть две команды
//        - команда 1 (к1): увеличить в с раза, а умножается на c
//        - команда 2 (к2): увеличить на d ( +2 ), к a прибавляется d
//        написать программу, которая выдаёт набор команд, позволяющий число a превратить в число b или сообщить, что это невозможно
//        Пример 1: а = 1, b = 7, c = 2, d = 1
//        ответ: к1, к1, к1, к1, к1, к1 или к1, к2, к1, к1, к1 или к1, к1, к2, к1.
//        Пример 2: а = 11, b = 7, c = 2, d = 1
//        ответ: нет решения.
//        *Подумать над тем, как сделать минимальное количество команд
import java.util.*;

class Seminar5 {
    public static void main(String[] args) {
        int a = 1;
        int b = 7;
        int c = 2;
        int d = 1;
        Map<Integer, String> resH = FindSolution(a,b,c,d);
        if(resH.isEmpty()) {
            System.out.printf("For numbers a = %d и b = %d solution isn't find\n", a, b);
        } else {
            for(var item : resH.entrySet()) {
                System.out.printf("%d  %s\n", item.getKey(), item.getValue());
            }
        }
    }

    static Map<Integer, String> FindSolution(int a, int b, int c, int d) {
        Queue<Integer> qll = new LinkedList<>();
        Queue<String> ql = new LinkedList<>();
        Map<Integer, String> hm = new HashMap<>();
        qll.add(a);
        String str = "";
        ql.add(str);
        int tmp = a;
        String tmpStr = str;
        int count = 0;
        while (!qll.isEmpty()) {
            tmp = qll.poll();
            tmpStr = ql.poll();
            if(tmp == b) {
                count++;
                hm.put(count,tmpStr);
            }
            if (tmp > b) {
                continue;
            }
            qll.add(k1(tmp,c));
            ql.add(tmpStr + "k1 ");
            qll.add(k2(tmp,d));
            ql.add(tmpStr + "k2 ");
        }
        return hm;
    }

    static int k1(int x, int c) {
        return x * c;
    }
    static int k2(int x, int d) {
        return x + d;
    }
}
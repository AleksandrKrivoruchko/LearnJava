
// Задано уравнение вида q + w = e, q, w, e >= 0. Некоторые цифры могут быть заменены знаком вопроса, 
// например 2? + ?5 = 69. Требуется восстановить выражение до верного равенства.
//  Предложить хотя бы одно решение или сообщить, что его нет.
import java.util.List;
import java.util.LinkedList;;

public class Seminar6 {
    public static void main(String[] args) {
        String str = " 2? + ?5 = 69";
        FindSolution sol = new FindSolution(str);
        StringBuilder[] sb = sol.getSbExpr();
        for (var s : sb) {
            System.out.println(s);
        }
        var q = sol.getQuestion();
        for (var item : q) {
            for (var f : item) {
                System.out.printf("%s ", f);
            }
            System.out.println();
        }
        var n = sol.getNumbers();
        for (var item : n) {
            for (var f : item) {
                System.out.printf("%s ", f);
            }
            System.out.println();
        }
    }
}
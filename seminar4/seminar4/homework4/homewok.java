// Реализовать алгоритм перевода из инфиксной записи в постфиксную 
// для арифметического выражения.
// Вычислить запись если это возможно
// Написать программу вычисляющую значение сложного арифметического выражения. 
// Для простоты - выражение всегда вычисляемое
// Пример: (2^3 * (10 / (5 - 3)))^(Sin(Pi)) ответ - 1
package seminar4.homework4;

import java.util.ArrayDeque;

public class homewok {
    public static void main(String[] args) {
        String exp = "(2^3 * (10 / (5 - 3) ) )^(Sin(Pi ) )";
        System.out.println(CalculatingExpression(exp));
    }

    static String CalculatingExpression(String exp) {
        exp = exp.trim().replace(" ", "");
        exp = exp.toLowerCase();
        StringBuilder sb = new StringBuilder(exp);
        System.out.println(sb.capacity());
        ArrayDeque<Double> dqDouble = new ArrayDeque<>();
        ArrayDeque<String> dqChar = new ArrayDeque<>();

        return sb.toString();
    }

    static double Calculate(ArrayDeque<String> dqChar, ArrayDeque<Double> dqDouble) {
        double res = 0;
        switch (dqChar.pop()) {
            case "*":
                res = dqDouble.pop() * dqDouble.pop();
                break;
            case "/":
                res = dqDouble.pop() / dqDouble.pop();
                break;
            case "+":
                res = dqDouble.pop() + dqDouble.pop();
                break;
            case "-":
                res = dqDouble.pop() + dqDouble.pop();
                break;
            
        }
        return res;
    }
}

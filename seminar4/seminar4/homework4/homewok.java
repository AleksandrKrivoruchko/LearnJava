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
        StringBuilder sb = new StringBuilder(CalculatingExpression(exp));
        System.out.println(IsBracketsRight(sb));
        System.out.println(sb);
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

    static StringBuilder OpeningBrackets(StringBuilder sb, ArrayDeque<String> dqString, ArrayDeque<Double> dqDouble) {
        int endIndex = sb.indexOf(")");
        if (endIndex == -1) {

        }
        return sb;
    }

    static boolean IsBracketsRight(StringBuilder sb1) {
        StringBuilder sb = new StringBuilder(sb1);
        ArrayDeque<Character> tmp = new ArrayDeque<>();
        int bracketClose = 0;
        int bracketOpen = sb.indexOf(")", bracketClose);
        while (bracketClose != -1) {
            bracketClose = sb.indexOf(")");
            // System.out.println();
            if (bracketClose == -1)
                break;
            tmp.push(sb.charAt(bracketClose));
            // System.out.println(bracketClose);
            sb.deleteCharAt(bracketClose);
            bracketOpen = sb.lastIndexOf("(", bracketClose);
            if (bracketOpen == -1)
                break;
            if (!tmp.isEmpty()) {
                // System.out.println(bracketOpen);
                tmp.pop();
                sb.deleteCharAt(bracketOpen);
            }
        }
        // System.out.println(sb);
        return tmp.isEmpty() && sb.indexOf("(") == -1;
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
            case "^":
                res = Math.pow(dqDouble.pop(), dqDouble.pop());
                break;
            case "sin":
                res = Math.sin(dqDouble.pop());
        }
        return res;
    }
}

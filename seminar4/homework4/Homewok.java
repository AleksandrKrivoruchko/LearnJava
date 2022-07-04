
// Реализовать алгоритм перевода из инфиксной записи в постфиксную 
// для арифметического выражения.
// Вычислить запись если это возможно
// Написать программу вычисляющую значение сложного арифметического выражения. 
// Для простоты - выражение всегда вычисляемое
// Пример: (2^3 * (10 / (5 - 3)))^(Sin(Pi)) ответ 
import java.util.ArrayDeque;

public class Homewok {
    public static void main(String[] args) {
        String exp = "(2^3 * (10 / (5 - 3) ) )^(Sin(Pi ) )";
        ArrayDeque<Double> dqDouble = new ArrayDeque<>();
        ArrayDeque<String> dqString = new ArrayDeque<>();
        // System.out.println(CalculatingExpression(exp));
        // StringBuilder sb = new StringBuilder(CalculatingExpression(exp));
        // System.out.println(IsBracketsRight(sb));
        StringBuilder s = new StringBuilder("2+4^3+2");
        System.out.println(s.substring(2));
        String[] txt_double = ParsingExpression(s, dqString);
        for (int i = txt_double.length - 1; i >= 0; i--) {
            System.out.printf("%s ", txt_double[i]);
            // dqDouble.push(Double.parseDouble(txt_double[i]));
        }
        System.out.println();
        System.out.println(s);
        double tmp = 0;
        // while (!dqString.isEmpty()) {
        // // System.out.printf("%s ", dqString.pop());
        // tmp = Calculate(dqString, dqDouble);
        // if (!dqString.isEmpty()) {
        // dqDouble.push(tmp);
        // }
        // }
        // System.out.println(tmp);
        System.out.println(dqDouble.size());
        // System.out.printf("%f %s %f\n", dqDouble.pop(),
        // dqString.pop(), dqDouble.pop());
        // System.out.println(sb);
    }

    static String CalculatingExpression(String exp) {
        exp = exp.trim().replace(" ", "");
        exp = exp.toLowerCase();
        StringBuilder sb = new StringBuilder(exp);
        if (!IsBracketsRight(sb)) {
            return "В выражении лишняя скобка!";
        }
        System.out.println(sb.length());
        ArrayDeque<Double> dqDouble = new ArrayDeque<>();
        ArrayDeque<String> dqString = new ArrayDeque<>();

        // sb = OpeningBrackets(sb, dqString, dqDouble);

        return sb.toString();
    }

    static StringBuilder OpeningBrackets(StringBuilder sb, ArrayDeque<String> dqString, ArrayDeque<Double> dqDouble) {
        StringBuilder expTmp = new StringBuilder(sb);
        int endIndex = sb.indexOf(")");
        if (endIndex == -1) {

        }
        return expTmp;
    }

    static String[] ParsingExpression(StringBuilder sb, ArrayDeque<String> dqString) {
        String[] operations = { "-", "+", "/", "*", "^" };
        int len = operations.length;
        for (int i = 0; i < len; i++) {
            int tmpIndex = sb.indexOf(operations[i]);
            // System.out.println(tmpIndex);
            if (tmpIndex != -1) {
                dqString.push(sb.substring(tmpIndex, tmpIndex + 1));
                sb.replace(tmpIndex, tmpIndex + 1, ",");
            }
        }
        String[] txt = sb.toString().split(",");
        // System.out.println();
        return txt;
    }

    static boolean IsBracketsRight(StringBuilder sb1) {
        StringBuilder sb = new StringBuilder(sb1);
        ArrayDeque<Character> tmp = new ArrayDeque<>();
        int bracketClose = 0;
        int bracketOpen = sb.indexOf(")");
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
        System.out.println(sb);
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
        System.out.println(res);
        return res;
    }
}

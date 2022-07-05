
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

        StringBuilder s = new StringBuilder("-2+(2^3+2-8/2)");
        System.out.println(s);

        // ParsingExpression(s, dqString, dqDouble);
        System.out.println(CalculatingExpression(s.toString()));

        System.out.println();
        System.out.println(s);
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

        OpeningBrackets(sb, dqString, dqDouble);
        return sb.toString();
    }

    static StringBuilder OpeningBrackets(StringBuilder sb, ArrayDeque<String> dqString,
            ArrayDeque<Double> dqDouble) {

        int sIndex = sb.indexOf("(");
        if (sIndex == -1) {
            ParsingExpression(sb, dqString, dqDouble);
            return sb;
        } else {
            int eIndex = sb.lastIndexOf(")");
            StringBuilder txtSb = new StringBuilder(sb.substring(sIndex + 1, eIndex));
            sb.replace(sIndex, eIndex + 1, ParsingExpression(txtSb,
                    dqString, dqDouble).toString());
        }
        return sb;
    }

    static StringBuilder ParsingExpression(StringBuilder sb, ArrayDeque<String> dqString,
            ArrayDeque<Double> dqDouble) {
        String[] op = { "^", "/", "*", "-", "+" };
        int len = op.length;
        for (int i = 0; i < len; i++) {
            int tmpIndex = sb.indexOf(op[i]);
            // System.out.println(tmpIndex);
            if (tmpIndex != -1) {
                ReplaceOperation(tmpIndex, i, sb, op, dqString, dqDouble);
            }

        }
        return sb;
    }

    static void ReplaceOperation(int index, int i, StringBuilder sb,
            String[] op, ArrayDeque<String> dqString, ArrayDeque<Double> dqDouble) {
        int tmp = 0;
        while (index != -1) {
            tmp = index;
            StringArgs(index, op[i], sb, dqString, dqDouble);
            index = sb.indexOf(op[i]);
            if (index == 0 && op[i] == "-") {
                index = sb.indexOf(op[i], index + 1);
            }
            if (op[i] == "-" && !Character.isDigit(sb.charAt(index - 1))) {
                index = sb.indexOf(op[i], index + 1);
                if (tmp == index)
                    index = sb.indexOf(op[i], index + 1);
            }
        }
    }

    static void StringArgs(int index, String op, StringBuilder sb,
            ArrayDeque<String> dqString, ArrayDeque<Double> dqDouble) {

        int ks = index + 1;
        if (index == 0 && op == "-") {
            ks = 0;
        }

        int ke = ks;
        // String[] str = new String[2];
        // Character ch = sb.charAt(ke);
        while (Character.isDigit(sb.charAt(ke)) || sb.charAt(ke) == '.'
                || sb.charAt(ke) == '-') {
            if (sb.charAt(ke) == '-' && Character.isDigit(sb.charAt(ke + 1))
                    && ke != 0 && Character.isDigit(sb.charAt(ke - 1))) {
                break;
            }
            ke++;
            if (ke == sb.length())
                break;
        }
        String str = sb.substring(ks, ke);
        double value = Double.parseDouble(str);
        dqDouble.push(value);
        ks = index - 1;
        if (ks < 0) {
            ks = 0;
        }
        // System.out.println(ks);
        while (Character.isDigit(sb.charAt(ks)) || sb.charAt(ks) == '.'
                || sb.charAt(ks) == '-') {
            if (sb.charAt(ks) == '-' && Character.isDigit(sb.charAt(ks + 1))
                    && ks != 0 && Character.isDigit(sb.charAt(ks - 1))) {
                break;
            }
            ks--;
            if (ks < 0) {
                ks = 0;
                break;
            }
        }
        if (ks != 0)
            ks++;
        str = sb.substring(ks, index);
        System.out.println(str);
        if (str.isEmpty()) {
            if (!dqDouble.isEmpty()) {
                dqDouble.pop();
            }
            return;
        }
        value = Double.parseDouble(str);
        dqDouble.push(value);
        dqString.push(op);
        value = Calculate(dqString, dqDouble);
        sb.replace(ks, ke, Double.toString(value));
        System.out.println(sb);
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
                System.out.println("*");
                break;
            case "/":
                res = dqDouble.pop() / dqDouble.pop();
                System.out.println("/");
                break;
            case "+":
                res = dqDouble.pop() + dqDouble.pop();
                System.out.println("+");
                break;
            case "-":
                res = dqDouble.pop() - dqDouble.pop();
                System.out.println("-");
                break;
            case "^":
                res = Math.pow(dqDouble.pop(), dqDouble.pop());
                System.out.println("^");
                break;
            case "sin":
                res = Math.sin(dqDouble.pop());
                System.out.println("sin");
                break;
        }
        System.out.println(res);
        return res;
    }
}

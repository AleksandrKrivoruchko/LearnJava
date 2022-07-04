package homework4;

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
        System.out.println(s);
        StringArgs(1, "+", s, dqString, dqDouble);
        String[] txt_double = ParsingExpression(s, dqString, dqDouble);
        for (int i = txt_double.length - 1; i >= 0; i--) {
            System.out.printf("%s ", txt_double[i]);
            // dqDouble.push(Double.parseDouble(txt_double[i]));
        }
        System.out.println();
        System.out.println(s);
        double tmp = 0;
        System.out.println(dqString.size());
        while (!dqString.isEmpty()) {
            System.out.printf("%s ", dqString.pop());
            // tmp = Calculate(dqString, dqDouble);
            // if (!dqString.isEmpty()) {
            // dqDouble.push(tmp);
            // }
        }
        System.out.println();

        System.out.println(dqDouble.size());
        // System.out.println(dqDouble.pop() + "  " + dqDouble.pop());
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

    static String[] ParsingExpression(StringBuilder sb, ArrayDeque<String> dqString,
            ArrayDeque<Double> dqDouble) {
        String[] op = { "-", "+", "/", "*", "^" };
        int len = op.length;
        for (int i = 0; i < len; i++) {
            int tmpIndex = sb.indexOf(op[i]);
            // System.out.println(tmpIndex);
            if (tmpIndex != -1) {
                ReplaceOperation(tmpIndex, i, sb, op, dqString, dqDouble);
            }

        }
        String[] txt = sb.toString().split(",");
        // System.out.println();
        return txt;
    }

    static void ReplaceOperation(int index, int i, StringBuilder sb,
            String[] op, ArrayDeque<String> dqString, ArrayDeque<Double> dqDouble) {
        while (index != -1) {
            dqString.push(op[i]);
            sb.replace(index, index + 1, ",");
            index = sb.indexOf(op[i], index + 1);
        }
    }

    static void StringArgs(int index, String op, StringBuilder sb,
        ArrayDeque<String> dqString, ArrayDeque<Double> dqDouble) {
        int ks = index + 1;
        int ke = ks;
        // String[] str = new String[2];
        // Character ch = sb.charAt(ke);
        while(Character.isDigit(sb.charAt(ke))) {
            ke++;
        }
        String str = sb.substring(ks, ke);
        double value = Double.parseDouble(str);
        dqDouble.push(value);
        ks = index-1;
        // System.out.println(ks);
        while(Character.isDigit(sb.charAt(ks))) {
            ks--;
            if(ks < 0) {
                ks = 0;
                break;
            }
        }
        str = sb.substring(ks, index);
        // System.out.println(str);
        value = Double.parseDouble(str);
        dqDouble.push(value);
        dqString.push(op);
        value = Calculate(dqString, dqDouble);
        // System.out.println(value);
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

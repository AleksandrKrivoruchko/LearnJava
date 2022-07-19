
import java.util.LinkedList;

public class FindSolution {
    private String expression;
    private StringBuilder[] sbExpr;
    private LinkedList<LinkedList<Boolean>> question;
    private LinkedList<LinkedList<Integer>> numbers;
    private int[] args;

    public FindSolution(String str) {
        expression = str;
        sbExpr = parsingExpression(expression);
        question = new LinkedList<>();
        numbers = new LinkedList<>();
        parsingNumber();
        args = new int[3];
    }

    public StringBuilder[] getSbExpr() {
        return sbExpr;
    }

    public LinkedList<LinkedList<Boolean>> getQuestion() {
        return question;
    }

    public LinkedList<LinkedList<Integer>> getNumbers() {
        return numbers;
    }

    public int[] getArgs() {
        return args;
    }

    private void parsingNumber() {

        int tmp = 0;
        for (int i = 0; i < sbExpr.length; i++) {
            LinkedList<Integer> num = new LinkedList<>();
            LinkedList<Boolean> quest = new LinkedList<>();
            for (int j = 0; j < sbExpr[i].length(); j++) {
                tmp = getDigit(sbExpr[i].charAt(j));
                if (tmp < 0) {
                    quest.addFirst(true);
                    num.addFirst(0);
                } else {
                    quest.addFirst(false);
                    num.addFirst(tmp);
                }
            }
            question.add(quest);
            numbers.add(num);
        }
    }

    public boolean findSolution() {
        if (!isSolution(sbExpr)) {
            return false;
        }
        int n = 0;
        while (!numbers.get(0).isEmpty()) {
            for (int i = 0; i < numbers.size(); i++) {
                args[i] += numbers.get(i).pop() * powOfTen(n);
            }
            calcExpr(args, n);
            n++;
        }
        return true;
    }

    private void calcExpr(int[] args, int n) {
        if (isResult(args)) {
            return;
        }
        int boolCount = 0;
        boolean[] temp = new boolean[3];
        for (int i = 0; i < question.get(n).size(); i++) {
            if (question.get(n).get(i)) {
                boolCount++;
                temp[i] = true;
            }
        }
        oneCalc(args, temp, n);

    }

    private void oneCalc(int[] args, boolean[] flags, int n) {
        for (int i = 1; i < 10; i++) {
            for (int j = 0; j < flags.length; j++) {
                if (flags[j]) {
                    replaceNumber(args, j, n, i);
                }

            }
            if (isResult(args)) {
                return;
            }
        }
    }

    private void replaceNumber(int[] args, int j, int n, int i) {
        if (n == 0) {
            args[j] = i;
            return;
        }
        int tmp = args[j] % powOfTen(n);
        args[j] = tmp + i * powOfTen(n);
    }

    private boolean isResult(int[] args) {
        return args[0] + args[1] == args[2];
    }

    private boolean isSolution(StringBuilder[] strArr) {
        int a = strArr[0].length();
        int b = strArr[1].length();
        int c = strArr[2].length();
        return a <= c && b <= c;
    }

    private int getDigit(char ch) {
        return switch (ch) {
            case '0' -> 0;
            case '1' -> 1;
            case '2' -> 2;
            case '3' -> 3;
            case '4' -> 4;
            case '5' -> 5;
            case '6' -> 6;
            case '7' -> 7;
            case '8' -> 8;
            case '9' -> 9;
            default -> -1;
        };
    }

    private char getChar(int x) {
        return switch (x) {
            case 0 -> '0';
            case 1 -> '1';
            case 2 -> '2';
            case 3 -> '3';
            case 4 -> '4';
            case 5 -> '5';
            case 6 -> '6';
            case 7 -> '7';
            case 8 -> '8';
            case 9 -> '9';
            default -> '?';
        };
    }

    private int powOfTen(int n) {
        int res = 1;
        for (int i = 0; i < n; i++) {
            res *= 10;
        }
        return res;
    }

    private StringBuilder[] parsingExpression(String str) {
        str = str.trim().replace(" ", "");
        StringBuilder[] resStr = new StringBuilder[3];
        StringBuilder temp = new StringBuilder();
        int j = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '+' || str.charAt(i) == '=') {
                resStr[j] = temp;
                j++;
                temp = new StringBuilder();
                continue;
            }
            temp.append(str.charAt(i));
        }
        resStr[j] = temp;
        return resStr;
    }
}

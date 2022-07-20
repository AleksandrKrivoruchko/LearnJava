
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
        if (!isSolution()) {
            return false;
        }
        int n = 0;
        while (!numbers.get(0).isEmpty()) {
            for (int i = 0; i < numbers.size(); i++) {
                args[i] += numbers.get(i).pop() * powOfTen(n);
            }
            calcExpr(n);
            n++;
        }
        return true;
    }

    private void calcExpr(int n) {
        if (isResult(n)) {
            return;
        }
        // int boolCount = 0;
        boolean[] temp = new boolean[3];
        for (int i = 0; i < question.size(); i++) {
            if (question.get(i).get(n)) {
                // boolCount++;
                temp[i] = true;
            }
            // System.out.printf("")
        }
        oneCalc(temp, n);

    }

    private void oneCalc(boolean[] flags, int n) {
        int m = 10;
        if (question.get(2).size() > n + 1) {
            if (question.get(2).get(n + 1)) {
                m = 19;
                flags[2] = true;
            }
        }
        for (int i = 0; i < 10; i++) {
            if (flags[0]) {
                replaceNumber(0, n, i);
            }
            for (int j = 0; j < 10; j++) {
                if (flags[1]) {
                    replaceNumber(1, n, j);
                }
                for (int k = 0; k < m; k++) {
                    if (flags[2]) {
                        replaceNumber(2, n, k);
                    }
                    if (isResult(n)) {
                        return;
                    }
                }
            }

        }
    }

    private void replaceNumber(int j, int n, int i) {
        if (n == 0) {
            args[j] = i;
            return;
        }
        int tmp = args[j] % powOfTen(n);
        args[j] = tmp + i * powOfTen(n);
    }

    private boolean isResult(int n) {
        int tmp = args[0] + args[1];
        if (n == 0)
            if (tmp > 9) {
                return tmp % 10 == args[2];
            }
        if (question.get(2).size() > n + 1 && n != 0) {
            if (tmp < powOfTen(n + 1)) {
                return false;
            }
        }
        return tmp == args[2];
    }

    private boolean isSolution() {
        int a = sbExpr[0].length();
        int b = sbExpr[1].length();
        int c = sbExpr[2].length();
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

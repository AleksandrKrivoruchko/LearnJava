
import java.util.List;
import java.util.LinkedList;

public class FindSolution {
    private String expression;
    private StringBuilder[] sbExpr;
    private List<List<Boolean>> question;
    private List<List<Integer>> numbers;

    public FindSolution(String str) {
        expression = str;
        sbExpr = parsingExpression(expression);
        question = new LinkedList<>();
        numbers = new LinkedList<>();
        parsingNumber();
    }

    public StringBuilder[] getSbExpr() {
        return sbExpr;
    }

    public List<List<Boolean>> getQuestion() {
        return question;
    }

    public List<List<Integer>> getNumbers() {
        return numbers;
    }

    private void parsingNumber() {

        int tmp = 0;
        for (int i = 0; i < sbExpr.length; i++) {
            List<Integer> num = new LinkedList<>();
            List<Boolean> quest = new LinkedList<>();
            for (int j = 0; j < sbExpr[i].length(); j++) {
                tmp = getDigit(sbExpr[i].charAt(j));
                if (tmp < 0) {
                    quest.add(true);
                    num.add(0);
                } else {
                    quest.add(false);
                    num.add(tmp);
                }
            }
            question.add(quest);
            numbers.add(num);
            // quest.clear();
            // num.clear();
        }
    }

    // public boolean findSolution(StringBuilder[] sb) {
    // if (!isSolution(sbExpr)) {
    // return false;
    // }
    // int len = sb.length;
    // int[] indexSb = new int[len];
    // for (int i = 0; i < len; i++) {
    // indexSb[i] = sb[i].length() - 1;
    // }
    // while (indexSb[len - 1] >= 0) {

    // }
    // }

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

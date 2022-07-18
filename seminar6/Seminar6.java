// Задано уравнение вида q + w = e, q, w, e >= 0. Некоторые цифры могут быть заменены знаком вопроса, 
// например 2? + ?5 = 69. Требуется восстановить выражение до верного равенства.
//  Предложить хотя бы одно решение или сообщить, что его нет.

public class Seminar6 {
    public static void main(String[] args) {
        String str = " 2? + ?5 = 69";
        StringBuilder[] strArr = parsingExpression(str);
        for (StringBuilder s : strArr) {
            System.out.println(s);
        }
        if (!isSolution(strArr)) {
            System.out.println("Решения для выражения " + str + " нет");
            System.exit(1);
        }
        System.out.println(str);
    }

    static boolean findSolution(StringBuilder[] sb) {
        int len = sb.length;
        int[] indexSb = new int[len];
        for (int i = 0; i < len; i++) {
            indexSb[i] = sb[i].length() - 1;
        }
        while (indexSb[len - 1] >= 0) {

        }
    }

    static boolean isSolution(StringBuilder[] strArr) {
        int a = strArr[0].length();
        int b = strArr[1].length();
        int c = strArr[2].length();
        return a <= c && b <= c;
    }

    static int getDigit(char ch) {
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

    static char getChar(int x) {
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

    static int powOfTen(int n) {
        int res = 1;
        for (int i = 0; i < n; i++) {
            res *= 10;
        }
        return res;
    }

    static StringBuilder[] parsingExpression(String str) {
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
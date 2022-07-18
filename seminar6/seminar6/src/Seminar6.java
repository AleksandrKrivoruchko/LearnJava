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
        if(!isSolution(strArr)) {
            System.out.println("Для выражения " + str + " решения нет");
            System.exit(1);
        }
        System.out.println(str);

        findSolution(strArr);
        strArr[0].append(" + ").append(strArr[1]).append(" = ").append(strArr[2]);
        System.out.println(strArr[0]);
    }

    static void findSolution(StringBuilder[] sb) {
        int[] indexSb = {sb[0].length() - 1, sb[1].length() - 1, sb[2].length() - 1};
        boolean[] tmp;
        while (indexSb[2] >= 0) {
            tmp = questionMark(sb[0].charAt(indexSb[0]), sb[1].charAt(indexSb[1]),
                    sb[2].charAt(indexSb[2]));
            if(tmp[0] || tmp[1] || tmp[2]) {
                int x = findDigit(sb[0].charAt(indexSb[0]), sb[1].charAt(indexSb[1]),
                        sb[2].charAt(indexSb[2]), tmp);
                if(x != -1) {
                    for(int i = 0; i < tmp.length; i++) {
                        if(tmp[i]) {
                            sb[i].setCharAt(indexSb[i], getChar(x));
                        }
                    }
                }
            }
            for(int i = 0; i < indexSb.length; i++) {
                indexSb[i]--;
                if(indexSb[i] < 0) {
                    indexSb[2] = -1;
                }
            }
        }
    }

    static int findDigit(char a, char b, char c, boolean[] tmp) {
        if(tmp[0] && !tmp[1] && !tmp[2]) {
            return calcDigit(getDigit(c) - getDigit(b));
        }
        if (!tmp[0] && tmp[1] && !tmp[2]) {
            return calcDigit(getDigit(c) - getDigit(a));
        }
        if(!tmp[0] && !tmp[1] && tmp[2]) {
            return calcDigit(getDigit(a) + getDigit(b));
        }
        return -1;
    }

    static int calcDigit(int x) {
        if (x < 0) {
            return x + 10;
        }
        if (x > 9) {
            return x%10;
        }
        return x;
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

    static boolean[] questionMark(char a, char b, char c) {
        boolean[] mark = new boolean[3];
        if(a == '?') {
            mark[0] = true;
        }
        if(b == '?') {
            mark[1] = true;
        }
        if (c == '?') {
            mark[2] = true;
        }
        return mark;
    }

    static boolean isSolution(StringBuilder[] strArr) {
        int a = strArr[0].length();
        int b = strArr[1].length();
        int c = strArr[2].length();
        return a <= c && b <= c;
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
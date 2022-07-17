// Задано уравнение вида q + w = e, q, w, e >= 0. Некоторые цифры могут быть заменены знаком вопроса, 
// например 2? + ?5 = 69. Требуется восстановить выражение до верного равенства.
//  Предложить хотя бы одно решение или сообщить, что его нет.

public class Seminar6 {
    public static void main(String[] args) {
        String str = " 2? + ?5 = 69";
        String[] strArr = parsingExpression(str);
        for (String s : strArr) {
            System.out.println(s);
        }
        if(!isSolution(strArr)) {
            System.out.println("Решения для выражения " + str + " нет");
            System.exit(1);
        }
        System.out.println(str);
    }

    static boolean isSolution(String[] strArr) {
        int a = strArr[0].length();
        int b = strArr[1].length();
        int c = strArr[2].length();
        if (a > c || b > c) {
            return false;
        }
        return true;
    }

    static boolean isDigit(String str) {
        for (char ch : str.toCharArray()) {
            if (!Character.isDigit(ch)) {
                return false;
            }
        }
        return true;
    }

    static String[] parsingExpression(String str) {
        str = str.trim().replace(" ", "");
        String[] resStr = new String[3];
        String temp = "";
        int j = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '+' || str.charAt(i) == '=') {
                resStr[j] = temp;
                j++;
                temp = "";
                continue;
            }
            temp += str.charAt(i);
        }
        resStr[j] = temp;
        return resStr;
    }
}
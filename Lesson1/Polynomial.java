import java.util.Random;

public class Polynomial {
    public static String CreatePolynomial(int power) {
        String res = "";
        if (power < 1)
            return String.format("Многочлена со степенью %d не существует!", power);
        Random rnd = new Random();
        for (int i = power; i > 0; i--) {
            int tmp = rnd.nextInt(101);
            if (tmp > 0 && i < power && i > 1) {
                res += " + ";
            }
            if (tmp > 1) {
                if (i > 1) {
                    res += tmp + "*x^" + i;
                } else {
                    if (res.length() > 0) {
                        res += " + " + tmp + "*x";
                    } else {
                        res += tmp + "*x";
                    }
                }
            } else {
                if (tmp > 0) {
                    if (i > 1) {
                        res += "x^" + i;
                    } else {
                        if (res.length() > 0) {
                            res += " + " + "x";
                        } else {
                            res += "x";
                        }
                    }
                }
            }
        }
        if (res.length() > 0) {
            int tmp = rnd.nextInt(2);
            if (tmp > 0) {
                int temp = rnd.nextInt(101);
                if (temp > 0) {
                    res += " + " + temp;
                }
            }
            res += " = 0";
        }
        return res;
    }
}

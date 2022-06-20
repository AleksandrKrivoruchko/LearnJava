import java.util.Random;

public class Polynomial {
    public static String CreatePolynomial(int power) {
        StringBuilder res = new StringBuilder();
        if (power < 1)
            return String.format("Многочлена со степенью %d не существует!", power);
        Random rnd = new Random();
        for (int i = power; i > 0; i--) {
            int tmp = rnd.nextInt(101);
            if (tmp > 0 && i < power && i > 1) {
                res.append(" + ");
            }
            if (tmp > 1) {
                if (i > 1) {
                    res.append(tmp + "*x^" + i);
                } else {
                    if (res.length() > 0) {
                        res.append(" + " + tmp + "*x");
                    } else {
                        res.append(tmp + "*x");
                    }
                }
            } else {
                if (tmp > 0) {
                    if (i > 1) {
                        res.append("x^" + i);
                    } else {
                        if (res.length() > 0) {
                            res.append(" + " + "x");
                        } else {
                            res.append("x");
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
                    res.append(" + " + temp);
                }
            }
            res.append(" = 0");
        }
        return res.toString();
    }
}

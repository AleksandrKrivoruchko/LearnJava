import java.util.*;

public class PosfixTest {
    public static void main(String[] args) {
        char[] t = { '(', '+', '-', '*', '/', '^', '~' };
        int[] pT = { 0, 1, 1, 2, 2, 3, 4 };
        ArrayList<Character> ops = new ArrayList<>();
        for (int i = 0; i < t.length; i++) {
            ops.add(t[i]);
        }
        String exp = "(2 ^ 3 * (10 / (5 - 3)))^(8-(5 + 2))";
        exp = exp.trim().replace(" ", "").toLowerCase();
        StringBuilder sb = new StringBuilder(exp);
        ArrayDeque<String> dqArgs = new ArrayDeque<>();
        ArrayDeque<String> dqOp = new ArrayDeque<>();
        System.out.println(ops.toString());
        Mather post = new Mather(exp);
        String txt = post.postfixExpr;
        System.out.println(post.infixExpr);
        System.out.println(txt);
    }
}

import java.util.ArrayDeque;

public class Mather {
    // Хранит инфиксное выражение
    public String infixExpr;
    // Хранит постфиксное выражение
    public String postfixExpr;

    char[] t = { '(', '+', '-', '*', '/', '^', '~' };
    int[] pT = { 0, 1, 1, 2, 2, 3, 4 };

    public Mather(String expression) {
        infixExpr = expression;
        postfixExpr = ToPostfix(infixExpr + "\n");
    }

    public String GetPostfix() {
        return postfixExpr;
    }

    private String GetStingNumber(String expr, int pos) {
        String strNumber = "";

        for (; pos < expr.length(); pos++) {
            char num = expr.charAt(pos);
            if (Character.isDigit(num)) {
                strNumber += num;
            } else {
                break;
            }
        }
        return strNumber;
    }

    private String ToPostfix(String infixExpr) {
        String postfixExpr = "";
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < infixExpr.length(); i++) {
            char c = infixExpr.charAt(i);
            if (Character.isDigit(c)) {
                String tmp = GetStingNumber(infixExpr, i);
                i = i + tmp.length() - 1;
                postfixExpr += tmp + " ";
            } else {
                if (c == '(') {
                    stack.push(c);
                } else {
                    if (c == ')') {
                        while (!stack.isEmpty() && stack.peek() != '(') {
                            postfixExpr += stack.pop();
                        }
                        if (!stack.isEmpty())
                            stack.pop();
                    } else {
                        if (IsSymbol(t, c)) {
                            char op = c;
                            if (op == '-' && (i == 0 || (i > 1 &&
                                    IsSymbol(t, infixExpr.charAt(i - 1))))) {
                                op = '~';
                            }

                            while (!stack.isEmpty() && (pT[GetIndex(t, stack.peek())] >= pT[GetIndex(t, op)])) {
                                postfixExpr += stack.pop();
                            }
                            stack.push(op);
                        }
                    }
                }
            }
        }
        for (char op : stack) {
            postfixExpr += op;
        }
        return postfixExpr;
    }

    private boolean IsSymbol(char[] t, char c) {
        for (int i = 0; i < t.length; i++) {
            if (t[i] == c) {
                return true;
            }
        }
        return false;
    }

    private int GetIndex(char[] t, char c) {
        for (int i = 0; i < t.length; i++) {
            if (t[i] == c) {
                return i;
            }
        }
        return -1;
    }

    private double Execute(char op, double first, double second) {
        switch (op) {
            case '+':
                return first + second;
            case '-':
                return first - second;
            case '*':
                return first * second;
            case '/':
                return first / second;
            case '^':
                return Math.pow(first, second);
            default:
                return 0;
        }
    }

    public double Calc() {
        ArrayDeque<Double> locals = new ArrayDeque<>();
        int counter = 0;
        for (int i = 0; i < postfixExpr.length(); i++) {
            char c = postfixExpr.charAt(i);
            if (Character.isDigit(c)) {
                String tmp = GetStingNumber(postfixExpr, i);
                i = i + tmp.length() - 1;
                Double x = Double.parseDouble(tmp);
                locals.push(x);
            } else if (IsSymbol(t, c)) {
                counter++;
                if (c == '~') {
                    Double last = locals.isEmpty() ? 0 : locals.pop();
                    locals.push(Execute('-', 0, last));
                    System.out.println(counter + "  " + c + last +
                            " = " + locals.peek());
                    continue;
                }

                Double second = locals.isEmpty() ? 0 : locals.pop();
                Double first = locals.isEmpty() ? 0 : locals.pop();

                locals.push(Execute(c, first, second));
                System.out.println(counter + "  " + first + " " + c +
                        " " + second + " = " + locals.peek());
            }
        }
        return locals.pop();
    }
}

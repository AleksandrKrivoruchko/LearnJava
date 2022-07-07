import java.util.ArrayDeque;

public class InfixToPostfix {
    public String infixExpr;
    public String postfixExpr;
    private String[] op;
    private int[] priority;

    public InfixToPostfix(String expression, String[] operator, int[] opPrioruty) {
        infixExpr = expression;
        op = operator;
        priority = opPrioruty;
    }

    private String GetStringNumber(String expr, int pos) {
        String strNumber = "";
        for (; pos < expr.length(); pos++) {
            char num = expr.charAt(pos);
            if (Character.isDigit(num)) {
                strNumber += num;
            } else
                break;
        }
        return strNumber;
    }

    private String GetOperator(String expr, String[] op, int pos) {
        String operator = "";
        for (int i = 0; i < op.length; i++) {
            if (expr.charAt(pos) == op[i].charAt(0)) {
                if (op[i].length() > 1 && expr.charAt(pos + 1) == op[i].charAt(1)) {
                    operator = op[i];
                    break;
                } else {
                    if (op[i].length() == 1) {
                        operator = op[i];
                        break;
                    }
                }
            }
        }
        return operator;
    }

    private boolean IsOperator(String expr, String[] op, int pos) {
        if (GetOperator(expr, op, pos).length() > 0) {
            return true;
        }
        return false;
    }

    public String ToPostfix(String infixExpr) {
        String tmpInfix = infixExpr.trim().replace(" ", "").toLowerCase();
        ArrayDeque<String> stack = new ArrayDeque<>();
        String postfix = "";
        String tmp = "";
        int len = tmpInfix.length();
        for (int i = 0; i < len; i++) {
            char c = tmpInfix.charAt(i);
            if (Character.isDigit(c)) {
                tmp = GetStringNumber(tmpInfix, i);
                i = i + tmp.length() - 1;
                postfix += tmp + " ";
            } else if (c == '(') {
                tmp = GetOperator(tmpInfix, op, i);
                i = i + tmp.length() - 1;
                stack.push(tmp);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != ")") {
                    postfix += stack.pop();
                }
                if (stack.isEmpty()) {
                    return "Некорректно раставлены скобки!";
                }
                stack.pop();
            } else if (IsOperator(tmpInfix, op, i)) {
                String tmpOp = GetOperator(tmpInfix, op, i);

                if (tmpOp == "-" && (i == 0 || (i > 1 && IsOperator(tmpInfix, op, i - 1)))) {
                    tmpOp = "~";
                }

            }
        }
    }
}

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
        postfixExpr = ToPostfix(infixExpr);
    }

    private String GetStringNumber(String expr, int pos) {
        String strNumber = "";
        for (; pos < expr.length(); pos++) {
            char num = expr.charAt(pos);
            if (Character.isDigit(num) || num == '.') {
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

    private int GetIndex(String oper) {
        for (int i = 0; i < op.length; i++) {
            if (oper == op[i]) {
                return i;
            }
        }
        return -1;
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
            } else if (c == 'p' && tmpInfix.charAt(i + 1) == 'i') {
                postfix += Math.PI + " ";
                i++;
            } else if (c == '(') {
                tmp = GetOperator(tmpInfix, op, i);
                i = i + tmp.length() - 1;
                stack.push(tmp);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != "(") {
                    postfix += stack.pop();
                }
                if (stack.isEmpty()) {
                    return "Некорректно расставлены скобки!!!";
                }
                stack.pop();
            } else if (IsOperator(tmpInfix, op, i)) {
                String tmpOp = GetOperator(tmpInfix, op, i);

                if (tmpOp == "-" && (i == 0 || (i > 0 &&
                        IsOperator(tmpInfix, op, i - 1)))) {
                    tmpOp = "~";
                }

                while (!stack.isEmpty() && (priority[GetIndex(stack.peek())] >= priority[GetIndex(tmpOp)])) {
                    postfix += stack.pop();
                }
                stack.push(tmpOp);
                i = i + tmpOp.length() - 1;
            }
        }
        for (String str : stack) {
            postfix += str;
        }
        return postfix;
    }

    private Double Execute(String op, Double first, Double second) {
        switch (op) {
            case "+":
                return first + second;
            case "-":
                return first - second;
            case "*":
                return first * second;
            case "/":
                return first / second;
            case "^":
                return Math.pow(first, second);
            default:
                return .0;
        }
    }

    private Double Execute(String op, Double one) {
        switch (op) {
            case "-":
                return -one;
            case "cos":
                return Math.cos(one);
            case "sin":
                return Math.sin(one);
            case "tan":
                return Math.tan(one);
            default:
                return .0;
        }
    }

    public String Calc() {
        if (!IsBracketsRight(infixExpr)) {
            return "Некорректно расставлены скобки!!!";
        }
        ArrayDeque<Double> result = new ArrayDeque<>();
        int counter = 0;
        int len = postfixExpr.length();
        Double temp = .0;
        String number = "";
        Double first = .0;
        Double second = .0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            char c = postfixExpr.charAt(i);
            if (Character.isDigit(c)) {
                number = GetStringNumber(postfixExpr, i);
                i = i + number.length() - 1;
                temp = Double.parseDouble(number);
                result.push(temp);
            } else if (IsOperator(postfixExpr, op, i)) {
                counter++;
                if (c == '~') {
                    temp = result.isEmpty() ? .0 : result.pop();
                    result.push(Execute("-", temp));
                    sb.append(counter + "  " + "-" + temp + " = " + result.peek() + "\n");
                    continue;
                }
                String operator = GetOperator(postfixExpr, op, i);
                i = i + operator.length() - 1;
                if (priority[GetIndex(operator)] > 4) {
                    temp = result.isEmpty() ? 0 : result.pop();
                    result.push(Execute(operator, temp));
                    sb.append(counter + "  " + operator + " " + temp + " = " + result.peek() + "\n");
                    continue;
                }

                second = result.isEmpty() ? 0 : result.pop();
                first = result.isEmpty() ? 0 : result.pop();
                result.push(Execute(operator, first, second));
                sb.append(counter + "  " + first + " " + operator + " " +
                        second + " = " + result.peek() + "\n");
            }
        }
        System.out.println("Вычисление выражения:\n" + sb);
        temp = result.pop();
        Long res = Math.round(temp);
        Double t = temp - res;
        if (t > 0.001) {
            return Double.toString(temp);
        }
        return Long.toString(res);
    }

    private boolean IsBracketsRight(String str) {
        ArrayDeque<Character> temp = new ArrayDeque<>();
        int len = str.length();
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            if (c == '(') {
                temp.push(c);
                continue;
            }
            if (c == ')') {
                if (temp.isEmpty()) {
                    return false;
                }
                temp.pop();
            }
        }
        return temp.isEmpty();
    }
}

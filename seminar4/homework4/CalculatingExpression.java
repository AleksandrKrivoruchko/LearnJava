// Реализовать алгоритм перевода из инфиксной записи в постфиксную 
// для арифметического выражения.
// Вычислить запись если это возможно
// Написать программу вычисляющую значение сложного арифметического выражения. 
// Для простоты - выражение всегда вычисляемое
// Пример: (2^3 * (10 / (5 - 3)))^(Sin(Pi)) ответ - 1 

public class CalculatingExpression {
    public static void main(String[] args) {
        String sourceStr = "(2^3 * (10 / (5 - 3)))^(Sin(Pi))";
        String[] op = { "(", "+", "-", "*", "/", "^", "~", "cos", "sin", "tan" };
        int[] prOp = { 0, 1, 1, 2, 2, 3, 4, 5, 5, 5 };
    }
}

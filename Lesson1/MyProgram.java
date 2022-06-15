import java.util.Scanner;

public class MyProgram {
    public static void main(String[] args) {
        Scanner isNum = new Scanner(System.in);
        System.out.print("Введите целое число: ");
        if (isNum.hasNextInt()) {
            int n = isNum.nextInt();
            int result = CalculatingTriangularNumber(n);
            if (result == -1) {
                System.out.printf("Для числа %d треугольного числа не существует!\n", n);
            } else {
                System.out.printf("Для числа %d треугольное число равно %d\n", n, result);
            }
        } else {
            System.out.printf("%s - не является целым числом!\n", isNum.nextLine());
        }
        isNum.close();
    }

    static int CalculatingTriangularNumber(int n) {
        if (n < 1)
            return -1;
        return (n * (n + 1)) / 2;
    }
}
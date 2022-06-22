//     __
//    ____
//   ______
//  ________
// __________

public class HanoiTower {
    static int step = 0;

    public static void main(String[] args) {
        int n = 5;
        int m = n;

        MyStack stA = new MyStack(n);
        MyStack stB = new MyStack(n);
        MyStack stC = new MyStack(n);

        int[] start = new int[n];
        for (int i = 0; i < n; i++) {
            start[i] = i + 1;
        }
        stA.Push(start);
        System.out.println(BuildHanoi(n, stA, stB, stC));
        // HanoiMove(n, new MyStack[] { stA, stB, stC });
        Hanoi(m, n, stA, stC, stB);
    }

    public static void Hanoi(int m, int n, MyStack a, MyStack c, MyStack b) {
        if (n == 1) {
            c.Push(a.Pop());
            step++;
            System.out.printf("Шаг %d\n", step);
            System.out.println(BuildHanoi(m, a, b, c));
            return;
        } else {
            Hanoi(m, n - 1, a, b, c);
            c.Push(a.Pop());
            step++;
            System.out.printf("Шаг %d\n", step);
            System.out.println(BuildHanoi(m, a, b, c));
            Hanoi(m, n - 1, b, c, a);
        }
    }

    // public static void HanoiMove(int n, MyStack[] t) {
    // if (t[0].GetCount() == 0) {
    // return;
    // }
    // int k = 0;
    // t[2].Push(t[0].Pop());
    // k++;
    // System.out.printf("Шаг %d\n", k);
    // System.out.println(BuildHanoi(n, t[0], t[1], t[2]).toString());
    // t[1].Push(t[0].Pop());
    // k++;
    // System.out.printf("Шаг %d\n", k);
    // System.out.println(BuildHanoi(n, t[0], t[1], t[2]).toString());
    // t[1].Push(t[2].Pop());
    // k++;
    // System.out.printf("Шаг %d\n", k);
    // System.out.println(BuildHanoi(n, t[0], t[1], t[2]).toString());
    // t[2].Push(t[0].Pop());
    // k++;
    // System.out.printf("Шаг %d\n", k);
    // System.out.println(BuildHanoi(n, t[0], t[1], t[2]).toString());
    // t[0].Push(t[1].Pop());
    // k++;
    // System.out.printf("Шаг %d\n", k);
    // System.out.println(BuildHanoi(n, t[0], t[1], t[2]).toString());
    // t[2].Push(t[1].Pop());
    // k++;
    // System.out.printf("Шаг %d\n", k);
    // System.out.println(BuildHanoi(n, t[0], t[1], t[2]).toString());
    // t[2].Push(t[0].Pop());
    // k++;
    // System.out.printf("Шаг %d\n", k);
    // System.out.println(BuildHanoi(n, t[0], t[1], t[2]).toString());
    // }

    public static StringBuilder BuildHanoi(int n, MyStack stA, MyStack stB, MyStack stC) {
        StringBuilder sb = new StringBuilder();
        char a;
        char b;
        char c;
        for (int i = 0; i < n; i++) {
            if (stA.GetItem(i) == 0) {
                a = ' ';
            } else {
                a = '_';
            }
            if (stB.GetItem(i) == 0) {
                b = ' ';
            } else {
                b = '_';
            }
            if (stC.GetItem(i) == 0) {
                c = ' ';
            } else {
                c = '_';
            }
            sb.append(LineHanoi(stA.GetItem(i), n, a));
            sb.append(LineHanoi(stB.GetItem(i), n, b));
            sb.append(LineHanoi(stC.GetItem(i), n, c));
            sb.append('\n');
        }
        return sb;
    }

    public static StringBuilder LineHanoi(int m, int n, char sign) {
        StringBuilder sb = new StringBuilder();
        int width = n * 2 + 2;
        int center = width / 2;
        int start = center - m;

        for (int j = 0; j < start; j++) {
            sb.append(" ");
        }
        for (int j = 0; j < m * 2; j++) {
            sb.append(sign);
        }
        for (int j = start + m * 2; j <= width; j++) {
            sb.append(' ');
        }
        return sb;
    }
}

//     __
//    ____
//   ______
//  ________
// __________

public class HanoiTower {
    public static void main(String[] args) {
        int n = 4;

        MyStack stA = new MyStack(n);
        MyStack stB = new MyStack(n);
        MyStack stC = new MyStack(n);

        int[] start = new int[n];
        for (int i = 0; i < n; i++) {
            start[i] = i + 1;
        }
        stA.Push(start);
        System.out.println(BuildHanoi(n, stA, stB, stC));
        HanoiMove(n, new MyStack[] { stA, stB, stC });
    }

    public static void HanoiMove(int n, MyStack[] t) {
        int k = 0;
        while (t[0].GetCount() != 0) {
            t[1].Push(t[0].Pop());
            k++;
            System.out.println(k + BuildHanoi(n, t[0], t[1], t[2]).toString());
            t[2].Push(t[0].Pop());
            k++;
            System.out.println(k + BuildHanoi(n, t[0], t[1], t[2]).toString());
            t[0].Push(t[1].Pop());
            System.out.println(k + BuildHanoi(n, t[0], t[1], t[2]).toString());
            t[1].Push(t[2].Pop());
            // t[2].Push(t[0].Pop());
            // System.out.println(BuildHanoi(n, t[0], t[1], t[2]).toString());
            // t[0].Push(t[1].Pop());
            // System.out.println(BuildHanoi(n, t[0], t[1], t[2]).toString());
            // t[2].Push(t[1].Pop());
            // System.out.println(BuildHanoi(n, t[0], t[1], t[2]).toString());
            // t[2].Push(t[1].Pop());
            // System.out.println(BuildHanoi(n, t[0], t[1], t[2]).toString());
            // t[0].Push(t[1].Pop());
            // System.out.println(BuildHanoi(n, t[0], t[1], t[2]).toString());
            // t[0].Push(t[2].Pop());
            // System.out.println(BuildHanoi(n, t[0], t[1], t[2]).toString());
            // t[1].Push(t[2].Pop());
            // System.out.println(BuildHanoi(n, t[0], t[1], t[2]).toString());
            // HanoiMove(n - 1, t);
        }
    }

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

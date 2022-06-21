//     __
//    ____
//   ______
//  ________
// __________

public class HanoiTower {
    public static void main(String[] args) {
        String str = PrintHanoi(8, '_');
        System.out.println(str);
    }

    public static String PrintHanoi(int n, char sign) {
        StringBuilder sb = new StringBuilder();
        int width = n * 2 + 2;
        int center = width / 2;
        for (int i = 1; i <= n; i++) {
            for (int k = 0; k < 3; k++) {
                int start = center - i;

                for (int j = 0; j < start; j++) {
                    sb.append(" ");
                }
                for (int j = 0; j < i * 2; j++) {
                    sb.append(sign);
                }
                for (int j = start + i * 2; j <= width; j++) {
                    sb.append(' ');
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

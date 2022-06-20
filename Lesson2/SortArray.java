import java.util.Random;

public class SortArray {
    public static void HeapSort(int[] arr) {
        int len = arr.length;

        for (int i = len / 2 - 1; i >= 0; i--) {
            BuildingHeap(arr, len, i);
        }

        for (int i = len - 1; i >= 0; i--) {
            int tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;
            BuildingHeap(arr, i, 0);
        }
    }

    static void BuildingHeap(int[] arr, int len, int i) {
        int maxIndex = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < len && arr[left] > arr[maxIndex]) {
            maxIndex = left;
        }

        if (right < len && arr[right] > arr[maxIndex]) {
            maxIndex = right;
        }

        if (maxIndex != i) {
            int tmp = arr[i];
            arr[i] = arr[maxIndex];
            arr[maxIndex] = tmp;

            BuildingHeap(arr, len, maxIndex);
        }
    }

    public static int[] CreateArray(int len, int min, int max) {
        int[] arr = new int[len];
        Random rnd = new Random();
        for (int i = 0; i < len; i++) {
            arr[i] = rnd.nextInt(min, max + 1);
        }
        return arr;
    }

    public static String ArrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            sb.append(arr[i] + " ");
            if ((i + 1) % 15 == 0) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}

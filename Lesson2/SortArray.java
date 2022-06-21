import java.util.Random;

public class SortArray {
    public static void HeapSort(int[] arr) {
        int len = arr.length;

        for (int i = len / 2 - 1; i >= 0; i--) {
            BuildingHeap(arr, len, i);
        }

        for (int i = len - 1; i >= 0; i--) {
            Swap(arr, 0, i);
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
            Swap(arr, i, maxIndex);

            BuildingHeap(arr, len, maxIndex);
        }
    }

    public static void Swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
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
            sb.append(String.format("%5d", arr[i]));
            if ((i + 1) % 10 == 0) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}

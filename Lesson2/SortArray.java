import java.util.Random;

public class SortArray {
    // Пирамидальная сортировка
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

    // Сортировка слиянием
    public static int[] MergeSort(int[] arr, int[] buff, int left, int right) {
        if (left == right) {
            buff[left] = arr[left];
            return buff;
        }

        int middle = left + (right - left) / 2;
        int[] lBuff = MergeSort(arr, buff, left, middle);
        int[] rBuff = MergeSort(arr, buff, middle + 1, right);
        int[] result = lBuff == arr ? buff : arr;

        int lCurrent = left;
        int rCurrent = middle + 1;
        for (int i = left; i <= right; i++) {
            if (lCurrent <= middle && rCurrent <= right) {
                if (lBuff[lCurrent] < rBuff[rCurrent]) {
                    result[i] = lBuff[lCurrent];
                    lCurrent++;
                } else {
                    result[i] = rBuff[rCurrent];
                    rCurrent++;
                }
            } else if (lCurrent <= middle) {
                result[i] = lBuff[lCurrent];
                lCurrent++;
            } else {
                result[i] = rBuff[rCurrent];
                rCurrent++;
            }
        }
        return result;
    }

    // Создание массива длинной len, c целыми числами в диапазоне от min до max
    public static int[] CreateArray(int len, int min, int max) {
        int[] arr = new int[len];
        Random rnd = new Random();
        for (int i = 0; i < len; i++) {
            arr[i] = rnd.nextInt(min, max + 1); // version for openjdk 17.0.3 for linux
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

    public static int[] CopyArray(int[] arr) {
        int len = arr.length;
        int[] tmp = new int[len];
        for (int i = 0; i < len; i++) {
            tmp[i] = arr[i];
        }
        return tmp;
    }
}

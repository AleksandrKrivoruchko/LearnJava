package seminar2.Homework2;

public class HomeworkMergeSort {
    public static void main(String[] args) {
        int len = 100;
        int max = 101;
        // int min = -100;
        int[] arr = ArraySort.CreateArray(len, -100, max);
        ArraySort.PrintArray(arr);
        int[] tmp = new int[len];
        int[] res = ArraySort.MergeSort(arr, tmp, 0, len - 1);
        ArraySort.PrintArray(res);
    }
}

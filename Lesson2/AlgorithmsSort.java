public class AlgorithmsSort {
    public static void main(String[] args) {
        int[] array = SortArray.CreateArray(10, -100, 100);
        System.out.println(SortArray.ArrayToString(array));
        System.out.println();
        SortArray.HeapSort(array);
        System.out.println(SortArray.ArrayToString(array));
    }
}
public class AlgorithmsSort {
    public static void main(String[] args) {
        int[] array = SortArray.CreateArray(30, -100, 100);
        System.out.println("Исходный массив");
        System.out.println(SortArray.ArrayToString(array));

        int[] array1 = SortArray.CopyArray(array);

        SortArray.HeapSort(array);
        System.out.println("Массив отсортированный с помощью алгоритма пирамидальной сортировки");
        System.out.println(SortArray.ArrayToString(array));

        int[] buffer = new int[array1.length];
        SortArray.MergeSort(array1, buffer, 0, array1.length - 1);
        System.out.println("Массив отсортированный с помощью алгоритма сортировки слиянием");
        System.out.println(SortArray.ArrayToString(array1));
    }
}
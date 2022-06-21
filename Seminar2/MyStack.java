public class MyStack {
    int index;
    int count;
    int currentCount = 0;
    int[] arr;

    public MyStack(int n) {
        count = n;
        arr = new int[n];
        index = --n;
    }

    void Push(int x) {
        arr[index] = x;
        index--;
        currentCount++;
    }

    void Push(int[] x) {
        int len = x.length;
        for (int i = len - 1; i >= 0; i--) {
            Push(x[i]);
        }
    }

    int Pop() {
        index++;
        currentCount--;
        int tmp = arr[index];
        arr[index] = 0;
        return tmp;
    }

    int GetItem(int in) {
        return arr[in];
    }

    int GetCount() {
        return currentCount;
    }

    void PrintStack() {
        for (int i = 0; i < index; i++) {
            System.out.printf("%4d", arr[i]);
        }
        System.out.println();
    }

    void PrintFullStack() {
        for (int i = 0; i < count; i++) {
            System.out.printf("%4d", arr[i]);
        }
        System.out.println();
    }
}

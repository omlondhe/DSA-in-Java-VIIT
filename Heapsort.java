public class Heapsort {
    void heapify(int arr[], int size, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < size && arr[left] > arr[largest]) largest = left;
        if (right < size && arr[right] > arr[largest]) largest = right;

        if (largest != i) {
            int temp = arr[largest];
            arr[largest] = arr[i];
            arr[i] = temp;
            heapify(arr, size, largest);
        }
    }
    public static void main(String[] args) {
        int size = 6;
        int arr[] = {4, 3, 6, 2, 1, 5};

        Heapsort heapsort = new Heapsort();

        for (int i = (size / 2) - 1; i >= 0; i--) heapsort.heapify(arr, size, i);
        while (size > 0) {
            int temp = arr[--size];
            arr[size] = arr[0];
            arr[0] = temp;
            heapsort.heapify(arr, size, 0);
        }

        for (int i = 0; i < 6; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}

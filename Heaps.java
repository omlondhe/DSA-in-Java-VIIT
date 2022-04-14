import java.util.Scanner;

class MaxHeap {
    int size = 0;
    int heap[] = new int[100];
    
    void insert(int mark) {
        heap[++size] = mark;
        int index = size;

        while (index > 1) {
            int parent = index / 2;

            if (heap[index] > heap[parent]) {
                int temp = heap[index];
                heap[index] = heap[parent];
                heap[parent] = temp;
                index = parent;
            } else return;
        }
    }
    
    int pop() {
        if (size != 0) {
            int ans = heap[1];
            heap[1] = heap[size--];

            for (int i = 1; i < size; i++) {
                int left = i * 2;
                int right = i * 2 + 1;

                if (left < size && heap[i] < heap[left]) {
                    int temp = heap[left];
                    heap[left] = heap[i];
                    heap[i] = temp;
                } else if (right < size && heap[i] < heap[right]) {
                    int temp = heap[right];
                    heap[right] = heap[i];
                    heap[i] = temp;
                }  else break;
            }
            return ans;
        }
        return -1;
    }
}

class MinHeap {
    int size = 0;
    int heap[] = new int[100];
    
    void insert(int mark) {
        heap[++size] = mark;
        int index = size;

        while (index > 1) {
            int parent = index / 2;

            if (heap[index] < heap[parent]) {
                int temp = heap[index];
                heap[index] = heap[parent];
                heap[parent] = temp;
                index = parent;
            } else return;
        }

    }

    int pop() {
        if (size != 0) {
            int ans = heap[1];
            heap[1] = heap[size--];

            for (int i = 1; i < size; i++) {
                int left = i * 2;
                int right = i * 2 + 1;

                if (left < size && heap[i] > heap[left]) {
                    int temp = heap[left];
                    heap[left] = heap[i];
                    heap[i] = temp;
                } else if (right < size && heap[i] > heap[right]) {
                    int temp = heap[right];
                    heap[right] = heap[i];
                    heap[i] = temp;
                }   else break;
            }
            return ans;
        }
        return -1;
    }
}

public class Heaps {
    void maxHeapify(int marks[], int size, int i) {
        int largest = i;
        int left = i * 2;
        int right = i * 2 + 1;

        if (left <= size && marks[left] > marks[largest]) largest = left;
        if (right <= size && marks[right] > marks[largest]) largest = right;

        if (largest != i) {
            int temp = marks[largest];
            marks[largest] = marks[i];
            marks[i] = temp;
            maxHeapify(marks, size, largest);
        }
    }

    void minHeapify(int marks[], int size, int i) {
        int smallest = i;
        int left = i * 2;
        int right = i * 2 + 1;

        if (left <= size && marks[left] < marks[smallest]) smallest = left;
        if (right <= size && marks[right] < marks[smallest]) smallest = right;

        if (smallest != i) {
            int temp = marks[smallest];
            marks[smallest]  = marks[i];
            marks[i] = temp;
            minHeapify(marks, size, smallest);
        }
    }

    public static void main(String[] args) {
        int size = 0;
        int marks[] = new int[100];

        Scanner scanner = new Scanner(System.in);
        MinHeap minHeap = new MinHeap();
        MaxHeap maxHeap = new MaxHeap();

        while(true) {
            System.out.print("Enter the mark obtained:\t");
            int mark = scanner.nextInt();
            marks[++size] = mark;
            minHeap.insert(mark);
            maxHeap.insert(mark);
            System.out.println("You want to enter more marks?\n1:\tYes\n2:\tNo");
            if (scanner.nextInt() == 0) break;
        }

        System.out.println("Using Min heap and Max heap insertion and deletion algorithm:");
        System.out.println("Maximum marks are:\t" + maxHeap.pop()); 
        System.out.println("Minimum marks are:\t" + minHeap.pop());
        
        System.out.println("Using heapify methods:");
        Heaps heaps = new Heaps();
        for (int i = size / 2; i > 0; i--) heaps.maxHeapify(marks, size, i);
        System.out.println("Maximum marks are:\t" + marks[1]); 
        for (int i = size / 2; i > 0; i--) heaps.minHeapify(marks, size, i);
        System.out.println("Minimum marks are:\t" + marks[1]);

        scanner.close();
    }
}

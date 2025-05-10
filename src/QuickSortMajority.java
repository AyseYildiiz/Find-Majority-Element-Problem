public class QuickSortMajority {
    //Finds majority element with using quicksort algorithm
    public static int findMajorityQuickSort(int[] A) {
        quickSort(A, 0, A.length - 1);
        int candidate = A[A.length / 2];
        if (count(A, candidate) > A.length / 2) {
            return candidate;
        } else {
            return -1; 
        }
    }
    //Quicksort Algorithm
    private static void quickSort(int[] A, int low, int high) {
        if (low < high) {
            int pIndex = partition(A, low, high);
            quickSort(A, low, pIndex - 1);
            quickSort(A, pIndex + 1, high);
        }
    }
    //Partitions the array according to choosing first element as pivot
    private static int partition(int[] A, int low, int high) {
        int pivot = A[low];
        int i = low + 1;
        for (int j = low + 1; j <= high; j++) {
            if (A[j] < pivot) {
                swap(A, i, j);
                i++;
            }
        }
        swap(A, low, i - 1);
        return i - 1;
    }
    // Swaps 2 elements
    private static void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
    //Counts the occurrences of a candidate element.
    private static int count(int[] A, int candidate) {
        int count = 0;
        for (int value : A) {
            if (value == candidate) count++;
        }
        return count;
    }
}

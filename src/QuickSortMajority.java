public class QuickSortMajority {

    public static int findMajorityQuickSort(int[] A) {
        quickSort(A, 0, A.length - 1);
        int candidate = A[A.length / 2];
        if (count(A, candidate) > A.length / 2) {
            return candidate;
        } else {
            return -1; 
        }
    }

    private static void quickSort(int[] A, int low, int high) {
        if (low < high) {
            int pIndex = partition(A, low, high);
            quickSort(A, low, pIndex - 1);
            quickSort(A, pIndex + 1, high);
        }
    }

    private static int partition(int[] A, int low, int high) {
        int pivot = A[low]; // First element as pivot
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

    private static void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    private static int count(int[] A, int candidate) {
        int count = 0;
        for (int value : A) {
            if (value == candidate) count++;
        }
        return count;
    }
}

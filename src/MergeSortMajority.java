public class MergeSortMajority {
    // Finds majority element with merge sort algorithm.
    public static int findMajorityElementByMergeSort(int[] A) {
        mergeSort(A, 0, A.length - 1);
        int number = A[A.length / 2];
        int count = 0;
        for (int num : A) {
            if (num == number) {
                count++;
            }
        }

        int result = -1;
        if (count > (A.length / 2)) {
            result = number;
        }
        return result;
    }

    // The merge-sort algorithm
    private static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    // Merges 2 ordered sub-arrays.
    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        System.arraycopy(arr, left, L, 0, n1);
        for (int j = 0; j < n2; j++) {
            R[j] = arr[mid + 1 + j];
        }

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }
        while (i < n1) {
            arr[k++] = L[i++];
        }
        while (j < n2) {
            arr[k++] = R[j++];
        }
    }
}

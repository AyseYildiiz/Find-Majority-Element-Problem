public class InsertionSortMajority {

    public static int findMajorityElementByInsertionSort(int[] A) {

        insertionSort(A);
        int candidate = A[A.length / 2];

        int count = 0;
        for (int num : A) {
            if (num == candidate) {
                count++;
            }
        }

        int result = -1;
        if (count > (A.length / 2)) {
            result = candidate;
        }
        return result;

    }

    // Insertion sort algoritması
    private static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            // key'den büyük elemanları sağa kaydır
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

}
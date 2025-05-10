public class DivideAndConquerMajority {
    // Finds majority element with divide and conquer algorithm.
    public static int findMajorityDC(int[] A) {
    	int majority = findMajority(A, 0, A.length - 1);
    	return (count(A, majority, 0, A.length - 1) > A.length / 2) ? majority : -1;

    }
    //Finds the result recursively diving 2 parts.
    private static int findMajority(int[] A, int left, int right) {
        if (left == right) return A[left];

        int mid = (left + right) / 2;
        int mL = findMajority(A, left, mid);
        int mR = findMajority(A, mid + 1, right);

        if (mL == mR) return mL;

        int leftCount = count(A, mL, left, right);
        int rightCount = count(A, mR, left, right);

        return (leftCount > rightCount) ? mL : mR;
    }
    //Counts how many times the number occurs in subarray.
    private static int count(int[] A, int num, int left, int right) {
        int count = 0;
        for (int i = left; i <= right; i++) {
            if (A[i] == num) count++;
        }
        return count;
    }
}

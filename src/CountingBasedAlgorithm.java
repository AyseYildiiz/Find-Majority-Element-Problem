public class CountingBasedAlgorithm {
    //This function finds majority element with counting occurrences of numbers and adding them into an array.
    public static int findMajorityCounting(int[] nums) {
            int n = nums.length;
            int[] count = new int[2*n];

            for (int num : nums) {
                count[num]++;
                if (count[num] > n / 2) {
                    return num;
                }
            }
            return -1;
        }
}

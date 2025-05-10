public class BruteForceMajority {
    //This function finds majority element with brute force approach
    public static int findMajorityElementByBruteForce(int[] A) {
        int result = -1;
        //Checks all elements one by one
        for (int k : A) {
            int occurrence = 0;
            for (int i : A) {
                if (k == i) {
                    occurrence++;
                }
            }
            if (occurrence >  (A.length / 2)) {
                result = k;
                break;
            }
        }
        return result;

    }

}
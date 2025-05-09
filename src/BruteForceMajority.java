public class BruteForceMajority {

    public static int findMajorityElementByBruteForce(int[] A) {

        int result = -1;
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
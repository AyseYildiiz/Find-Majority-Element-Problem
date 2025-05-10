public class Boyer_Moore_Majority_Vote {
    //This function finds majority element with Boyer-Moore Majority vote algorithm.
    public static int findBoyerMooreMajority(int[] input) {
        int major = -1;
        int count = 0;
    //Compares one element with others and updates counters based on the result.
        for(int num :input){
            if (count == 0){
                major =num;
                count++;
            }
            else if (num == major){
                count++;
            }
            else {
                count--;

            }
        }
        //Checks whether the number is majority element.
        count = 0;
        for(int num :input){
            if (num == major){
                count++;
            }
        }
        if (count>input.length/2)
            return major;
        else return -1;

    }


}
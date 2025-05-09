public class Boyer_Moore_Majority_Vote {
    public Boyer_Moore_Majority_Vote() {
        int[] input = {3, 3, 4, 2, 3, 3, 3};
        int major = -1;
        int count = 0;

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
        count = 0;
        for(int num :input){
            if (num == major){
                count++;
            }
        }
        if (count>input.length/2)
            System.out.println("Majority element is " + major);
        else System.out.println("No majority element found." );

    }


}
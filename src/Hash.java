import java.util.HashMap;

public class Hash {
    public static void hash(String[] args) {

        int[] input = {3, 3, 4, 2, 3, 3, 3};
        int n = input.length;
        HashMap<Integer, Integer> hashTable = new HashMap<>();
        for (int num : input) {
            hashTable.put(num, hashTable.getOrDefault(num, 0) + 1);
            if (hashTable.get(num) > n / 2) {
                System.out.println("Majority element is " + num);
                return;
            }

        }
        System.out.println("No majority element found.");
    }
}
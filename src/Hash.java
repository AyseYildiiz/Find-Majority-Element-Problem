import java.util.HashMap;

public class Hash {
    //Finds majority element with using hash table
    public static int hashMajority(int[] input) {
        int n = input.length;
        HashMap<Integer, Integer> hashTable = new HashMap<>();
        for (int num : input) {
            hashTable.put(num, hashTable.getOrDefault(num, 0) + 1);
            if (hashTable.get(num) > n / 2)
                return num;

        }
        return -1;
    }
}
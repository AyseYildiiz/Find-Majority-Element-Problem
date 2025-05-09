import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        int[] sizes = { 2000, 5000, 10000 };
        int repetitions = 20;

        for (int size : sizes) {
            System.out.println("INPUT SIZE: " + size);

            runTest("All Same", generateAllSame(size, 7), repetitions, size);
            runTest("Dominant Majority", generateDominantMajority(size, 1, 0.7), repetitions, size);
            runTest("Average Case 70% Unique", generateAverageCaseVariant(size, 0.7), repetitions, size);
            runTest("Average Case 50% Unique", generateAverageCaseVariant(size, 0.5), repetitions, size);
            runTest("Average Case 30% Unique", generateAverageCaseVariant(size, 0.3), repetitions, size);
            runTest("No Majority", generateNoMajority(size), repetitions, size);
            runTest("All Different", generateAllDifferent(size), repetitions, size);
            runTest("Increasing Order", generateIncreasing(size), repetitions, size);
            runTest("Decreasing Order", generateDecreasing(size), repetitions, size);
            runTest("Majority in Middle", generateMajorityInMiddle(size, 1, 0.7), repetitions, size);

            System.out.println();
        }
    }

    private static void runTest(String label, int[] input, int repetitions, int size) {
        System.out.println(label + ":");
        System.out.println("---------------");

        // Save input to txt file
        saveInputToFile(input, label, size);

        testAlgorithm("Brute Force", input, repetitions,
                BruteForceMajority::findMajorityElementByBruteForce);

        testAlgorithm("InsertionSort", input, repetitions,
                InsertionSortMajority::findMajorityElementByInsertionSort);

        testAlgorithm("MergeSort", input, repetitions, MergeSortMajority::findMajorityElementByMergeSort);

        testAlgorithm("QuickSort", input, repetitions, QuickSortMajority::findMajorityQuickSort);

        testAlgorithm("Divide & Conquer", input, repetitions, DivideAndConquerMajority::findMajorityDC);

        System.out.println();
    }

    private static void saveInputToFile(int[] input, String label, int size) {

        File dir = new File("inputs");
        if (!dir.exists()) {
            dir.mkdir();
        }

        String safeLabel = label.replaceAll("[^a-zA-Z0-9]", "_");
        String filename = "inputs/input_" + safeLabel + "_" + size + ".txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (int val : input) {
                writer.print(val + " ");
            }
        } catch (IOException e) {
            System.err.println("Failed to write input to file: " + filename);
            e.printStackTrace();
        }
    }

    private static void testAlgorithm(String name, int[] input, int reps, MajorityFinder finder) {
        long totalTime = 0;
        int result = -1;

        for (int i = 0; i < reps; i++) {
            int[] copy = Arrays.copyOf(input, input.length);
            long start = System.nanoTime();
            result = finder.findMajority(copy);
            long end = System.nanoTime();
            totalTime += (end - start);
        }

        double avgTimeMs = ((double) totalTime / reps) / 1_000_000;
        System.out.println(name + " Result: " + result + "\nAverage Time: " + avgTimeMs + " ms\n");
    }

    // === INPUT GENERATORS ===

    private static int[] generateAllSame(int size, int value) {
        int[] A = new int[size];
        Arrays.fill(A, value);
        return A;
    }

    private static int[] generateDominantMajority(int size, int majorityVal, double majorityRatio) {
        int[] A = new int[size];
        int majorityCount = (int) (size * majorityRatio);
        for (int i = 0; i < majorityCount; i++) {
            A[i] = majorityVal;
        }
        Random rand = new Random();
        for (int i = majorityCount; i < size; i++) {
            int val;
            do {
                val = rand.nextInt(size) + 2;
            } while (val == majorityVal);
            A[i] = val;
        }
        shuffleArray(A);
        return A;
    }

    private static int[] generateNoMajority(int size) {
        int[] A = new int[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            A[i] = rand.nextInt(size / 2);
        }
        return A;
    }

    private static int[] generateAllDifferent(int size) {
        int[] A = new int[size];
        for (int i = 0; i < size; i++) {
            A[i] = i;
        }
        shuffleArray(A);
        return A;
    }

    private static int[] generateAverageCaseVariant(int size, double uniqueRatio) {
        int[] A = new int[size];
        int uniqueCount = (int) (size * uniqueRatio);
        Random rand = new Random();

        for (int i = 0; i < uniqueCount; i++) {
            A[i] = i;
        }

        for (int i = uniqueCount; i < size; i++) {
            A[i] = rand.nextInt(uniqueCount);
        }

        shuffleArray(A);
        return A;
    }

    private static int[] generateIncreasing(int size) {
        int[] A = new int[size];
        for (int i = 0; i < size; i++) {
            A[i] = i;
        }
        return A;
    }

    private static int[] generateDecreasing(int size) {
        int[] A = new int[size];
        for (int i = 0; i < size; i++) {
            A[i] = size - i;
        }
        return A;
    }

    // New method for generating majority element in the middle of the array
    private static int[] generateMajorityInMiddle(int size, int majorityVal, double majorityRatio) {
        int[] A = new int[size];
        int majorityCount = (int) (size * majorityRatio);
        int middleIndex = size / 2;

        // First, place majority elements around the middle
        for (int i = middleIndex - majorityCount / 2; i < middleIndex + majorityCount / 2; i++) {
            A[i] = majorityVal;
        }

        // Fill the rest of the array with random values that aren't the majority value
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            if (A[i] != majorityVal) {
                int val;
                do {
                    val = rand.nextInt(size) + 2;
                } while (val == majorityVal);
                A[i] = val;
            }
        }

        return A;
    }

    private static void shuffleArray(int[] A) {
        Random rand = new Random();
        for (int i = A.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = A[i];
            A[i] = A[j];
            A[j] = temp;
        }
    }

    @FunctionalInterface
    interface MajorityFinder {
        int findMajority(int[] array);
    }
}

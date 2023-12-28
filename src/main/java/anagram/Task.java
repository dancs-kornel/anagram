package anagram;

import java.util.Set;
import java.util.TreeSet;

public class Task {

    /**
     * Prints all sorted unique permutations of the given string.
     *
     * @param s The input string for which permutations are generated.
     */
    public static void printPermutations(String s) {
        Set<String> set = new TreeSet<>();
        printPermutations("", s, set);
        for (String permutation : set) {
            System.out.println(permutation);
        }
    }

    /**
     * Recursive method to generate and store sorted unique permutations.
     *
     * @param prefix The current prefix (partial permutation).
     * @param s      The remaining characters to be permuted.
     * @param set    The Set to store sorted unique permutations.
     */
    private static void printPermutations(String prefix, String s, Set<String> set) {
        int n = s.length();
        if (n == 0) {
            set.add(prefix);
        } else {
            for (int i = 0; i < n; i++) {
                printPermutations(prefix + s.charAt(i), s.substring(0, i) + s.substring(i+1, n), set);
            }
        }
    }

    public static void main(String[] args) {
        printPermutations("aabc");
    }
}
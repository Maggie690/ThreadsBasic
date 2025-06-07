package logicalTasks;

import java.util.Arrays;
import java.util.List;

public class Solutions {

    private static final int MISSING_NUMBER_DEFAULT_VALUE = -1;

    public static void main(String[] args) {
        //task 1
        int[] numbers = {1, 2, 3, 4, 5};//{70,68,67,71};

        int missingNumber = findMissingNumber(numbers);
        System.out.println(missingNumber == MISSING_NUMBER_DEFAULT_VALUE ? "There's no missing number" : "Missing number is - " + missingNumber);

        //task 2
        List<String> words = List.of("racecar", "peep", "noon", "tree", "cold");
        for (String word : words) {
            System.out.println("The word " + word + (isPalindrome(word) ? " is palindrome." : " is not palindrome"));
        }
    }

    private static boolean isPalindrome(String word) {
        var letters = word.toCharArray();

        for (int i = 0; i < letters.length / 2; i++) {
            if (!(letters[i] == letters[letters.length - i - 1])) {
                return false;
            }
        }
        return true;
    }

    private static int findMissingNumber(int[] numbers) {
        var sortArray = Arrays.stream(numbers).sorted().toArray();

        for (int i = 0; i < sortArray.length - 1; i++) {
            int nextNumber = sortArray[i + 1];

            if (nextNumber - sortArray[i] != 1) {
                return nextNumber - 1;
            }
        }
        return MISSING_NUMBER_DEFAULT_VALUE;
    }
}

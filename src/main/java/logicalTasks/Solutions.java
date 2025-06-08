package logicalTasks;

import org.apache.commons.lang3.ArrayUtils;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

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

        //task 3
        Node head = new Node(1);

        Node six = new Node(6);
        Node five = new Node(5);
        Node four = new Node(4);
        Node three = new Node(3);


        head.next = new Node(2);
        head.next.next = three;
        three.next = four;
        four.next = five;
        five.next = six;
        six.next = four;//create cycle //null - no cycle

        System.out.println(checkForCycle(head) ? "Has cycle" : "Does not contain cycle.");

        //Task 4
        String word = "Maggie";
        reverseWord(word);

        //Task 5
        int[] a = {1, 12, 15, 26, 38};
        int[] b = {2, 13, 17, 30, 45, 60};

        int[] a1 = {-5, 3, 6, 12, 15};
        int[] b1 = {-12, -10, -6, -3, 4, 10};

        int[] a2 = {};
        int[] b2 = {2, 4, 5, 6};

        System.out.println(findMedianInTwoArrays(a2, b2));

        //Task 6
        String wordTest6 = "treesstr";//"Fibonacci";//"consonants";
        findFirstNonRepeatingLetter(wordTest6);


    }

    private static void findFirstNonRepeatingLetter(String wordTest6) {
        List<String> letters = new CopyOnWriteArrayList<>(wordTest6.toLowerCase().split(""));
        List<String> temp = new ArrayList<>();

        Iterator<String> it = letters.iterator();
        while (it.hasNext()) {
            var letter = it.next();
            if (temp.contains(letter)) {
                temp.remove(letter);
                letters.remove(letter);
            } else {
                temp.add(letter);
            }
        }

        System.out.println(temp.isEmpty() ? "Does not contain non-repeating letter." : temp.get(0));
    }

    private static double findMedianInTwoArrays(int[] arrayFirst, int[] arraySecond) {
        int[] result = Arrays.stream(ArrayUtils.addAll(arrayFirst, arraySecond)).sorted().toArray();
        int size = result.length;

        return (size % 2 == 0) ? (double) (result[size / 2] + result[size / 2 - 1]) / 2 : result[size / 2];
    }

    private static void reverseWord(String word) {
        var letters = word.toCharArray();

        String reversedWord = "";
        for (int i = letters.length - 1; i >= 0; i--) {
            reversedWord += String.valueOf(letters[i]);
        }

        System.out.println(reversedWord);
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

    private static boolean isPalindrome(String word) {
        var letters = word.toCharArray();

        for (int i = 0; i < letters.length / 2; i++) {
            if (!(letters[i] == letters[letters.length - i - 1])) {
                return false;
            }
        }
        return true;
    }


    private static boolean checkForCycle(Node head) {
        Node node = head;
        Node twoStepMore = node.getNext().getNext();

        while (twoStepMore != null) {
            Node oneStepNode = node;
            oneStepNode.nodeIsChecked(true);

            if (twoStepMore.isTraversed()) {
                return true;
            }

            node = node.getNext();
            twoStepMore = node.getNext().getNext();

        }
        return false;
    }

    static class Node implements Checked {
        int data;
        Node next;
        boolean traversed;

        Node(int data) {
            this.data = data;
            Node nextNode = null;
        }

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }

        public Node getNext() {
            return next;
        }

        public boolean isTraversed() {
            return traversed;
        }

        @Override
        public void nodeIsChecked(boolean traversed) {
            this.traversed = traversed;
        }
    }
}

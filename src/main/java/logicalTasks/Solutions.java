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

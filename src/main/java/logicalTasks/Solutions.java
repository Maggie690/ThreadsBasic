package logicalTasks;

import org.apache.commons.lang3.ArrayUtils;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class Solutions {

    private static final int MISSING_NUMBER_DEFAULT_VALUE = -1;
    private static Map<Integer, Long> memorization = new HashMap<>(Map.of(0, 0l, 1, 1l, 2, 1l));

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


        //Task 7
        var firstWord = "doo";//"anagram";
        var secondWord = "dog";//"nagaram";

        System.out.println(checkTwoWordsAreAnagram(secondWord, firstWord) ? " anagrams" : "not anagrams");

        //Task 8
        int matrix[][] = {{1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}};


        int[][] matrix90 = rotate90Matrix(matrix);

        System.out.println(Arrays.deepToString(matrix));
        System.out.println(Arrays.deepToString(matrix90));

        //Task 9
        String word9 = "tosOlvealgorithmicproblems";

        HashSet<String> wordsEqual = new HashSet<>();
        wordsEqual = longestSubstring(word9.toLowerCase(), wordsEqual);

        wordsEqual.forEach(System.out::println);

        //Task 10
        int[] arr10 = {100, 200, 300, 400};
        int k = 4;

        int sum = subArrayMaxSum(arr10, k);
        System.out.println(sum);

        //Task 12
        int[] arraySorted = {12, 13, 14, 15, 16};
        int searchedElement = binarySearch(18, arraySorted);
        System.out.printf((searchedElement == -1 ? "Element does not exist.\n" : "The searched element is at position: %d\n"), searchedElement);

        //Task 13
        int[] arr0 = {1, 2, 1, 3, 1};
        int[] arr1 = {3, 1, 3, 4, 1};

        Set<Integer> sortedSet = intersectionArrays(arr0, arr1);
        sortedSet.forEach(num -> System.out.print(num + " "));
        System.out.println();

        //Task 14
        int fibNum = 10;
        System.out.println("\nFibonacci on position " + fibNum + " is: " + fibonacci(fibNum));

        long preTime = System.currentTimeMillis();
        System.out.printf("Fibonacci on position %s is %s\n", fibNum, fibonacciMemorization(fibNum));
        long postTime = System.currentTimeMillis();
        System.out.println("Time: " + (postTime - preTime));

        preTime = System.currentTimeMillis();
        System.out.printf("Fibonacci on position %s is %s\n", fibNum, fibonacciMemorization(fibNum));
        postTime = System.currentTimeMillis();
        System.out.println("Time: " + (postTime - preTime));

        //Task 15
        System.out.print("\nFibonacci for " + fibNum + " /recursive/: ");
        fib(fibNum + 1);

        System.out.print("\nFibonacci for " + fibNum + "  /dynamic/: ");
        calcFibonacciDynamic(fibNum + 1);
        System.out.println();

        //Таск 16
        LinkedList<Integer> numbers0 = new LinkedList<>(List.of(1, 2, 3, 78, 100));
        LinkedList<Integer> numbers1 = new LinkedList<>(List.of(4, 6, 7, 77, 104, 200, 300));

        sortTwoLinkedLists(numbers0, numbers1).forEach(num -> System.out.print(num + " "));

        //Таск 17
        int[] arr2 = {20, 25, 5, 7, 12, 18, 14};
        int searchedSum = 30;
        System.out.println("\nFor the searched sum of " + searchedSum + " the numbers are:");
        findSumOfElements(arr2, searchedSum);

        System.out.println(System.lineSeparator());

        //Task 20
        TreeMap<String, Integer> countLetters = countLetters(word);
        countLetters.forEach((key, value) -> System.out.println(key + " - " + value));
    }

    private static int[][] rotate90Matrix(int[][] matrix) {
        int col90 = matrix.length - 1;
        int row90 = 0;
        int matrix90[][] = new int[matrix.length][matrix[0].length];

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.println(matrix[row][col]);
                matrix90[row90][col90] = matrix[row][col];
                row90++;
            }
            col90--;
            row90 = 0;
        }

        return matrix90;
    }

    private static int subArrayMaxSum(int[] numbers, int k) {
        int[] numbersSorted = Arrays.stream(numbers).sorted().toArray();

        int sum = 0;
        for (int i = numbersSorted.length - 1; i >= 0 && k >= 0; i--) {
            sum += numbersSorted[i];
            k--;
        }
        return sum;
    }

    private static HashSet<String> longestSubstring(String word, HashSet<String> setEqualLength) {
        HashMap<Character, Integer> map = new HashMap<>();
        int index = 0, positionReturn = 0;

        while (index < word.length()) {

            Character letter = word.charAt(index);
            if (!map.containsKey(letter)) {
                map.put(letter, 1);
                index++;
                continue;
            }

            String subString = word.substring(positionReturn, index);

            if (setEqualLength.isEmpty() || setEqualLength.stream().iterator().next().length() < subString.length()) {
                setEqualLength = new HashSet<>();
                setEqualLength.add(subString);
            } else if (setEqualLength.stream().iterator().next().length() == subString.length()) {
                setEqualLength.add(subString);
            }

            map = new HashMap<>();
            positionReturn++;
            index = positionReturn;
        }
        return setEqualLength;
    }

    private static void findSumOfElements(int[] numbers, int searchedSum) {
        int tempSum = 0;
        for (int startIndex = 0; startIndex < numbers.length; startIndex++) {
            for (int currentIndex = startIndex; currentIndex < numbers.length; currentIndex++) {

                if (tempSum < searchedSum) {
                    tempSum += numbers[currentIndex];
                }

                if (tempSum >= searchedSum) {
                    if (tempSum == searchedSum) {
                        printResult(numbers, startIndex, currentIndex);
                    }
                    tempSum = 0;
                    break;
                }
            }
        }
    }

    private static void printResult(int[] numbers, int startIndex, int currentIndex) {
        StringBuilder sB = new StringBuilder(System.lineSeparator());
        for (int i = startIndex; i <= currentIndex; i++) {
            sB.append(numbers[i]).append(" ");
        }
        System.out.print(sB);
    }

    /**
     * Recursive
     * The Fibonacci sequence is the series of numbers where each number is the sum of the two preceding numbers.
     * For example, 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610,
     *
     * @param fibonacciNumber
     */
    private static void fib(int fibonacciNumber) {
        System.out.print(1 + " ");
        calcFibonacci(fibonacciNumber, 1, 1);
    }

    /**
     * This method works well for small values of n,
     * but it becomes increasingly slow for larger values
     * because it recalculates the same Fibonacci numbers many times.
     *
     * @param number
     * @return int
     */
    private static int fibonacci(int number) {
        if (number <= 0) {
            return 0;
        } else if (number == 1) {
            return 1;
        } else {
            return fibonacci(number - 1) + fibonacci(number - 2);
        }
    }

    private static int calcFibonacci(int fib, int firstNumber, int secondNumber) {
        if (fib == 1 || fib == 2) {
            return 1;
        }
        System.out.print(secondNumber + " ");
        return calcFibonacci(fib - 1, secondNumber, firstNumber + secondNumber);
    }

    /**
     * Dynamic
     * The Fibonacci sequence is the series of numbers where each number is the sum of the two preceding numbers.
     * For example, 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610,
     *
     * @param fibNum
     * @return
     */
    private static int calcFibonacciDynamic(int fibNum) {
        int n0 = 0, n1 = 1, n2 = 0, i;
        System.out.print(n0 + n1);
        for (i = 2; i < fibNum; i++) {
            n2 = n0 + n1;
            System.out.print(" " + n2);
            n0 = n1;
            n1 = n2;
        }

        return n2;
    }

    /**
     *
     * Calculating factorials recursively without memoization is inefficient for large n because it involves recomputing
     * the fibonacci of numbers multiple times. By using memoization, each factorial value is computed only once.
     * @param num
     * @return
     */
    private static long fibonacciMemorization(int num) {
        if (num <= 1) {
            return num;
        }

        if (memorization.containsKey(num)) {
            return memorization.get(num);
        }

        long result = fibonacciMemorization(num - 1) + fibonacciMemorization(num - 2);
        memorization.put(num, result);

        return result;

    }

    /**
     * Binary search trees, AVL trees, and other tree-based data structures can also provide efficient searching
     * with an average time complexity of O(log n). However, they require additional memory to store the tree structure.
     * Not suitable for small data sets, as the overhead of binary search may outweigh the benefits compared to linear search.
     *
     * @param target
     * @param arr
     * @return position in array for searched element
     */
    private static int binarySearch(int target, int[] arr) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    private static Set<Integer> intersectionArrays(int[] numsLeft, int[] numsRight) {
        Set<Integer> setLeft = Arrays.stream(numsLeft).boxed().collect(Collectors.toSet());
        Set<Integer> setRight = Arrays.stream(numsRight).boxed().collect(Collectors.toSet());
        setLeft.retainAll(setRight);

        return setLeft;
    }

    private static LinkedList<Integer> sortTwoLinkedLists(LinkedList<Integer> numbers0, LinkedList<Integer> numbers1) {
        var sortedList = numbers0.stream()
                .collect(Collectors.toCollection(() -> numbers1))
                .stream()
                .sorted()
                .toList();

        return new LinkedList<>(sortedList);
    }

    private static boolean checkTwoWordsAreAnagram(String secondWord, String firstWord) {
        if (secondWord.length() != firstWord.length()) {
            return false;
        }

        TreeMap<String, Integer> countFirstWord = countLetters(firstWord);
        TreeMap<String, Integer> countSecondWord = countLetters(secondWord);

        if (countSecondWord.size() != countFirstWord.size()) {
            return false;
        }

        for (var pair : countSecondWord.entrySet()) {
            var key = pair.getKey();
            var value = pair.getValue();

            if (!countFirstWord.containsKey(key) && countFirstWord.get(key) != value) {
                return false;
            }
        }
        return true;
    }

    private static TreeMap<String, Integer> countLetters(String word) {
        TreeMap<String, Integer> map = new TreeMap<>();
        for (var letter : Arrays.stream(word.split("")).toList()) {
            map.compute(letter, (k, v) -> (v == null ? 1 : map.get(letter) + 1));
        }

        return map;
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

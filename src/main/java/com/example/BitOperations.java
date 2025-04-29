package com.example;

public class BitOperations {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 6, 7};
        printMissingNumberXOR(numbers);
    }

    /**
     * Rules:
     * XOR of two same numbers is always 0 i.e. a ^ a = 0.
     * XOR of a number with 0 will result in the number itself i.e. 0 ^ a = a.
     *
     * @param numbers
     */
    private static void printMissingNumberXOR(int[] numbers) {
        int xorNum = 1, xorIndex = 1;
        for (int i = 0; i < numbers.length - 1; i++) {
            xorNum = xorNum ^ numbers[i];
            xorIndex = xorIndex ^ (i + 1);
        }

        xorNum = xorNum ^ numbers.length;   // (1^2^3^4^6^7) ^ (1^2^3^4^5,6)
                                            //(1^1)^(2^2)^(3^3)^(4^4)^(5)^(6^6)^(7^7)
                                            //0^0^0^0^5^0^0
        System.out.println(xorNum ^ xorIndex);//0^5 = 5.

    }
}

package day04;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{
        Scanner in = new Scanner(new File("src/day04/input.txt"));
        String line = in.nextLine();
        int min = Integer.parseInt(line.split("-")[0]);
        int max = Integer.parseInt(line.split("-")[1]);

        System.out.println(part1(min, max));
        System.out.println(part2(min, max));
    }

    private static int part1(int min, int max) {
        int val = min;
        int amount = 0;
        while (val <= max) {
            //System.out.println(val);
            int[] numbers = Arrays.stream(Integer.toString(val).split("")).mapToInt(Integer::parseInt).toArray();
            boolean equals = false;
            boolean smaller = true;
            for (int i = 0; i + 1 < numbers.length; i++) {
                if (numbers[i] == numbers[i+1]) {
                        equals = true;
                }
                if (numbers[i] > numbers[i+1]) {
                    smaller = false;
                    break;
                }
            }
            if (equals && smaller) {
                amount++;
            }
            val++;
        }
        return amount;

    }

    private static int part2(int min, int max) {
        int val = min;
        int amount = 0;
        while (val <= max) {
            //System.out.println(val);
            int[] numbers = Arrays.stream(Integer.toString(val).split("")).mapToInt(Integer::parseInt).toArray();
            boolean equals = false;
            boolean smaller = true;
            for (int i = 0; i + 1 < numbers.length; i++) {
                if (numbers[i] == numbers[i+1]) {
                    boolean b = true;
                    if (i > 0) {
                        if (numbers[i] == numbers[i-1]) {
                            b = false;
                        }
                    }

                    if (i + 2 < numbers.length ) {
                        if (numbers[i] == numbers[i+2]) {
                            b = false;
                        }
                    }
                    if (b) {
                        equals = true;
                    }
                }
                if (numbers[i] > numbers[i+1]) {
                    smaller = false;
                    break;
                }
            }
            if (equals && smaller) {
                amount++;
            }
            val++;
        }
        return amount;

    }
}

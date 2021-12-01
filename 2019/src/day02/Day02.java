package day02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day02 {

    static String input = "src/day02/input.txt";

    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(new File(input));

        ArrayList<Integer> arrList = new ArrayList<Integer>();
        String inStr;

        inStr = in.nextLine();
        int[] arr = Arrays.stream(inStr.split(",")).mapToInt(Integer::parseInt).toArray();

        System.out.println("Part 1:" + part1( Arrays.stream(inStr.split(",")).mapToInt(Integer::parseInt).toArray(), 12, 2));
        System.out.println("Part 2:" + part2( Arrays.stream(inStr.split(",")).mapToInt(Integer::parseInt).toArray()));
    }

    private static int part1(int[] arr, int x, int y) {
        int i = 0;
        arr[1] = x;
        arr[2] = y;
        while (arr[i] != 99) {
            if(arr[i] == 1){
                arr[arr[i+3]] = arr[arr[i+2]] + arr[arr[i+1]];
            } else if(arr[i] == 2) {
                arr[arr[i+3]] = arr[arr[i+2]] * arr[arr[i+1]];
            }
            i += 4;
        }
        return arr[0];
    }

    private static int part2(int[] arr) {
        int result = 0;
        int x, y = 0;
        for (x = 0; x <= 99; x++) {
            for (y = 0; y <= 99; y++) {
                result = part1(Arrays.copyOf(arr, arr.length), x, y);
                if (result == 19690720) {
                    return 100 * x + y;
                }
            }
        }
        return -1;
    }
}

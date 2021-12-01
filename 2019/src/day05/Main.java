package day05;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static String input = "src/day05/input.txt";

    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(new File(input));

        ArrayList<Integer> arrList = new ArrayList<>();
        String inStr;

        inStr = in.nextLine();
        int[] arr = Arrays.stream(inStr.split(",")).mapToInt(Integer::parseInt).toArray();

        solve(Arrays.stream(inStr.split(",")).mapToInt(Integer::parseInt).toArray(), 1);
        solve(Arrays.stream(inStr.split(",")).mapToInt(Integer::parseInt).toArray(), 5);
    }

    private static int solve(int[] arr, int in) {
        int i = 0;
        int pipe = in;
        int[] val = Arrays.stream(Integer.toString(arr[i]).split("")).mapToInt(Integer::parseInt).toArray();
        int len = val.length;
        while (arr[i] != 99) {

            boolean[] mode = new boolean[len + 4];
            for (int j = 0; j < len - 2; j++) {
                mode[j] = val[len - (3 + j)] == 1;
            }

            if (arr[i] % 100 == 1) {
                int[] positions = getVals(i, 3, arr, mode);
                arr[positions[3]] = arr[positions[2]] + arr[positions[1]];
                i += 4;
            } else if (arr[i] % 100 == 2) {
                int[] positions = getVals(i, 3, arr, mode);
                arr[positions[3]] = arr[positions[2]] * arr[positions[1]];
                i += 4;
            } else if (arr[i] % 100 == 3) {
                int[] positions = getVals(i, 1, arr, mode);
                arr[positions[1]] = pipe;
                i += 2;
            } else if (arr[i] % 100 == 4) {
                int[] positions = getVals(i, 1, arr, mode);
                pipe = arr[positions[1]];
                i += 2;
                System.out.println(pipe);
            } else if (arr[i] % 100 == 5) {
                int[] positions = getVals(i, 2, arr, mode);
                if (arr[positions[1]] != 0) {
                    i = arr[positions[2]];
                } else {
                    i += 3;
                }
            } else if (arr[i] % 100 == 6) {
                int[] positions = getVals(i, 2, arr, mode);
                if (arr[positions[1]] == 0) {
                    i = arr[positions[2]];
                } else {
                    i += 3;
                }
            } else if (arr[i] % 100 == 7) {
                int[] positions = getVals(i, 3, arr, mode);
                arr[positions[3]] = arr[positions[1]] < arr[positions[2]] ? 1 : 0;
                i += 4;
            } else if (arr[i] % 100 == 8) {
                int[] positions = getVals(i, 3, arr, mode);
                arr[positions[3]] = arr[positions[1]] == arr[positions[2]] ? 1 : 0;
                i += 4;
            }


            val = Arrays.stream(Integer.toString(arr[i]).split("")).mapToInt(Integer::parseInt).toArray();
            len = val.length;
        }
        return arr[0];
    }

    private static int[] getVals(int offset, int i, int[] arr, boolean[] mode) {
        int[] out = new int[i + 1];
        for (int j = 0; j < i; j++) {
            if (mode[j]) {
                out[j + 1] = offset + j + 1;
            } else {
                out[j + 1] = arr[offset + j + 1];
            }
        }
        return out;
    }
}
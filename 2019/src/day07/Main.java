package day07;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static String input = "src/day07/input.txt";

    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(new File(input));

        String inStr = in.nextLine();

        System.out.println(solve1(Arrays.stream(inStr.split(",")).mapToInt(Integer::parseInt).toArray()));
        System.out.println(solve2(Arrays.stream(inStr.split(",")).mapToInt(Integer::parseInt).toArray()));
    }


    private static int solve1(int[] arr) {
        int[] order = {0,1,2,3,4};
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < 120; i++) {

            int out = 0;
            int[] in = new int[2];


            for (int j = 0; j < 5; j++) {

                in[0] = order[j];
                in[1] = out;

                out = simulate(arr, in, 0)[1][0];

            }

            if (out > max) {
                max = out;
            }

            Permutation.nextPermutation(order);
        }
        return max;
    }

    private static int solve2(int[] arr) {
        int[] order = {5,6,7,8,9};
        int max = Integer.MIN_VALUE;
        int lastOut = Integer.MIN_VALUE;

        for (int i = 0; i < 120; i++) {
            int[] positions = new int[]{0,0,0,0,0};
            int[][] arrays = new int[][] {Arrays.copyOf(arr, arr.length), Arrays.copyOf(arr, arr.length),
                    Arrays.copyOf(arr, arr.length), Arrays.copyOf(arr, arr.length),
                    Arrays.copyOf(arr, arr.length)};

            int out = 0;
            int[] in = new int[2];
            int halted = 0;

            int j = 0;
            boolean isFirstRound = true;


            while (halted == 0) {

                if(isFirstRound) {
                    in[0] = order[j];
                    in[1] = out;
                } else {
                    in[0] = out;
                    in[1] = 0;
                }



                int[][] returnVal = simulate(arrays[j], in, positions[j]);
                arrays[j] = returnVal[0];
                out = returnVal[1][0];
                positions[j] = returnVal[1][1];
                halted = returnVal[1][2];

                j++;
                if (j >4) {
                    isFirstRound = false;
                    lastOut = out;
                    j = j % 5;
                }
            }

            if (lastOut > max) {
                max = lastOut;
                int[] maxSettings = Arrays.copyOf(order, order.length);
            }

            Permutation.nextPermutation(order);
        }
        return max;
    }

    private static int[][] simulate(int[] arr, int[] in, int pos) {
        int index = pos;
        int inputCounter = 0;

        int out = Integer.MIN_VALUE;

        int halted = 0;

        int[] val = Arrays.stream(Integer.toString(arr[index]).split("")).mapToInt(Integer::parseInt).toArray();
        int len = val.length;
        while (halted == 0) {

            boolean[] mode = new boolean[len + 4];
            for (int j = 0; j < len - 2; j++) {
                mode[j] = val[len - (3 + j)] == 1;
            }

            if (arr[index] % 100 == 1) {
                int[] positions = getVals(index, 3, arr, mode);
                arr[positions[3]] = arr[positions[2]] + arr[positions[1]];
                index += 4;
            } else if (arr[index] % 100 == 2) {
                int[] positions = getVals(index, 3, arr, mode);
                arr[positions[3]] = arr[positions[2]] * arr[positions[1]];
                index += 4;
            } else if (arr[index] % 100 == 3) {
                int[] positions = getVals(index, 1, arr, mode);
                arr[positions[1]] = in[inputCounter];
                inputCounter++;
                index += 2;
            } else if (arr[index] % 100 == 4) {
                int[] positions = getVals(index, 1, arr, mode);
                out = arr[positions[1]];
                index += 2;
            } else if (arr[index] % 100 == 5) {
                int[] positions = getVals(index, 2, arr, mode);
                if (arr[positions[1]] != 0) {
                    index = arr[positions[2]];
                } else {
                    index += 3;
                }
            } else if (arr[index] % 100 == 6) {
                int[] positions = getVals(index, 2, arr, mode);
                if (arr[positions[1]] == 0) {
                    index = arr[positions[2]];
                } else {
                    index += 3;
                }
            } else if (arr[index] % 100 == 7) {
                int[] positions = getVals(index, 3, arr, mode);
                arr[positions[3]] = arr[positions[1]] < arr[positions[2]] ? 1 : 0;
                index += 4;
            } else if (arr[index] % 100 == 8) {
                int[] positions = getVals(index, 3, arr, mode);
                arr[positions[3]] = arr[positions[1]] == arr[positions[2]] ? 1 : 0;
                index += 4;
            }

            if (out != Integer.MIN_VALUE) {
                break;
            }

            val = Arrays.stream(Integer.toString(arr[index]).split("")).mapToInt(Integer::parseInt).toArray();
            len = val.length;
            if (arr[index] == 99) {
                halted = 1;
            }
        }
        return new int[][]{arr, {out, index, halted}};
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
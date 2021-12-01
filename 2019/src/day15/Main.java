package day15;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


public class Main {

    private static String input = "src/day15/input.txt";

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File(input));

        String inStr = in.nextLine();
        long[] arr = Arrays.stream(inStr.split(",")).mapToLong(Long::parseLong).toArray();


    }

    private static long solve(long[] arr, int in) {
        int i = 0;
        long pipe = in;
        long[] val = Arrays.stream(Long.toString(arr[i]).split("")).mapToLong(Long::parseLong).toArray();
        int len = val.length;

        int base = 0;

        while (arr[i] % 100 != 99) {

            long[] mode = new long[len + 4];
            for (int j = 0; j < len - 2; j++) {
                mode[j] = val[len - (3 + j)];
            }
            int[] positions = getVals(i, 4, arr, mode, base);
            if (arr[i] % 100 == 1) {
                arr[positions[3]] = arr[positions[2]] + arr[positions[1]];
                i += 4;
            } else if (arr[i] % 100 == 2) {
                arr[positions[3]] = arr[positions[2]] * arr[positions[1]];
                i += 4;
            } else if (arr[i] % 100 == 3) {
                arr[positions[1]] = pipe;
                i += 2;
            } else if (arr[i] % 100 == 4) {
                pipe = arr[positions[1]];
                i += 2;
                System.out.println(pipe);
            } else if (arr[i] % 100 == 5) {
                if (arr[positions[1]] != 0) {
                    i = (int)arr[positions[2]];
                } else {
                    i += 3;
                }
            } else if (arr[i] % 100 == 6) {
                if (arr[positions[1]] == 0) {
                    i = (int)arr[positions[2]];
                } else {
                    i += 3;
                }
            } else if (arr[i] % 100 == 7) {
                arr[positions[3]] = arr[positions[1]] < arr[positions[2]] ? 1 : 0;
                i += 4;
            } else if (arr[i] % 100 == 8) {
                arr[positions[3]] = arr[positions[1]] == arr[positions[2]] ? 1 : 0;
                i += 4;
            } else if (arr[i] % 100 == 9) {
                base += arr[positions[1]];
                i+=2;
            }


            val = Arrays.stream(Long.toString(arr[i]).split("")).mapToLong(Long::parseLong).toArray();
            len = val.length;
        }
        return pipe;
    }

    private static int[] getVals(int offset, int i, long[] arr, long[] mode, int base) {
        int[] out = new int[i + 1];
        for (int j = 0; j < i; j++) {
            if (mode[j] == 0) {
                out[j + 1] = (int)arr[offset + j + 1];
            } else if (mode[j] == 1) {
                out[j + 1] = offset + j + 1;
            } else if (mode[j] == 2) {
                out[j+1] = base + (int)arr[offset + j + 1];
            }
        }
        return out;
    }
}

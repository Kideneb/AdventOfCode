package day08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static String input = "src/day08/input.txt";

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File(input));


        String inStr = in.nextLine();

        int[] arr = Arrays.stream(inStr.split("")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solve1(arr, 25, 6));
        System.out.println(solve2(arr, 25, 6));
    }

    private static String solve2(int[] arr, int width, int height) {
        int[][] picture = new int[width][height];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                picture[j][i] = 2;
            }
        }

        int offset = 0;
        while(offset < arr.length) {

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (picture[j][i] == 2 && arr[offset + i * width + j] != 2) {
                        picture[j][i] = arr[offset + i * width + j];
                    }
                }
            }


            offset += width * height;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                sb.append(picture[j][i]);
            }
            sb.append("\n");
        }


        return sb.toString();
    }

    private static int solve1(int[] arr, int width, int height) {
        int minZeros = Integer.MAX_VALUE;
        int minVal = Integer.MAX_VALUE;
        int j = 0;

        while(j < arr.length / (width * height)) {
            int zeros = 0;
            int ones = 0;
            int twos = 0;

            for (int i = 0; i < width * height; i++) {
                int num = arr[j * (width * height) + i];
                if (num == 0) {
                    zeros++;
                } else if (num ==  1) {
                    ones++;
                } else if (num == 2) {
                    twos++;
                }
            }

            if (zeros < minZeros) {
                minZeros = zeros;
                minVal = ones * twos;
            }
            j++;
        }
        return minVal;
    }
}

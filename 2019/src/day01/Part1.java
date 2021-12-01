package tag01;

import java.io.*;
import java.util.Scanner;

public class Part1 {

    public static void main(String[] args) throws Exception{
        System.out.println(System.getProperty("user.dir"));
        File file = new File("src/day01/input.txt");

        Scanner in = new Scanner(file);

        int sum = 0;

        while(in.hasNext()) {
            int i = in.nextInt();
            sum += ((i/3)-2);
        }
        System.out.println(sum);
    }

}

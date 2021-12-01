package tag01;

import java.io.File;
import java.util.Scanner;

public class Part2 {
    public static void main(String[] args) throws Exception{
        System.out.println(System.getProperty("user.dir"));
        File file = new File("src/day01/input.txt");

        Scanner in = new Scanner(file);

        int sum = 0;

        while(in.hasNext()) {
            int i = in.nextInt();
            while (i >= 0) {
                i = ((i/3)-2);
                if(i > 0) {
                    sum += i;
                }
            }
        }
        System.out.println(sum);
    }
}

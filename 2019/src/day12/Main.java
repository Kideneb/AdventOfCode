package day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static String input = "src/day12/input.txt";

    private static int MOON_AMOUNT = 4;
    private static long SIMULATION_COUNT = 1000;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File(input));

        Moon[] moons1 = new Moon[MOON_AMOUNT];
        Moon[] moons2 = new Moon[MOON_AMOUNT];

        int i = 0;
        while(in.hasNext()) {
            String line = in.nextLine();
            line = line.replaceAll("<|>|\\p{Lower}|\\p{Blank}|=", "");
            int[] pos1 = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
            int[] pos2 = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
            moons1[i] = new Moon(pos1, new int[] {0,0,0});
            moons2[i] = new Moon(pos2, new int[] {0,0,0});
            i++;
        }
        System.out.println(solve1(moons1));
        System.out.println(solve2(moons2));

    }

    private static int solve1(Moon[] moons) {

        for (long i = 0; i < SIMULATION_COUNT; i++) {
            simulateStep(moons);
        }
        return calculateTotalEngery(moons);
    }

    private static long solve2(Moon[] moons) {
        int[] xVals = new int[4];
        int[] yVals = new int[4];
        int[] zVals = new int[4];

        for (int j = 0; j < moons.length; j++) {
            xVals[j] = moons[j].getPos()[0];
            yVals[j] = moons[j].getPos()[1];
            zVals[j] = moons[j].getPos()[2];
        }

        Moon[] copiedMoons = new Moon[] {moons[0].getCopy(), moons[1].getCopy(), moons[2].getCopy(), moons[3].getCopy()};
        long xi =0, yi = 0, zi = 0;
        do{
            simulateStep(copiedMoons);
            xi++;
        } while (!isEqualX(copiedMoons, xVals));

        copiedMoons = new Moon[] {moons[0].getCopy(), moons[1].getCopy(), moons[2].getCopy(), moons[3].getCopy()};

        do{
            simulateStep(copiedMoons);
            yi++;
        } while (!isEqualY(copiedMoons, yVals));

        copiedMoons = new Moon[] {moons[0].getCopy(), moons[1].getCopy(), moons[2].getCopy(), moons[3].getCopy()};

        do{
            simulateStep(copiedMoons);
            zi++;
        } while (!isEqualZ(copiedMoons, zVals));

        long temp = lcm(xi, yi);
        return lcm(temp, zi);
    }

    private static boolean isEqualX(Moon[] moons, int[] vals) {
        for (int j = 0; j < moons.length; j++) {
            if (vals[j] != moons[j].getPos()[0]) {
                return false;
            }
            if (moons[j].getVel()[0] != 0) {
                return false;
            }
        }
        return true;
    }

    private static boolean isEqualY(Moon[] moons, int[] vals) {
        for (int j = 0; j < moons.length; j++) {
            if (vals[j] != moons[j].getPos()[1]) {
                return false;
            }
            if (moons[j].getVel()[1] != 0) {
                return false;
            }
        }
        return true;
    }

    private static boolean isEqualZ(Moon[] moons, int[] vals) {
        for (int j = 0; j < moons.length; j++) {
            if (vals[j] != moons[j].getPos()[2]) {
                return false;
            }
            if (moons[j].getVel()[2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static long lcm(long number1, long number2) {
        if (number1 == 0 || number2 == 0) {
            return 0;
        }
        long absNumber1 = Math.abs(number1);
        long absNumber2 = Math.abs(number2);
        long absHigherNumber = Math.max(absNumber1, absNumber2);
        long absLowerNumber = Math.min(absNumber1, absNumber2);
        long lcm = absHigherNumber;
        while (lcm % absLowerNumber != 0) {
            lcm += absHigherNumber;
        }
        return lcm;
    }

    private static int calculateTotalEngery(Moon[] moons) {
        int energy = 0;
        for (Moon moon : moons) {
            int[] pos = moon.getPos();
            int[] vel = moon.getVel();
            int posSum = 0, velSum = 0;
            for (int i = 0; i < pos.length; i++) {
                posSum += Math.abs(pos[i]);
                velSum += Math.abs(vel[i]);
            }
            energy += posSum * velSum;
        }
        return energy;
    }

    private static void simulateStep(Moon[] moons) {
        for (int i = 0; i < moons.length; i++) {
            for (int j = i + 1; j < moons.length; j++) {

                Moon moonA = moons[i];
                Moon moonB = moons[j];
                changeVels(moonA, moonB);

            }
        }
        changePos(moons);
    }

    private static void changeVels(Moon moonA, Moon moonB) {
        int[] posA = moonA.getPos();
        int[] posB = moonB.getPos();
        int[] velA = moonA.getVel();
        int[] velB = moonB.getVel();

        for (int k = 0; k < posA.length; k++) {
            if (posA[k] > posB[k]) {
                velA[k] = velA[k] - 1;
                velB[k] = velB[k] + 1;
            } else if (posA[k] < posB[k]) {
                velA[k] = velA[k] + 1;
                velB[k] = velB[k] - 1;
            }
        }
    }

    private static void changePos(Moon[] moons) {
        for (Moon moon : moons) {
            int[] pos = moon.getPos();
            int[] vel = moon.getVel();
            for (int j = 0; j < pos.length; j++) {
                pos[j] = pos[j] + vel[j];
            }
        }
    }

}

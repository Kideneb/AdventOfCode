package day03;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception{
        Scanner in = new Scanner(new File("src/day03/input.txt"));

        String w1Str = in.nextLine();
        String w2Str = in.nextLine();
        String[] w1Order = w1Str.split(",");
        String[] w2Order = w2Str.split(",");
        ArrayList<Coord2D> wire1 = getPath(w1Order);
        ArrayList<Coord2D> wire2 = getPath(w2Order);

        int[] solution = solve(wire1, wire2);

        System.out.println("Part 1: " + solution[0] + "\nPart 2: " + solution[1]);

    }

    private static int[] solve(ArrayList<Coord2D> wire1, ArrayList<Coord2D> wire2) {
        int minDist = 99999;
        int minWalk = 99999;

        for(int i = 1; i < wire1.size(); i++) {
            Coord2D elem1 = wire1.get(i);
            for(int j = 1; j < wire2.size(); j++) {
                Coord2D elem2 = wire2.get(j);
                if (elem1.equals(elem2)) {
                    int dist = Math.abs(elem1.getX()) + Math.abs(elem2.getY());
                    if (dist < minDist) {
                        minDist = dist;
                    }
                    int walk = i +j;
                    if (walk < minWalk) {
                        minWalk = walk;
                    }
                }
            }
        }
        int[] out = {minDist, minWalk};
        return out;

    }

    private static ArrayList<Coord2D> getPath(String[] wOrder) {
        ArrayList<Coord2D> wire = new ArrayList<Coord2D>();
        wire.add(new Coord2D(0,0));
        for (String elem : wOrder) {
            char direction = elem.charAt(0);
            int partLength = Integer.parseInt(elem.substring(1));

            Coord2D offset;
            switch (direction) {
                case 'U':
                    offset = new Coord2D(0, 1);
                    break;
                case 'L':
                    offset = new Coord2D(-1, 0);
                    break;
                case 'R':
                    offset = new Coord2D(1, 0);
                    break;
                case 'D':
                    offset = new Coord2D(0, -1);
                    break;
                default:
                    throw new IllegalStateException("State this code ist not supposed to get into.");
            }
            for (; partLength > 0; partLength--) {
                Coord2D lastElem = wire.get(wire.size()-1);
                wire.add(lastElem.add(offset));
            }

        }

        return wire;
    }


}

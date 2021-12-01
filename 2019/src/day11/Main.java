package day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static String input = "src/day11/input.txt";

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File(input));

        String inStr = in.nextLine();
        long[] arr = Arrays.stream(inStr.split(",")).mapToLong(Long::parseLong).toArray();
        arr = Arrays.copyOf(arr, 40000000);

        HashMap<Coord2D, Integer> field = new HashMap<>();

        System.out.println(solve(arr, field, 0).keySet().size());
        System.out.println(solve2(arr, field, 1));


    }

    private static String solve2(long[] arr, HashMap<Coord2D, Integer> field, int color) {
        HashMap<Coord2D, Integer> solved = solve(arr, field, color);

        int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE, minY = Integer.MAX_VALUE, maxY  = Integer.MIN_VALUE;

        for(Coord2D tile : solved.keySet()) {
            int x = tile.getX();
            int y = tile.getY();
            if (x < minX) {
                minX = x;
            }
            if (x > maxX) {
                maxX = x;
            }
            if (y < minY) {
                minY = y;
            }
            if (y > maxY) {
                maxY = y;
            }
        }

        int[][] pic = new int[maxX-minX + 1][maxY-minY + 1];
        for (Coord2D tile : solved.keySet()) {
            pic[tile.getX() - minX][tile.getY() - minY] = solved.get(tile);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = maxY-minY; i >= 0; i--) {
            for (int j = 0; j < maxX - minX + 1; j++ ) {
                if (pic[j][i] == 1) {
                    sb.append("░");
                } else {
                    sb.append("█");
                }
            }
            sb.append("\n");
        }
        sb.append("\n\n\n");
        return sb.toString();
    }

    private static HashMap<Coord2D, Integer> solve(long[] arr, HashMap<Coord2D, Integer> field, int startColor) {
        HashMap<Coord2D, Integer> editField = (HashMap<Coord2D, Integer>) field.clone();
        int i = 0;
        Coord2D position = new Coord2D(0,0);
        editField.put(position, startColor);
        Coord2D direction = new Coord2D(0,1);
        long pipe = editField.get(position);
        long[] val = Arrays.stream(Long.toString(arr[i]).split("")).mapToLong(Long::parseLong).toArray();
        int len = val.length;

        int base = 0;
        //Check if its the first or second robot output
        boolean firstOutput = true;

        while (arr[i] % 100 != 99) {

            //Get current color
            pipe = editField.get(position);


            //Split instruction and get modes of parameters
            long[] mode = new long[len + 4];
            for (int j = 0; j < len - 2; j++) {
                mode[j] = val[len - (3 + j)];
            }
            //Get values based on parameter modes
            int[] positions = getVals(i, 4, arr, mode, base);
            //Addition
            if (arr[i] % 100 == 1) {
                arr[positions[3]] = arr[positions[2]] + arr[positions[1]];
                i += 4;
            }
            // Multiplication
            else if (arr[i] % 100 == 2) {
                arr[positions[3]] = arr[positions[2]] * arr[positions[1]];
                i += 4;
            }
            // Input
            else if (arr[i] % 100 == 3) {
                arr[positions[1]] = pipe;
                i += 2;
            }
            // Output
            else if (arr[i] % 100 == 4) {
                long in = arr[positions[1]];
                if (firstOutput) {
                    editField.replace(position, (int) in);
                    firstOutput = false;
                } else {
                    firstOutput = true;
                    direction = getDirection(direction, in);
                    position = position.add(direction);
                    boolean contains = false;
                    for (Coord2D coord2D : editField.keySet()) {
                        if (coord2D.getX() == position.getX() && coord2D.getY() == position.getY()) {
                            position = coord2D;
                            contains = true;
                            break;
                        }
                    }
                    if(!contains) {
                        editField.put(position, 0);
                    }
                }
                //pipe = arr[positions[1]];
                i += 2;
                //System.out.println(pipe);
            }
            //Jump if not zero
            else if (arr[i] % 100 == 5) {
                if (arr[positions[1]] != 0) {
                    i = (int)arr[positions[2]];
                } else {
                    i += 3;
                }
            }
            //Jump if zero
            else if (arr[i] % 100 == 6) {
                if (arr[positions[1]] == 0) {
                    i = (int)arr[positions[2]];
                } else {
                    i += 3;
                }
            }
            //Jump if first is less than second
            else if (arr[i] % 100 == 7) {
                arr[positions[3]] = arr[positions[1]] < arr[positions[2]] ? 1 : 0;
                i += 4;
            }
            //Jump if first and second are equal
            else if (arr[i] % 100 == 8) {
                arr[positions[3]] = arr[positions[1]] == arr[positions[2]] ? 1 : 0;
                i += 4;
            }
            //Change instruction offset
            else if (arr[i] % 100 == 9) {
                base += arr[positions[1]];
                i+=2;
            }


            val = Arrays.stream(Long.toString(arr[i]).split("")).mapToLong(Long::parseLong).toArray();
            len = val.length;
        }
        return editField;
    }

    private static Coord2D getDirection(Coord2D direction, long rotation) {
        if (rotation == 0) {
            if (direction.getX() == 0) {
                if (direction.getY() == 1) {
                    direction = new Coord2D(-1, 0);
                } else {
                    direction = new Coord2D(1, 0);
                }
            } else if (direction.getX() == 1){
                direction = new Coord2D(0, 1);
            } else {
                direction = new Coord2D(0, -1);
            }
        } else {
            if (direction.getX() == 0) {
                if (direction.getY() == 1) {
                    direction = new Coord2D(1, 0);
                } else {
                    direction = new Coord2D(-1, 0);
                }
            } else if (direction.getX() == 1){
                direction = new Coord2D(0, -1);
            } else {
                direction = new Coord2D(0, 1);
            }
        }
        return direction;
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

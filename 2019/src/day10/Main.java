package day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    private static String input = "src/day10/input.txt";

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File(input));

        String[] lines = new String[100];

        int height = 0, width = 0;
        int i = 0;
        for (i = 0; in.hasNextLine(); i++) {
            lines[i] = in.nextLine();
        }
        height = i;
        width = lines[0].length();

        HashSet<Coord2D> asteroids = new HashSet<Coord2D>();
        for(i = 0; i < height; i++) {
            String[] line = lines[i].split("");
            for (int j = 0; j < width; j++) {
                if(line[j].equals("#")) {
                    asteroids.add(new Coord2D(j, i));
                }
            }
        }

        System.out.println(solve1(asteroids)[2]);
        System.out.println(solve2(asteroids));
    }

    private static int solve2(HashSet<Coord2D> asteroids) {
        int[] sol1 = solve1(asteroids);
        int destroyedCount = 0;

        Coord2D lastDestroyed = new Coord2D(0,0);
        Coord2D lastDestroyedDist = new Coord2D(0,0);
        Coord2D laser = new Coord2D(sol1[0], sol1[1]);

        while(destroyedCount < 200) {
            HashSet<Coord2D> visible = getVisible(laser, asteroids);
            if (destroyedCount + visible.size() < 200) {
                asteroids.removeAll(visible);
            } else {
                HashMap<Coord2D, Coord2D> distances = getDistances(laser, visible);
                int size = distances.size();
                for (int i = 0; i < size; i++) {
                    Coord2D smallestAst = new Coord2D(0,0);
                    Coord2D smallestAstDist = new Coord2D(-1, Integer.MAX_VALUE / 10);
                    int smallestAstQuarter = 5;
                    for (Coord2D ast : distances.keySet()) {
                        Coord2D astDist = distances.get(ast);
                        int x = astDist.getX();
                        int y = astDist.getY();
                        int astQuarter = getQuarter(astDist);
                        if (astQuarter < smallestAstQuarter) {
                            smallestAst = ast;
                            smallestAstDist = distances.get(ast);
                            smallestAstQuarter = astQuarter;
                        } else if (astQuarter == smallestAstQuarter) {
                            double astFrac, smallestAstFrac;
                            if (y != 0) {
                                astFrac = (double) x / (double) y;
                            } else {
                                astFrac = (double) 30;
                            }
                            if (smallestAstDist.getY() != 0) {
                                smallestAstFrac = (double) smallestAstDist.getX() / (double) smallestAstDist.getY();
                            } else {
                                smallestAstFrac = (double) 30;
                            }
                            if (astFrac > smallestAstFrac) {
                                smallestAst = ast;
                                smallestAstDist = distances.get(ast);
                            }
                        }
                    }
                    lastDestroyed = smallestAst;
                    lastDestroyedDist = distances.get(smallestAst);
                    visible.remove(smallestAst);
                    distances.remove(smallestAst);
                    destroyedCount++;
                    if (destroyedCount == 200) {
                        break;
                    }
                }
            }
        }
        return 100 * lastDestroyed.getX() + lastDestroyed.getY();
    }

    private static int getQuarter(Coord2D vector) {
        int x = vector.getX();
        int y = vector.getY();

        if (x >= 0 && y < 0) {
            return 1;
        } else if ( x > 0 && y >= 0) {
            return 2;
        } else if (x <= 0 && y > 0) {
            return 3;
        } else if (x < 0 && y <= 0){
            return 4;
        }
        return -1;
    }

    private static HashMap<Coord2D, Coord2D> getDistances(Coord2D source, HashSet<Coord2D> asteroids) {
        HashMap<Coord2D, Coord2D>  distances = new HashMap<>();
        for (Coord2D ast : asteroids) {
            distances.put(ast, source.dist(ast));
        }

        return distances;
    }

    private static int[] solve1(HashSet<Coord2D> asteroids) {
        int maxView = 0;
        Coord2D maxAsteroid = new Coord2D(0,0);
        for(Coord2D source : asteroids) {
            int x = source.getX();
            int y = source.getY();

            HashSet<Coord2D> distances = getReducedDistances(source, asteroids);

            if (distances.size() > maxView) {
                maxView = distances.size();
                maxAsteroid = source;
            }

        }
        return new int[]{maxAsteroid.getX(), maxAsteroid.getY(), maxView};

    }

    private static HashSet<Coord2D> getReducedDistances(Coord2D source, HashSet<Coord2D> asteroids) {
        HashSet<Coord2D> distances = new HashSet<Coord2D>();

        for (Coord2D view : asteroids) {
            if (!view.equals(source)) {
                Coord2D reducedDist = reduceFraction(source.dist(view));

                boolean found = false;
                for (Coord2D dist : distances) {
                    if (dist.equals(reducedDist)) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    distances.add(reducedDist);
                }
            }
        }
        return distances;
    }

    private static HashSet<Coord2D> getVisible(Coord2D source, HashSet<Coord2D> asteroids) {
        HashSet<Coord2D> visible = new HashSet<Coord2D>();

        for (Coord2D view : asteroids) {
            if (!view.equals(source)) {

                boolean found = false;
                for (Coord2D vis : visible) {
                    if (reduceFraction(source.dist(view)).equals(reduceFraction(source.dist(vis)))) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    visible.add(view);
                }
            }
        }
        return visible;
    }

    static Coord2D reduceFraction(Coord2D length)
    {
        int d;

        int x = length.getX();
        int y = length.getY();

        if (x != 0 && y != 0) {
            int xPre = 1, yPre =1 ;

            if (x < 0) {
                xPre = -1;
            }
            if (y < 0) {
                yPre = -1;
            }
            x = x / xPre;
            y = y / yPre;

            d = __gcd(x, y);

            x = x / d;
            y = y / d;

            x = x * xPre;
            y = y * yPre;
        } else {
            if (x != 0) {
                x =  x > 0 ? 1 : -1;
            } else if (y != 0) {
                y = y > 0 ? 1 : -1;
            } else {
                throw new RuntimeException("WRONG");
            }
        }


        return new Coord2D(x,y);
    }

    private static int __gcd(int a, int b)
    {
        if (b == 0)
            return a;
        return __gcd(b, a % b);

    }

}

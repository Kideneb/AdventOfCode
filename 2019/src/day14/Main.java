package day14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    private static String input = "src/day14/input.txt";

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File(input));;

        HashMap<String, Node> nodes = new HashMap<String, Node>();

        while(in.hasNextLine()) {
            String str = in.nextLine();

            String[] words = str.replaceAll("\\d| |", "").split(",|=>");
            int[] numbers = Arrays.stream(str.replaceAll("[a-zA-Z]+| ", "").split(",|=>")).mapToInt(Integer::parseInt).toArray();
            for (String s : words) {
                if (!nodes.containsKey(s)) {
                    Node n = new Node();
                    n.setName(s);
                    nodes.put(s, n);
                }
            }

            Node product = nodes.get(words[words.length - 1]);
            product.setAmount(numbers[numbers.length - 1]);

            for (int i = 0; i < words.length - 1; i++) {
                product.addParent(nodes.get(words[i]), numbers[i]);
            }
        }


        System.out.println(solve(nodes));
    }

    private static long solve(HashMap<String, Node> nodes) {
        Node product = nodes.get("FUEL");
        Node educt = nodes.get("ORE");
        HashMap<Node, Long> leftover = new HashMap<>();
        for (Node n : nodes.values()) {
            leftover.put(n, (long) 0);
        }
        long solution = getAmountNeeded(educt, product, 1, leftover);
        return solution;
    }

    private static long getAmountNeeded(Node educt, Node product, long multiplier, HashMap<Node, Long> leftover) {
        if (multiplier == 0) {
            return 0;
        }
        long realMult = multiplier / product.getAmount();
        if (multiplier % product.getAmount() != 0) {
            if ((multiplier % product.getAmount()) + leftover.get(product) >=  product.getAmount()) {
                leftover.replace(product, (multiplier % product.getAmount()) + leftover.get(product) -  product.getAmount());
            } else {
                realMult++;
                long left = product.getAmount() - (multiplier % product.getAmount());
                    leftover.replace(product, leftover.get(product) + left);
            }
        }

        long result = 0;
        for (Node n  : product.getParents().keySet()) {
            if (n != educt) {
                long existing = leftover.get(n);
                    leftover.replace(n, 0L);
                if ((product.getParents().get(n) * realMult) - existing >= 0) {
                    result += getAmountNeeded(educt, n, (product.getParents().get(n) * realMult) - existing, leftover);
                }
            } else {
                result += product.getParents().get(n) * realMult;
            }
        }
        return result;
    }

}

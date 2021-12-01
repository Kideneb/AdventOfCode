package day06;

import java.io.File;
import java.util.*;

public class Main {

    private static String input = "src/day06/input.txt";

    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(new File(input));

        Node<String> com = new Node<String>("COM");
        List<Node<String>> nodeList = new ArrayList<Node<String>>();
        nodeList.add(com);

        while(in.hasNext()) {
            String s = in.nextLine();

            String v1 = s.split("\\)")[0];
            String v2 = s.split("\\)")[1];

            Node<String> v1Elem = new Node<String>(v1);
            Node<String> v2Elem = new Node<String>(v2);
            boolean v1Exists = false;
            boolean v2Exists = false;


            for (Node<String> node : nodeList) {
                if (v1.equals(node.getData())){
                    v1Elem = node;
                    v1Exists = true;
                }
                if (v2.equals(node.getData())){
                    v2Elem = node;
                    v2Exists = true;
                }
            }

            if(!v1Exists) {
                nodeList.add(v1Elem);
            }
            if(!v2Exists) {
                nodeList.add(v2Elem);
            }

            v1Elem.addChild(v2Elem);
        }

        System.out.println(countConnections(com));
        System.out.println(shortestPath(com, "YOU", "SAN"));
    }

    private static int shortestPath(Node<String> com, String start, String end) {
        int pathLength = 0;

        Node<String> startNode = getNode(com, start);
        Node<String> endNode = getNode(com, end);

        List<Node<String>> startParents = new ArrayList<Node<String>>();
        List<Node<String>> endParents = new ArrayList<Node<String>>();
        Node<String> p = startNode.getParent();
        while (p != null) {
            startParents.add(p);
            p = p.getParent();
        }
        p = endNode.getParent();
        while (p != null) {
            endParents.add(p);
            p = p.getParent();
        }

        for (int i = 0; i < startParents.size(); i++) {
            Node<String> elem = startParents.get(i);
            if(endParents.contains(elem)) {
                pathLength = endParents.indexOf(elem) + i;
                break;
            }
        }

        return pathLength;
    }

    private static Node<String> getNode(Node<String> head, String data) {
        for (Node<String>  node: head.getChildren()) {
            if (node.getData().equals(data)) {
                return node;
            } else {
                Node<String> ret = getNode(node, data);
                if(ret.getData().equals(data)) {
                    return ret;
                }
            }
        }
        return head;
    }

    private static int countConnections(Node<String> head, int counter, int depth)  {
        depth++;
        for (Node<String>  node: head.getChildren()) {
            counter += depth;
            counter = countConnections(node, counter, depth);
        }
        return counter;
    }

    private static int countConnections(Node<String> head)  {
        int counter = 0;
        int depth = 0;
        return countConnections(head, counter, depth);
    }

}

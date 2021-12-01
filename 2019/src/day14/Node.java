package day14;

import java.util.HashMap;

public class Node {

    private HashMap<Node, Integer>  parents = new HashMap<>();

    private int amount;

    private String name;

    public void addParent(Node n, int i) {
        parents.put(n, i);
    }

    public HashMap<Node, Integer> getParents() {
        return parents;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setName(String s) {
        this.name = s;
    }

    public int getAmount() {
        return amount;
    }
}

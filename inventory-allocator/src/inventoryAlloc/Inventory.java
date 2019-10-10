package inventoryAlloc;

import java.util.HashMap;

public class Inventory {
    String name;
    HashMap<String, Integer> total;

    public Inventory(String name, HashMap<String, Integer> total) {
        this.name = name;
        this.total = total;
    }

    @Override
    public String toString() {
        return "name: " + name + " inventory: " + total;
    }
}

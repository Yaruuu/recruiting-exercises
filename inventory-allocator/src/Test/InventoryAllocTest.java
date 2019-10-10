package Test;

import inventoryAlloc.Inventory;
import inventoryAlloc.InventoryAllocator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InventoryAllocTest {
    HashMap<String, Integer> order;
    List<Inventory> warehouse;

    public static void main(String[] args){
        InventoryAllocTest iaTest = new InventoryAllocTest();

        //iaTest.test1();
        //iaTest.test2();
        //iaTest.test3();
        iaTest.test4();
    }

    public void testInventoryAllocator(){
        InventoryAllocator ia = new InventoryAllocator();
        System.out.println("order: " + order);
        System.out.println("warehouse: " + warehouse);

        HashMap<String, HashMap<String, Integer>> result = ia.getAllocate(order, warehouse);

        System.out.println("res: " + result);
    }

    // Exact inventory match
    public void test1(){
        order = new HashMap<String, Integer>(){{
            put("apple", 1);
        }};

        Inventory inventory1 = new Inventory("owd", new HashMap<String, Integer>(){{
            put("apple", 1);
        }});

        warehouse = new ArrayList<Inventory>(){{
            add(inventory1);
        }};

        testInventoryAllocator();
    }

    // Not enough inventory
    public void test2(){
        order = new HashMap<String, Integer>(){{
            put("apple", 1);
        }};
        Inventory inventory1 = new Inventory("owd", new HashMap<String, Integer>(){{
            put("apple", 0);
        }});
        warehouse = new ArrayList<Inventory>(){{
            add(inventory1);
        }};

        testInventoryAllocator();
    }

    // Should split an item across warehouses
    public void test3(){
        order = new HashMap<String, Integer>(){{
            put("apple", 10);
        }};
        Inventory inventory1 = new Inventory("owd", new HashMap<String, Integer>(){{
            put("apple", 5);
        }});
        Inventory inventory2 = new Inventory("dm", new HashMap<String, Integer>(){{
            put("apple", 5);
        }});
        warehouse = new ArrayList<Inventory>(){{
            add(inventory1);
            add(inventory2);
        }};

        testInventoryAllocator();
    }

    // general test
    public void test4(){
        order = new HashMap<String, Integer>(){{
            put("apple", 10);
            put("pear", 3);
            put("orange", 3);
            put("peach", 4);
            put("banana", 10);
        }};

        Inventory inventory1 = new Inventory("owd", new HashMap<String, Integer>(){{
            put("apple", 5);
            put("pear", 2);
            put("peach", 4);

        }});
        Inventory inventory2 = new Inventory("dm", new HashMap<String, Integer>(){{
            put("apple", 5);
            put("orange", 1);
            put("banana", 10);
        }});
        Inventory inventory3 = new Inventory("wer", new HashMap<String, Integer>(){{
            put("apple", 5);
            put("pear", 5);
            put("orange", 3);
        }});
        warehouse = new ArrayList<Inventory>(){{
            add(inventory1);
            add(inventory2);
            add(inventory3);

        }};

        testInventoryAllocator();
    }




}

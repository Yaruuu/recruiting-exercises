package inventoryAlloc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryAllocator {
    public HashMap<String, HashMap<String, Integer>> getAllocate(HashMap<String, Integer> order,
                                                      List<Inventory> warehouse) {
        HashMap<String, HashMap<String, Integer>> res = new HashMap<>();

        // To completely ship an item
        for(Inventory inventory: warehouse){ // iterate inventory list
            String invName = inventory.name;
            HashMap<String, Integer> fruits = inventory.total;
            HashMap<String, Integer> alloc = new HashMap<>(); // allocate status by this inventory

            for(Map.Entry<String, Integer> odr: order.entrySet()){
                String orderFruit = odr.getKey();
                Integer reqNum = odr.getValue();
                if(reqNum > 0 && fruits.containsKey(orderFruit) && fruits.get(orderFruit) >= reqNum){
                    alloc.put(orderFruit, reqNum);
                    odr.setValue(0);
                    fruits.put(orderFruit, fruits.get(orderFruit) - reqNum);
                }
            }
            if(!alloc.isEmpty()) {
                res.put(invName, alloc);
            }
            if(checkOrderDone(order)){
                break;
            }
        }

        // Cannot be shipped from one warehouse, split it
        for(Inventory inventory: warehouse){ // iterate inventory list
            String invName = inventory.name;
            HashMap<String, Integer> fruits = inventory.total;
            HashMap<String, Integer> alloc = new HashMap<>(); // allocate status by this inventory

            for(Map.Entry<String, Integer> odr: order.entrySet()){ // iterate order
                String orderFruit = odr.getKey();
                Integer reqNum = odr.getValue();
                if(reqNum > 0 && fruits.containsKey(orderFruit)){
                    int numAlloc = Math.min(reqNum, fruits.get(orderFruit));
                    odr.setValue(reqNum - numAlloc);
                    fruits.put(orderFruit, fruits.get(orderFruit) - numAlloc);
                    alloc.put(orderFruit, alloc.getOrDefault(orderFruit, 0) + numAlloc);
                }
            }

            if(!alloc.isEmpty()) {
                if(res.containsKey(invName)) {
                    for(Map.Entry<String, Integer> item: alloc.entrySet()){
                        res.get(invName).put(item.getKey(), item.getValue());
                    }
                } else {
                    res.put(invName, alloc);
                }
            }
            if(checkOrderDone(order)){
                break;
            }
        }

        if(!checkOrderDone(order)){
            return new HashMap<>();
        }
        return res;
    }

    // check if order is finished allocated
    public boolean checkOrderDone(HashMap<String, Integer> order){
        //System.out.println("order: " + order);
        for(Map.Entry<String, Integer> item: order.entrySet()){
            if(item.getValue() != 0) {
                return false;
            }
        }
        return true;
    }
}

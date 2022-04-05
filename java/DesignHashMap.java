import java.util.Iterator;
import java.util.LinkedList;
class MyHashMap {
    /*
        use linkedlist  key.   
        hash function 
    */
    private class Pair{
        private int key;
        private int val;
        
        public Pair(int key, int value) {
            this.key = key;
            this.val = value;
        }
        public int getVal() {
            return val;
        }
        public int getKey() {
            return key;
        }
        public void set(int value) {
            this.val = value;
        }
    }
    
    private static int BASE = 769;
    private LinkedList[] myHash;
    
    private int hash (int key) {
        return key % BASE;
    }

    public MyHashMap() {
        // initial
        myHash = new LinkedList[BASE];
        for(int i = 0; i < BASE; i++) {
            myHash[i] = new LinkedList<Pair>();
        }
    }
    
    public void put(int key, int value) {
        int position = hash(key);
        Iterator<Pair> list = myHash[position].iterator();
        while (list.hasNext()) {
            Pair element = list.next();
            if (element.getKey() == key) {
                element.set(value);
                return;
            }
        }
        myHash[position].add(new Pair(key, value));
    }
    
    public int get(int key) {
        int position = hash(key);
        Iterator<Pair> list = myHash[position].iterator();
        while (list.hasNext()) {
            Pair element = list.next();
            if (key == element.getKey()) {
                return element.getVal();
            }
        }
        
        return -1;
    }
    
    public void remove(int key) {
        int position = hash(key);
        Iterator<Pair> list = myHash[position].iterator();
        while (list.hasNext()) {
            Pair element = list.next();
            if (key == element.getKey()) {
                myHash[position].remove(element);
                return;
            }
        }
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
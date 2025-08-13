
import java.util.*;

public class MultiLevelCacheSystem {

    static class CacheEntry {

        int key;
        String value;
        int freq;
        long time;

        public CacheEntry(int key, String value) {
            this.key = key;
            this.value = value;
            this.freq = 1;
            this.time = System.nanoTime();
        }

        void accessed() {
            freq++;
            time = System.nanoTime();
        }
    }

    static class CacheLevel {

        int capacity;
        int accessCost;
        Map<Integer, CacheEntry> map;
        PriorityQueue<CacheEntry> heap;

        public CacheLevel(int capacity, int accessCost) {
            this.capacity = capacity;
            this.accessCost = accessCost;
            this.map = new HashMap<>();
            this.heap = new PriorityQueue<>((a, b) -> {
                if (a.freq != b.freq) {
                    return Integer.compare(a.freq, b.freq); // low freq first
                } else {
                    return Long.compare(a.time, b.time); // older first

                }
            });
        }

        public boolean contains(int key) {
            return map.containsKey(key);
        }

        public CacheEntry get(int key) {
            if (!map.containsKey(key)) {
                return null;
            }
            CacheEntry entry = map.get(key);
            heap.remove(entry);
            entry.accessed();
            heap.offer(entry);
            return entry;
        }

        public void put(CacheEntry entry) {
            if (map.size() >= capacity) {
                evict();
            }
            map.put(entry.key, entry);
            heap.offer(entry);
        }

        public void evict() {
            if (!heap.isEmpty()) {
                CacheEntry evicted = heap.poll();
                map.remove(evicted.key);
            }
        }

        public void remove(int key) {
            if (map.containsKey(key)) {
                CacheEntry entry = map.get(key);
                heap.remove(entry);
                map.remove(key);
            }
        }

        public List<CacheEntry> getAllEntries() {
            return new ArrayList<>(map.values());
        }

        public String toString() {
            List<Integer> keys = new ArrayList<>();
            for (CacheEntry e : map.values()) {
                keys.add(e.key);
            }
            return keys.toString();
        }
    }

    CacheLevel L1 = new CacheLevel(2, 1);
    CacheLevel L2 = new CacheLevel(5, 3);
    CacheLevel L3 = new CacheLevel(10, 10);

    public void put(int key, String value) {
        CacheEntry entry = new CacheEntry(key, value);
        // Check if already exists
        if (L1.contains(key)) {
            L1.remove(key);
        } else if (L2.contains(key)) {
            L2.remove(key);
        } else if (L3.contains(key)) {
            L3.remove(key);
        }

        // Default insert to L3
        L3.put(entry);
        rebalance();
    }

    public String get(int key) {
        CacheEntry entry = null;
        if (L1.contains(key)) {
            entry = L1.get(key);
        } else if (L2.contains(key)) {
            entry = L2.get(key);
        } else if (L3.contains(key)) {
            entry = L3.get(key);
        }

        if (entry == null) {
            return null;
        }

        // remove from current level
        L1.remove(key);
        L2.remove(key);
        L3.remove(key);

        entry.accessed();

        // Promote based on frequency
        if (entry.freq >= 3) {
            L1.put(entry);
        } else if (entry.freq >= 2) {
            L2.put(entry);
        } else {
            L3.put(entry);
        }

        rebalance();
        return entry.value;
    }

    private void rebalance() {
        // move cold entries down
        for (CacheEntry entry : new ArrayList<>(L1.getAllEntries())) {
            if (entry.freq < 3) {
                L1.remove(entry.key);
                if (entry.freq == 2) {
                    L2.put(entry);
                } else {
                    L3.put(entry);
                }
            }
        }

        for (CacheEntry entry : new ArrayList<>(L2.getAllEntries())) {
            if (entry.freq < 2) {
                L2.remove(entry.key);
                L3.put(entry);
            } else if (entry.freq >= 3) {
                L2.remove(entry.key);
                L1.put(entry);
            }
        }

        for (CacheEntry entry : new ArrayList<>(L3.getAllEntries())) {
            if (entry.freq >= 3) {
                L3.remove(entry.key);
                L1.put(entry);
            } else if (entry.freq == 2) {
                L3.remove(entry.key);
                L2.put(entry);
            }
        }
    }

    public void printState() {
        System.out.println("L1: " + L1);
        System.out.println("L2: " + L2);
        System.out.println("L3: " + L3);
    }

    // for demo purposes
    public static void main(String[] args) {
        MultiLevelCacheSystem cache = new MultiLevelCacheSystem();
        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");

        cache.printState(); // L1: [2,3], L2: [1]

        cache.get(1);
        cache.get(1);
        cache.get(2);
        cache.printState(); // L1: [1,2], L2: [3]

        cache.put(4, "D");
        cache.put(5, "E");
        cache.put(6, "F");

        cache.printState();
    }
}


import java.util.*;

public class MovingAverageStream {

    private int windowSize;
    private Queue<Integer> window;
    private double sum;

    // 中位數：小的一半用 Max Heap，大的一半用 Min Heap
    private PriorityQueue<Integer> maxHeap; // 左半部（大頂堆）
    private PriorityQueue<Integer> minHeap; // 右半部（小頂堆）

    // 最小值與最大值 → 使用 TreeMap 追蹤頻率
    private TreeMap<Integer, Integer> freqMap;

    public MovingAverageStream(int size) {
        this.windowSize = size;
        this.window = new LinkedList<>();
        this.sum = 0.0;

        this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        this.minHeap = new PriorityQueue<>();

        this.freqMap = new TreeMap<>();
    }

    public double next(int val) {
        window.offer(val);
        sum += val;

        // 平衡中位數 heap
        if (maxHeap.isEmpty() || val <= maxHeap.peek()) {
            maxHeap.offer(val);
        } else {
            minHeap.offer(val);
        }

        // 頻率表更新
        freqMap.put(val, freqMap.getOrDefault(val, 0) + 1);

        if (window.size() > windowSize) {
            int removed = window.poll();
            sum -= removed;
            removeFromHeaps(removed);
            removeFromFreqMap(removed);
        }

        rebalanceHeaps();
        return sum / window.size();
    }

    private void removeFromHeaps(int val) {
        // 延遲刪除：將元素從兩個 heap 中移除（較慢）
        if (!maxHeap.remove(val)) {
            minHeap.remove(val);
        }
    }

    private void removeFromFreqMap(int val) {
        freqMap.put(val, freqMap.get(val) - 1);
        if (freqMap.get(val) == 0) {
            freqMap.remove(val);
        }
    }

    private void rebalanceHeaps() {
        while (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        }

        while (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double getMedian() {
        if (maxHeap.isEmpty()) {
            return 0;
        }

        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            return maxHeap.peek();
        }
    }

    public int getMin() {
        return freqMap.firstKey();
    }

    public int getMax() {
        return freqMap.lastKey();
    }

    // 示範主程式
    public static void main(String[] args) {
        MovingAverageStream ma = new MovingAverageStream(3);
        System.out.println(ma.next(1));  // 1.0
        System.out.println(ma.next(10)); // 5.5
        System.out.println(ma.next(3));  // 4.67
        System.out.println(ma.next(5));  // 6.0

        System.out.println("Median: " + ma.getMedian()); // 5.0
        System.out.println("Min: " + ma.getMin());       // 3
        System.out.println("Max: " + ma.getMax());       // 10
    }
}

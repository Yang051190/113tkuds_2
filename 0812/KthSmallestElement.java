
import java.util.*;

public class KthSmallestElement {

    // 方法一：使用大小為 K 的 Max Heap
    public static int kthSmallestMaxHeap(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : nums) {
            maxHeap.offer(num);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        return maxHeap.peek();
    }

    // 方法二：使用 Min Heap 並提取 K 次
    public static int kthSmallestMinHeap(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.offer(num);
        }
        for (int i = 1; i < k; i++) {
            minHeap.poll(); // 取出前 k-1 個
        }
        return minHeap.peek();
    }

    public static void main(String[] args) {
        int[] arr1 = {7, 10, 4, 3, 20, 15};
        int[] arr2 = {1};
        int[] arr3 = {3, 1, 4, 1, 5, 9, 2, 6};

        System.out.println("方法1 (MaxHeap):");
        System.out.println("第3小元素為: " + kthSmallestMaxHeap(arr1, 3)); // 7
        System.out.println("第1小元素為: " + kthSmallestMaxHeap(arr2, 1)); // 1
        System.out.println("第4小元素為: " + kthSmallestMaxHeap(arr3, 4)); // 3

        System.out.println("\n方法2 (MinHeap):");
        System.out.println("第3小元素為: " + kthSmallestMinHeap(arr1, 3)); // 7
        System.out.println("第1小元素為: " + kthSmallestMinHeap(arr2, 1)); // 1
        System.out.println("第4小元素為: " + kthSmallestMinHeap(arr3, 4)); // 3
    }
}

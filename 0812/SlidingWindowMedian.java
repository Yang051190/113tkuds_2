
import java.util.*;

public class SlidingWindowMedian {

    // 小的那半使用 maxHeap（反向排序）
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    // 大的那半使用 minHeap（預設升序）
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    // 平衡兩個 heap 的大小，讓 maxHeap.size() == minHeap.size() 或多一個
    private void balanceHeaps() {
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    // 插入新值到 heap 中
    private void add(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }
        balanceHeaps();
    }

    // 從 heap 中移除一個值
    private void remove(int num) {
        if (num <= maxHeap.peek()) {
            maxHeap.remove(num);
        } else {
            minHeap.remove(num);
        }
        balanceHeaps();
    }

    // 取得目前中位數
    private double getMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return ((double) maxHeap.peek() + minHeap.peek()) / 2;
        } else {
            return maxHeap.peek();
        }
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        List<Double> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            add(nums[i]);

            if (i >= k - 1) { // 當視窗達到大小
                result.add(getMedian());

                // 移除視窗最左側元素
                remove(nums[i - k + 1]);
            }
        }

        // 將 List<Double> 轉成 double[]
        double[] ans = new double[result.size()];
        for (int i = 0; i < result.size(); i++) {
            ans[i] = result.get(i);
        }

        return ans;
    }

    // 測試程式
    public static void main(String[] args) {
        SlidingWindowMedian swm = new SlidingWindowMedian();

        int[] nums1 = {1, 3, -1, -3, 5, 3, 6, 7};
        System.out.println(Arrays.toString(swm.medianSlidingWindow(nums1, 3)));
        // 預期：[1.0, -1.0, -1.0, 3.0, 5.0, 6.0]

        int[] nums2 = {1, 2, 3, 4};
        System.out.println(Arrays.toString(swm.medianSlidingWindow(nums2, 2)));
        // 預期：[1.5, 2.5, 3.5]
    }
}


import java.util.*;

public class MergeKSortedArrays {

    // 用來包裝每個元素的類別
    static class Element implements Comparable<Element> {

        int value;      // 該元素的值
        int arrayIndex; // 它來自哪個陣列
        int elementIndex; // 在該陣列中的位置

        public Element(int value, int arrayIndex, int elementIndex) {
            this.value = value;
            this.arrayIndex = arrayIndex;
            this.elementIndex = elementIndex;
        }

        @Override
        public int compareTo(Element other) {
            return this.value - other.value; // 升冪排序（小的先出）
        }
    }

    public static List<Integer> mergeKSortedArrays(int[][] arrays) {
        PriorityQueue<Element> minHeap = new PriorityQueue<>();
        List<Integer> result = new ArrayList<>();

        // 初始化：把每個陣列的第一個元素加入 heap
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                minHeap.offer(new Element(arrays[i][0], i, 0));
            }
        }

        // 不斷從 heap 取出最小值，並加入下一個元素
        while (!minHeap.isEmpty()) {
            Element current = minHeap.poll();
            result.add(current.value);

            int nextIndex = current.elementIndex + 1;
            if (nextIndex < arrays[current.arrayIndex].length) {
                int nextValue = arrays[current.arrayIndex][nextIndex];
                minHeap.offer(new Element(nextValue, current.arrayIndex, nextIndex));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] input1 = {{1, 4, 5}, {1, 3, 4}, {2, 6}};
        int[][] input2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] input3 = {{1}, {0}};

        System.out.println(mergeKSortedArrays(input1)); // [1,1,2,3,4,4,5,6]
        System.out.println(mergeKSortedArrays(input2)); // [1,2,3,4,5,6,7,8,9]
        System.out.println(mergeKSortedArrays(input3)); // [0,1]
    }
}

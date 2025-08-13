
public class ValidMaxHeapChecker {

    // 方法：檢查是否為有效 Max Heap，回傳結果
    public static boolean isValidMaxHeap(int[] arr) {
        int n = arr.length;

        // 最後一個非葉節點為 (n-2)/2
        for (int i = 0; i <= (n - 2) / 2; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < n && arr[i] < arr[left]) {
                System.out.println("違反 Max Heap 的節點索引：" + left + "，值：" + arr[left] + " 大於父節點 " + arr[i]);
                return false;
            }

            if (right < n && arr[i] < arr[right]) {
                System.out.println("違反 Max Heap 的節點索引：" + right + "，值：" + arr[right] + " 大於父節點 " + arr[i]);
                return false;
            }
        }

        return true;
    }

    // 測試主程式
    public static void main(String[] args) {
        int[][] testCases = {
            {100, 90, 80, 70, 60, 75, 65},
            {100, 90, 80, 95, 60, 75, 65},
            {50},
            {}
        };

        for (int i = 0; i < testCases.length; i++) {
            int[] test = testCases[i];
            System.out.print("測試案例 " + (i + 1) + ": ");
            printArray(test);
            boolean result = isValidMaxHeap(test);
            System.out.println("是否為有效 Max Heap？" + result);
            System.out.println("-------------------------------------------------");
        }
    }

    // 陣列列印工具
    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}

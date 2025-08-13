
import java.util.*;

public class MeetingRoomScheduler {

    // 第1部分：最少需要多少會議室
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        // 根據開始時間排序
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        // 最小堆存放結束時間（表示目前正在使用的會議室）
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int[] meeting : intervals) {
            // 如果最早結束的會議已經結束，釋放該會議室
            if (!minHeap.isEmpty() && meeting[0] >= minHeap.peek()) {
                minHeap.poll();
            }
            // 加入當前會議的結束時間（占用一個會議室）
            minHeap.offer(meeting[1]);
        }

        return minHeap.size(); // heap size = 同時最多進行的會議數 = 最少會議室數
    }

    // 第2部分：如果只有N間會議室，安排最多總時間的會議
    public int maxTotalMeetingTimeWithNRooms(int[][] intervals, int roomCount) {
        // 根據會議結束時間排序（區間排程的經典貪心策略）
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));

        // 每間會議室最後使用時間（結束時間），初始化為0
        int[] roomAvailableAt = new int[roomCount];
        Arrays.fill(roomAvailableAt, 0);

        int totalTime = 0;

        for (int[] meeting : intervals) {
            for (int i = 0; i < roomCount; i++) {
                if (roomAvailableAt[i] <= meeting[0]) {
                    // 分配此會議室
                    roomAvailableAt[i] = meeting[1];
                    totalTime += (meeting[1] - meeting[0]);
                    break;
                }
            }
        }

        return totalTime;
    }

    public static void main(String[] args) {
        MeetingRoomScheduler mrs = new MeetingRoomScheduler();

        System.out.println("需要會議室數：");
        System.out.println(mrs.minMeetingRooms(new int[][]{{0, 30}, {5, 10}, {15, 20}})); // 2
        System.out.println(mrs.minMeetingRooms(new int[][]{{9, 10}, {4, 9}, {4, 17}})); // 2
        System.out.println(mrs.minMeetingRooms(new int[][]{{1, 5}, {8, 9}, {8, 9}})); // 2

        System.out.println("1間會議室最大總時間：");
        System.out.println(mrs.maxTotalMeetingTimeWithNRooms(new int[][]{{1, 4}, {2, 3}, {4, 6}}, 1)); // 5
    }
}

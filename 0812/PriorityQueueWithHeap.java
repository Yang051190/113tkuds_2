
import java.util.*;

public class PriorityQueueWithHeap {

    static class Task {

        String name;
        int priority;
        long timestamp; // 為了處理相同優先級的任務順序

        Task(String name, int priority, long timestamp) {
            this.name = name;
            this.priority = priority;
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return name + " (優先級: " + priority + ")";
        }
    }

    static class TaskComparator implements Comparator<Task> {

        public int compare(Task a, Task b) {
            if (a.priority != b.priority) {
                return b.priority - a.priority; // 大的優先
            } else {
                return Long.compare(a.timestamp, b.timestamp); // 先進先出
            }
        }
    }

    private PriorityQueue<Task> heap;
    private Map<String, Task> taskMap;
    private long timestampCounter = 0;

    public PriorityQueueWithHeap() {
        heap = new PriorityQueue<>(new TaskComparator());
        taskMap = new HashMap<>();
    }

    public void addTask(String name, int priority) {
        Task task = new Task(name, priority, timestampCounter++);
        heap.offer(task);
        taskMap.put(name, task);
    }

    public Task executeNext() {
        Task task = heap.poll();
        if (task != null) {
            taskMap.remove(task.name);
        }
        return task;
    }

    public Task peek() {
        return heap.peek();
    }

    public void changePriority(String name, int newPriority) {
        Task task = taskMap.get(name);
        if (task != null) {
            heap.remove(task); // PriorityQueue 不支援直接更新，需要先移除後再加入
            Task newTask = new Task(name, newPriority, timestampCounter++);
            heap.offer(newTask);
            taskMap.put(name, newTask);
        }
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }

    // 測試程式
    public static void main(String[] args) {
        PriorityQueueWithHeap pq = new PriorityQueueWithHeap();

        pq.addTask("備份", 1);
        pq.addTask("緊急修復", 5);
        pq.addTask("更新", 3);

        System.out.println("目前最優先任務：" + pq.peek());

        System.out.println("執行任務：" + pq.executeNext()); // 緊急修復
        System.out.println("執行任務：" + pq.executeNext()); // 更新
        System.out.println("執行任務：" + pq.executeNext()); // 備份

        System.out.println("佇列是否為空？" + pq.isEmpty());

        // 重新加入測試 changePriority
        pq.addTask("掃描", 2);
        pq.addTask("防毒更新", 4);
        pq.changePriority("掃描", 6);

        System.out.println("目前最優先任務：" + pq.peek()); // 掃描（提升後）

        System.out.println("執行任務：" + pq.executeNext());
        System.out.println("執行任務：" + pq.executeNext());
    }
}

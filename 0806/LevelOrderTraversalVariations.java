
import java.util.*;

/**
 * 練習 3.5 ─ 層序走訪變形 -------------------------------------------------------------
 * 1) levelOrderLists 每層一 List 2) zigzagLevelOrder 之字形（Zig-zag） 3)
 * lastNodeEachLevel 每層最後一節點 4) verticalOrder 垂直層序（column 分組、由左到右）
 */
public class LevelOrderTraversalVariations {

    /* ---------- 節點定義 ---------- */
    static class Node {

        int val;
        Node left, right;

        Node(int v) {
            val = v;
        }
    }

    /* ==========================================================
       1) 普通層序 → List<List<Integer>>
       ========================================================== */
    public static List<List<Integer>> levelOrderLists(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> lvl = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node cur = q.poll();
                lvl.add(cur.val);
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
            res.add(lvl);
        }
        return res;
    }

    /* ==========================================================
       2) 之字形層序
       ========================================================== */
    public static List<List<Integer>> zigzagLevelOrder(Node root) {
        List<List<Integer>> base = levelOrderLists(root);
        for (int i = 1; i < base.size(); i += 2) {
            Collections.reverse(base.get(i));      // 偶數層 (0-based) 反轉

        }
        return base;
    }

    /* ==========================================================
       3) 每層最後一節點
       ========================================================== */
    public static List<Integer> lastNodeEachLevel(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Node cur = q.poll();
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
                if (i == size - 1) {
                    res.add(cur.val); // 最後一個

                }
            }
        }
        return res;
    }

    /* ==========================================================
       4) 垂直層序（BFS + column 編號）
       ========================================================== */
    public static List<List<Integer>> verticalOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Map<Integer, List<Integer>> map = new TreeMap<>(); // column → list
        Queue<Node> qNode = new LinkedList<>();
        Queue<Integer> qCol = new LinkedList<>();
        qNode.offer(root);
        qCol.offer(0);

        while (!qNode.isEmpty()) {
            Node cur = qNode.poll();
            int col = qCol.poll();
            map.computeIfAbsent(col, k -> new ArrayList<>()).add(cur.val);

            if (cur.left != null) {
                qNode.offer(cur.left);
                qCol.offer(col - 1);
            }
            if (cur.right != null) {
                qNode.offer(cur.right);
                qCol.offer(col + 1);
            }
        }
        res.addAll(map.values());     // TreeMap 已按 column 排好
        return res;
    }

    /* --------------------------- Demo --------------------------- */
    public static void main(String[] args) {
        /* 建以下二元樹
                  1
                /   \
               2     3
              / \   / \
             4   5 6   7
                    \
                     8
         */
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.right.left.right = new Node(8);

        System.out.println("1) 每層 List ：" + levelOrderLists(root));
        System.out.println("2) 之字形    ：" + zigzagLevelOrder(root));
        System.out.println("3) 每層最後節點：" + lastNodeEachLevel(root));
        System.out.println("4) 垂直層序  ：" + verticalOrder(root));
    }
}

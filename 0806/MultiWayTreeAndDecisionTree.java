
import java.util.*;

/**
 * 練習 3.10 ─ 多路樹與決策樹 ----------------------------------------------------------
 * A) MultiNode ─ 支援任意多子節點 • addChild() • dfs(), bfs() • height(), degree map
 *
 * B) 簡易「猜數字」決策樹：問 <=mid? 左 or 右 • buildGuessTree(1,100) • traverseDecision(root
 * , target)
 */
public class MultiWayTreeAndDecisionTree {

    /* =======================================================
       A) 多路樹節點定義與基本操作
       ======================================================= */
    static class MultiNode {

        String label;                 // 節點資料（可自行改成泛型）
        List<MultiNode> children = new ArrayList<>();

        MultiNode(String label) {
            this.label = label;
        }

        MultiNode addChild(MultiNode child) {  // 方便鏈式新增
            children.add(child);
            return this;
        }
    }

    /* ------- DFS (前序) ------- */
    public static void dfs(MultiNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.label + " ");
        for (MultiNode ch : root.children) {
            dfs(ch);
        }
    }

    /* ------- BFS (層序) ------- */
    public static void bfs(MultiNode root) {
        if (root == null) {
            return;
        }
        Queue<MultiNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            MultiNode cur = q.poll();
            System.out.print(cur.label + " ");
            for (MultiNode ch : cur.children) {
                q.offer(ch);
            }
        }
    }

    /* ------- 樹高（根高=1） ------- */
    public static int height(MultiNode n) {
        if (n == null) {
            return 0;
        }
        int h = 1;
        for (MultiNode ch : n.children) {
            h = Math.max(h, 1 + height(ch));
        }
        return h;
    }

    /* ------- 每節點度數 (children 數) ------- */
    public static Map<String, Integer> degreeMap(MultiNode root) {
        Map<String, Integer> map = new LinkedHashMap<>();
        degDfs(root, map);
        return map;
    }

    private static void degDfs(MultiNode n, Map<String, Integer> m) {
        if (n == null) {
            return;
        }
        m.put(n.label, n.children.size());
        for (MultiNode ch : n.children) {
            degDfs(ch, m);
        }
    }

    /* =======================================================
       B) 猜數字決策樹：二元分割  (不是 BST，用 MultiNode 也 OK)
       ======================================================= */
    public static MultiNode buildGuessTree(int lo, int hi) {
        if (lo == hi) {
            return new MultiNode("答案=" + lo);
        }
        int mid = (lo + hi) >>> 1;
        MultiNode node = new MultiNode("數字 ≤ " + mid + " ?");
        node
                .addChild(buildGuessTree(lo, mid)) // yes
                .addChild(buildGuessTree(mid + 1, hi)); // no
        return node;
    }

    /* 根據 target 一路走決策樹，印出路徑 */
    public static void traverseDecision(MultiNode root, int target) {
        MultiNode cur = root;
        while (cur.children.size() > 0) {
            System.out.println(cur.label + "   target = " + target);
            int mid = Integer.parseInt(cur.label.split("≤ ")[1].split(" ")[0]);
            cur = (target <= mid) ? cur.children.get(0) : cur.children.get(1);
        }
        System.out.println(">> " + cur.label);
    }

    /* ======================= Demo ======================= */
    public static void main(String[] args) {

        /* ------------ 建立一棵示範多路樹 -------------- 
                      A
                /  |   \
               B   C    D
             / |   |\
            E  F   G H
         */
        MultiNode root = new MultiNode("A");
        MultiNode B = new MultiNode("B");
        MultiNode C = new MultiNode("C");
        MultiNode D = new MultiNode("D");
        root.addChild(B).addChild(C).addChild(D);
        B.addChild(new MultiNode("E")).addChild(new MultiNode("F"));
        C.addChild(new MultiNode("G")).addChild(new MultiNode("H"));

        System.out.print("DFS: ");
        dfs(root);                // A B E F C G H D
        System.out.print("\nBFS: ");
        bfs(root);                // A B C D E F G H

        System.out.println("\n樹高 = " + height(root)); // 3
        System.out.println("節點度數 = " + degreeMap(root));

        /* ------------ 決策樹：猜 1~100 -------------- */
        System.out.println("\n--- 決策樹 (猜 1~100) —— 目標 76 ---");
        MultiNode guessRoot = buildGuessTree(1, 100);
        traverseDecision(guessRoot, 76);
    }
}

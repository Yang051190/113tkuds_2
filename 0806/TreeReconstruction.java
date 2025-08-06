
import java.util.*;

/**
 * 練習 3.8 ─ 樹的重建 ------------------------------------------------------------ 1)
 * buildPreIn 由「前序 + 中序」重建 2) buildPostIn 由「後序 + 中序」重建 3) buildLevel
 * 由「層序」重建完全二元樹 4) verifyTree 重新走訪，比對輸入序列是否一致
 */
public class TreeReconstruction {

    /* ======== 節點定義 ======== */
    static class Node {

        int val;
        Node left, right;

        Node(int v) {
            val = v;
        }
    }

    /* =========================================================
       1) 前序 + 中序 重建
       ========================================================= */
    public static Node buildPreIn(int[] pre, int[] in) {
        Map<Integer, Integer> idx = indexMap(in);
        return buildPre(pre, 0, pre.length - 1, 0, idx);
    }

    private static Node buildPre(int[] pre, int ps, int pe, int is, Map<Integer, Integer> idx) {
        if (ps > pe) {
            return null;
        }
        int rootVal = pre[ps];
        int i = idx.get(rootVal);
        int leftLen = i - is;
        Node root = new Node(rootVal);
        root.left = buildPre(pre, ps + 1, ps + leftLen, is, idx);
        root.right = buildPre(pre, ps + leftLen + 1, pe, i + 1, idx);
        return root;
    }

    /* =========================================================
       2) 後序 + 中序 重建
       ========================================================= */
    public static Node buildPostIn(int[] post, int[] in) {
        Map<Integer, Integer> idx = indexMap(in);
        return buildPost(post, 0, post.length - 1, 0, idx);
    }

    private static Node buildPost(int[] post, int ps, int pe, int is, Map<Integer, Integer> idx) {
        if (ps > pe) {
            return null;
        }
        int rootVal = post[pe];
        int i = idx.get(rootVal);
        int leftLen = i - is;
        Node root = new Node(rootVal);
        root.left = buildPost(post, ps, ps + leftLen - 1, is, idx);
        root.right = buildPost(post, ps + leftLen, pe - 1, i + 1, idx);
        return root;
    }

    /* =========================================================
       3) 層序 → 完全二元樹
       （假設輸入長度 = 節點數，按索引建左右子）
       ========================================================= */
    public static Node buildLevel(int[] level) {
        if (level.length == 0) {
            return null;
        }
        Node[] nodes = new Node[level.length];
        for (int i = 0; i < level.length; i++) {
            nodes[i] = new Node(level[i]);
        }
        for (int i = 0; i < level.length; i++) {
            int l = 2 * i + 1, r = 2 * i + 2;
            if (l < level.length) {
                nodes[i].left = nodes[l];
            }
            if (r < level.length) {
                nodes[i].right = nodes[r];
            }
        }
        return nodes[0];
    }

    /* =========================================================
       4) 驗證：給 root 與原序列，比對是否一致
       ========================================================= */
    public static boolean verifyTree(Node root,
            int[] pre, int[] in, int[] post, int[] level) {
        return Arrays.equals(pre, preorder(root).stream().mapToInt(i -> i).toArray())
                && Arrays.equals(in, inorder(root).stream().mapToInt(i -> i).toArray())
                && Arrays.equals(post, postorder(root).stream().mapToInt(i -> i).toArray())
                && Arrays.equals(level, levelOrder(root).stream().mapToInt(i -> i).toArray());
    }

    /* --------- 走訪工具 (產生 List<Integer>) --------- */
    private static List<Integer> preorder(Node n) {
        List<Integer> r = new ArrayList<>();
        pre(n, r);
        return r;
    }

    private static void pre(Node n, List<Integer> r) {
        if (n == null) {
            return;
        
        }r.add(n.val);
        pre(n.left, r);
        pre(n.right, r);
    }

    private static List<Integer> inorder(Node n) {
        List<Integer> r = new ArrayList<>();
        in(n, r);
        return r;
    }

    private static void in(Node n, List<Integer> r) {
        if (n == null) {
            return;
        
        }in(n.left, r);
        r.add(n.val);
        in(n.right, r);
    }

    private static List<Integer> postorder(Node n) {
        List<Integer> r = new ArrayList<>();
        post(n, r);
        return r;
    }

    private static void post(Node n, List<Integer> r) {
        if (n == null) {
            return;
        
        }post(n.left, r);
        post(n.right, r);
        r.add(n.val);
    }

    private static List<Integer> levelOrder(Node n) {
        List<Integer> r = new ArrayList<>();
        if (n == null) {
            return r;
        }
        Queue<Node> q = new LinkedList<>();
        q.offer(n);
        while (!q.isEmpty()) {
            Node cur = q.poll();
            r.add(cur.val);
            if (cur.left != null) {
                q.offer(cur.left);
            
            }if (cur.right != null) {
                q.offer(cur.right);
        
            }}
        return r;
    }

    /* ---------- 內部：中序索引表 ---------- */
    private static Map<Integer, Integer> indexMap(int[] in) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            m.put(in[i], i);
        }
        return m;
    }

    /* ==================== Demo ==================== */
    public static void main(String[] args) {
        /* 建一棵示例完全二元樹
                 1
               /   \
              2     3
             / \   / \
            4  5  6   7
         */
        int[] level = {1, 2, 3, 4, 5, 6, 7};
        Node root = buildLevel(level);

        /* 取得對應 pre / in / post 以供測試 */
        int[] pre = preorder(root).stream().mapToInt(i -> i).toArray();
        int[] in = inorder(root).stream().mapToInt(i -> i).toArray();
        int[] post = postorder(root).stream().mapToInt(i -> i).toArray();

        /* 用 pre+in 重建 → 驗證 */
        Node r1 = buildPreIn(pre, in);
        System.out.println("pre+in 重建驗證: "
                + verifyTree(r1, pre, in, post, level));

        /* 用 post+in 重建 → 驗證 */
        Node r2 = buildPostIn(post, in);
        System.out.println("post+in 重建驗證: "
                + verifyTree(r2, pre, in, post, level));

        /* 用 level 重建 (完全二元樹) → 驗證 */
        Node r3 = buildLevel(level);
        System.out.println("level 重建驗證: "
                + verifyTree(r3, pre, in, post, level));
    }
}

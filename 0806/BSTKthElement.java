
import java.util.*;

/**
 * 練習 3.4 ─ BST 第 k 小 / 大 元素查詢
 * ---------------------------------------------------------- 1) kthSmallest(k)
 * 取第 k 小 (k從1開始) 2) kthLargest(k) 取第 k 大 3) rangeKtoJ(k,j) 取第 k~j 小 (遞增序列) 4)
 * insert / delete 維持子樹節點數，支援動態 O(log n) 查 k 小
 */
public class BSTKthElement {

    /* ============ 節點定義，有 size 欄位 = 子樹節點總數 ============ */
    static class Node {

        int val, size = 1;
        Node left, right;

        Node(int v) {
            val = v;
        }
    }

    private Node root;

    /* ------------------ 公開 API ------------------ */
    public void insert(int v) {
        root = insert(root, v);
    }

    public void delete(int v) {
        root = delete(root, v);
    }

    public int kthSmallest(int k) {
        return kth(root, k);
    }

    public int kthLargest(int k) {
        return kth(root, size(root) - k + 1);
    }

    public List<Integer> rangeKtoJ(int k, int j) {
        List<Integer> res = new ArrayList<>();
        range(root, k, j, res);
        return res;
    }

    public int size() {
        return size(root);
    }

    /* ------------------ 內部輔助 (遞迴) ------------------ */
 /* 插入：標準 BST 插入 + 回溯更新 size */
    private Node insert(Node n, int v) {
        if (n == null) {
            return new Node(v);
        }
        if (v < n.val) {
            n.left = insert(n.left, v); 
        }else {
            n.right = insert(n.right, v);
        }
        n.size = 1 + size(n.left) + size(n.right);
        return n;
    }

    /* 刪除：BST 刪除三情況，最後更新 size */
    private Node delete(Node n, int v) {
        if (n == null) {
            return null;
        }
        if (v < n.val) {
            n.left = delete(n.left, v); 
        }else if (v > n.val) {
            n.right = delete(n.right, v); 
        }else { // 命中
            if (n.left == null) {
                return n.right;
            }
            if (n.right == null) {
                return n.left;
            }
            // 兩子樹→ 取後繼
            Node succ = minNode(n.right);
            n.val = succ.val;
            n.right = delete(n.right, succ.val);
        }
        n.size = 1 + size(n.left) + size(n.right);
        return n;
    }

    /* 找子樹最小節點 */
    private Node minNode(Node n) {
        while (n.left != null) {
            n = n.left;
        }
        return n;
    }

    /* 取第 k 小：左子樹 size = L
       k==L+1 → 這個節點；k≤L → 左；否則到右且 k-=L+1 */
    private int kth(Node n, int k) {
        if (n == null || k <= 0 || k > size(n)) {
            throw new IllegalArgumentException("k 超出範圍");
        }
        int L = size(n.left);
        if (k == L + 1) {
            return n.val;
        }
        if (k <= L) {
            return kth(n.left, k); 
        }else {
            return kth(n.right, k - L - 1);
        }
    }

    /* 協助取得第 k~j 小 (中序 + 剪枝) */
    private void range(Node n, int k, int j, List<Integer> res) {
        if (n == null || k > j) {
            return;
        }
        int L = size(n.left);
        int rank = L + 1;               // n 的排名
        if (k <= L) {
            range(n.left, k, j, res);
        }
        if (k <= rank && rank <= j) {
            res.add(n.val);
        }
        if (j > rank) {
            range(n.right, k - rank, j - rank, res);
        }
    }

    /* null safe size() */
    private int size(Node n) {
        return n == null ? 0 : n.size;
    }

    /* ---------------- Demo ---------------- */
    public static void main(String[] args) {
        BSTKthElement bst = new BSTKthElement();
        int[] vals = {20, 10, 30, 5, 15, 25, 40};
        for (int v : vals) {
            bst.insert(v);
        }

        System.out.println("節點總數 = " + bst.size());   // 7
        System.out.println("第 3 小  = " + bst.kthSmallest(3)); // 15
        System.out.println("第 2 大  = " + bst.kthLargest(2));  // 30
        System.out.println("第 2~5 小= " + bst.rangeKtoJ(2, 5)); // [10,15,20,25]

        bst.delete(20);                                    // 刪根
        System.out.println("\n刪 20 後第 3 小 = " + bst.kthSmallest(3)); // 25
        bst.insert(17);
        System.out.println("插 17 後第 3 小 = " + bst.kthSmallest(3)); // 17
    }
}

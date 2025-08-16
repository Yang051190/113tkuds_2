
import java.util.*;

public class PersistentAVLExercise {

    // 不可變節點定義
    public static class Node {

        final int value;
        final int height;
        final Node left;
        final Node right;

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
            this.height = 1 + Math.max(height(left), height(right));
        }

        static int height(Node node) {
            return node == null ? 0 : node.height;
        }

        int balanceFactor() {
            return height(left) - height(right);
        }
    }

    private final List<Node> versions = new ArrayList<>();

    // 建立初始版本（空樹）
    public PersistentAVLExercise() {
        versions.add(null); // version 0 是空的
    }

    public void insert(int versionIndex, int value) {
        Node root = versions.get(versionIndex);
        Node newRoot = insertNode(root, value);
        versions.add(newRoot); // 建立新版本
    }

    private Node insertNode(Node node, int value) {
        if (node == null) {
            return new Node(value, null, null);
        }

        if (value < node.value) {
            return balance(new Node(node.value, insertNode(node.left, value), node.right));
        } else if (value > node.value) {
            return balance(new Node(node.value, node.left, insertNode(node.right, value)));
        } else {
            return node; // 不插入重複值
        }
    }

    private Node balance(Node node) {
        int balance = node.balanceFactor();

        // 左重
        if (balance > 1) {
            if (node.left.balanceFactor() >= 0) {
                return rotateRight(node); // LL
            } else {
                return rotateRight(new Node(node.value, rotateLeft(node.left), node.right)); // LR
            }
        }

        // 右重
        if (balance < -1) {
            if (node.right.balanceFactor() <= 0) {
                return rotateLeft(node); // RR
            } else {
                return rotateLeft(new Node(node.value, node.left, rotateRight(node.right))); // RL
            }
        }

        return node;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        return new Node(y.value, new Node(x.value, x.left, T2), y.right);
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        return new Node(x.value, x.left, new Node(y.value, T2, y.right));
    }

    // 查詢指定版本中是否包含某值
    public boolean contains(int versionIndex, int value) {
        Node root = versions.get(versionIndex);
        return search(root, value);
    }

    private boolean search(Node node, int value) {
        if (node == null) {
            return false;
        }
        if (value == node.value) {
            return true;
        }
        if (value < node.value) {
            return search(node.left, value);
        }
        return search(node.right, value);
    }

    // 印出某版本的中序結果
    public void printInOrder(int versionIndex) {
        Node root = versions.get(versionIndex);
        System.out.print("Version " + versionIndex + ": ");
        printInOrder(root);
        System.out.println();
    }

    private void printInOrder(Node node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.value + " ");
            printInOrder(node.right);
        }
    }

    // 回傳目前版本總數（含初始空樹）
    public int getVersionCount() {
        return versions.size();
    }

    // 測試用主程式
    public static void main(String[] args) {
        PersistentAVLExercise tree = new PersistentAVLExercise();

        tree.insert(0, 10); // version 1
        tree.insert(1, 20); // version 2
        tree.insert(2, 5);  // version 3
        tree.insert(3, 6);  // version 4

        for (int i = 0; i < tree.getVersionCount(); i++) {
            tree.printInOrder(i);
        }

        System.out.println("Version 2 contains 20? " + tree.contains(2, 20)); // true
        System.out.println("Version 2 contains 6? " + tree.contains(2, 6));   // false
    }
}


import java.util.*;

public class AVLRangeQueryExercise {

    static class Node {

        int key, height;
        Node left, right;

        Node(int key) {
            this.key = key;
            this.height = 1;
        }
    }

    static class AVLTree {

        Node root;

        int height(Node node) {
            return (node == null) ? 0 : node.height;
        }

        int getBalance(Node node) {
            return (node == null) ? 0 : height(node.left) - height(node.right);
        }

        Node rightRotate(Node y) {
            Node x = y.left;
            Node T2 = x.right;

            x.right = y;
            y.left = T2;

            y.height = Math.max(height(y.left), height(y.right)) + 1;
            x.height = Math.max(height(x.left), height(x.right)) + 1;

            return x;
        }

        Node leftRotate(Node x) {
            Node y = x.right;
            Node T2 = y.left;

            y.left = x;
            x.right = T2;

            x.height = Math.max(height(x.left), height(x.right)) + 1;
            y.height = Math.max(height(y.left), height(y.right)) + 1;

            return y;
        }

        Node insert(Node node, int key) {
            if (node == null) {
                return new Node(key);
            }

            if (key < node.key) {
                node.left = insert(node.left, key); 
            }else if (key > node.key) {
                node.right = insert(node.right, key); 
            }else {
                return node;
            }

            node.height = 1 + Math.max(height(node.left), height(node.right));
            int balance = getBalance(node);

            if (balance > 1 && key < node.left.key) {
                return rightRotate(node);
            }
            if (balance < -1 && key > node.right.key) {
                return leftRotate(node);
            }
            if (balance > 1 && key > node.left.key) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
            if (balance < -1 && key < node.right.key) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }

            return node;
        }

        public void insert(int key) {
            root = insert(root, key);
        }

        public List<Integer> rangeQuery(int min, int max) {
            List<Integer> result = new ArrayList<>();
            rangeQueryHelper(root, min, max, result);
            return result;
        }

        private void rangeQueryHelper(Node node, int min, int max, List<Integer> result) {
            if (node == null) {
                return;
            }

            if (node.key > min) {
                rangeQueryHelper(node.left, min, max, result);
            }

            if (node.key >= min && node.key <= max) {
                result.add(node.key);
            }

            if (node.key < max) {
                rangeQueryHelper(node.right, min, max, result);
            }
        }

        public void printTree() {
            inOrder(root);
            System.out.println();
        }

        private void inOrder(Node node) {
            if (node != null) {
                inOrder(node.left);
                System.out.print(node.key + " ");
                inOrder(node.right);
            }
        }
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        int[] keys = {20, 10, 30, 5, 15, 25, 35};
        for (int key : keys) {
            tree.insert(key);
        }

        System.out.println("樹中所有節點（中序遍歷）：");
        tree.printTree();

        List<Integer> result = tree.rangeQuery(12, 28);
        System.out.println("範圍 [12, 28] 的節點：");
        System.out.println(result);  // 應該輸出 [15, 20, 25]
    }
}


public class AVLRotationExercise {

    static class Node {

        int key;
        Node left, right;
        int height;

        Node(int key) {
            this.key = key;
            this.height = 1;
        }
    }

    static class AVLTree {

        Node root;

        // 插入節點
        Node insert(Node node, int key) {
            if (node == null) {
                return new Node(key);
            }

            if (key < node.key) {
                node.left = insert(node.left, key); 
            }else if (key > node.key) {
                node.right = insert(node.right, key); 
            }else {
                return node; // 不插入重複
            }
            // 更新高度
            node.height = 1 + Math.max(height(node.left), height(node.right));

            int balance = getBalance(node);

            // LL 右旋
            if (balance > 1 && key < node.left.key) {
                return rightRotate(node);
            }

            // RR 左旋
            if (balance < -1 && key > node.right.key) {
                return leftRotate(node);
            }

            // LR 左右旋
            if (balance > 1 && key > node.left.key) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }

            // RL 右左旋
            if (balance < -1 && key < node.right.key) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }

            return node;
        }

        // 左旋
        Node leftRotate(Node x) {
            Node y = x.right;
            Node T2 = y.left;

            y.left = x;
            x.right = T2;

            x.height = Math.max(height(x.left), height(x.right)) + 1;
            y.height = Math.max(height(y.left), height(y.right)) + 1;

            return y;
        }

        // 右旋
        Node rightRotate(Node y) {
            Node x = y.left;
            Node T2 = x.right;

            x.right = y;
            y.left = T2;

            y.height = Math.max(height(y.left), y.right == null ? 0 : height(y.right)) + 1;
            x.height = Math.max(height(x.left), height(x.right)) + 1;

            return x;
        }

        int height(Node node) {
            return (node == null) ? 0 : node.height;
        }

        int getBalance(Node node) {
            return (node == null) ? 0 : height(node.left) - height(node.right);
        }

        // 中序列印樹
        void printInOrder(Node node) {
            if (node != null) {
                printInOrder(node.left);
                System.out.print(node.key + " ");
                printInOrder(node.right);
            }
        }

        // 前序列印結構 + 平衡因子
        void printPreOrder(Node node) {
            if (node != null) {
                System.out.print(node.key + "(" + getBalance(node) + ") ");
                printPreOrder(node.left);
                printPreOrder(node.right);
            }
        }
    }

    public static void main(String[] args) {
        AVLTree tree;

        // 左旋 (RR)
        System.out.println("▶ 測試：RR 左旋");
        tree = new AVLTree();
        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 20);
        tree.root = tree.insert(tree.root, 30);
        tree.printPreOrder(tree.root);
        System.out.println("\n");

        // 右旋 (LL)
        System.out.println("▶ 測試：LL 右旋");
        tree = new AVLTree();
        tree.root = tree.insert(tree.root, 30);
        tree.root = tree.insert(tree.root, 20);
        tree.root = tree.insert(tree.root, 10);
        tree.printPreOrder(tree.root);
        System.out.println("\n");

        // 左右旋 (LR)
        System.out.println("▶ 測試：LR 左右旋");
        tree = new AVLTree();
        tree.root = tree.insert(tree.root, 30);
        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 20);
        tree.printPreOrder(tree.root);
        System.out.println("\n");

        // 右左旋 (RL)
        System.out.println("▶ 測試：RL 右左旋");
        tree = new AVLTree();
        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 30);
        tree.root = tree.insert(tree.root, 20);
        tree.printPreOrder(tree.root);
        System.out.println("\n");
    }
}


public class AVLDeleteExercise {

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

        Node delete(Node node, int key) {
            if (node == null) {
                return null;
            }

            if (key < node.key) {
                node.left = delete(node.left, key); 
            }else if (key > node.key) {
                node.right = delete(node.right, key); 
            }else {
                // 一或零子節點
                if (node.left == null || node.right == null) {
                    Node temp = (node.left != null) ? node.left : node.right;
                    if (temp == null) {
                        return null;
                    } else {
                        return temp;
                    }
                }

                // 兩個子節點 -> 找右子樹最小值
                Node successor = findMin(node.right);
                node.key = successor.key;
                node.right = delete(node.right, successor.key);
            }

            node.height = 1 + Math.max(height(node.left), height(node.right));
            int balance = getBalance(node);

            // 左重
            if (balance > 1 && getBalance(node.left) >= 0) {
                return rightRotate(node);
            }
            if (balance > 1 && getBalance(node.left) < 0) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }

            // 右重
            if (balance < -1 && getBalance(node.right) <= 0) {
                return leftRotate(node);
            }
            if (balance < -1 && getBalance(node.right) > 0) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }

            return node;
        }

        Node findMin(Node node) {
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }

        void printInOrder(Node node) {
            if (node != null) {
                printInOrder(node.left);
                System.out.print(node.key + " ");
                printInOrder(node.right);
            }
        }

        void printTree() {
            printInOrder(root);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        // 插入測試資料
        int[] keys = {20, 10, 30, 5, 15, 25, 35};
        for (int key : keys) {
            tree.root = tree.insert(tree.root, key);
        }

        System.out.println("原始樹：");
        tree.printTree();

        System.out.println("\n刪除葉子節點 5：");
        tree.root = tree.delete(tree.root, 5);
        tree.printTree();

        System.out.println("\n刪除只有一個子節點的節點 35：");
        tree.root = tree.delete(tree.root, 35);
        tree.printTree();

        System.out.println("\n刪除有兩個子節點的節點 20：");
        tree.root = tree.delete(tree.root, 20);
        tree.printTree();
    }
}

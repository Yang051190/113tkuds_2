
public class AVLBasicExercise {

    // 節點類別
    static class Node {

        int data;
        Node left, right;
        int height;

        Node(int data) {
            this.data = data;
            this.height = 1;
        }
    }

    // 主 AVL 樹類別
    static class AVLTree {

        private Node root;

        // 插入節點
        public void insert(int data) {
            root = insertRec(root, data);
        }

        private Node insertRec(Node node, int data) {
            if (node == null) {
                return new Node(data);
            }

            if (data < node.data) {
                node.left = insertRec(node.left, data);
            } else if (data > node.data) {
                node.right = insertRec(node.right, data);
            } else {
                return node; // 不插入重複
            }

            // 更新高度
            node.height = 1 + Math.max(height(node.left), height(node.right));

            // 計算平衡因子
            int balance = getBalance(node);

            // 左左
            if (balance > 1 && data < node.left.data) {
                return rightRotate(node);
            }

            // 右右
            if (balance < -1 && data > node.right.data) {
                return leftRotate(node);
            }

            // 左右
            if (balance > 1 && data > node.left.data) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }

            // 右左
            if (balance < -1 && data < node.right.data) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }

            return node;
        }

        // 搜尋節點
        public boolean search(int data) {
            return searchRec(root, data);
        }

        private boolean searchRec(Node node, int data) {
            if (node == null) {
                return false;
            }
            if (data == node.data) {
                return true;
            }
            return data < node.data ? searchRec(node.left, data) : searchRec(node.right, data);
        }

        // 計算樹高
        public int getTreeHeight() {
            return height(root);
        }

        private int height(Node node) {
            return (node == null) ? 0 : node.height;
        }

        private int getBalance(Node node) {
            return (node == null) ? 0 : height(node.left) - height(node.right);
        }

        // 右旋
        private Node rightRotate(Node y) {
            Node x = y.left;
            Node T2 = x.right;

            x.right = y;
            y.left = T2;

            y.height = Math.max(height(y.left), height(y.right)) + 1;
            x.height = Math.max(height(x.left), height(x.right)) + 1;

            return x;
        }

        // 左旋
        private Node leftRotate(Node x) {
            Node y = x.right;
            Node T2 = y.left;

            y.left = x;
            x.right = T2;

            x.height = Math.max(height(x.left), height(x.right)) + 1;
            y.height = Math.max(height(y.left), height(y.right)) + 1;

            return y;
        }

        // 驗證 AVL 屬性是否正確
        public boolean isValidAVL() {
            return checkAVL(root) != -1;
        }

        private int checkAVL(Node node) {
            if (node == null) {
                return 0;
            }

            int lh = checkAVL(node.left);
            int rh = checkAVL(node.right);

            if (lh == -1 || rh == -1 || Math.abs(lh - rh) > 1) {
                return -1;
            }

            return Math.max(lh, rh) + 1;
        }

        // 中序列印
        public void printInOrder() {
            printInOrderRec(root);
            System.out.println();
        }

        private void printInOrderRec(Node node) {
            if (node != null) {
                printInOrderRec(node.left);
                System.out.print(node.data + "(" + getBalance(node) + ") ");
                printInOrderRec(node.right);
            }
        }
    }

    // 主程式
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        // 插入節點
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(10);
        tree.insert(25);
        tree.insert(50);

        // 印出樹
        System.out.println("In-order traversal:");
        tree.printInOrder();

        // 搜尋節點
        System.out.println("Search 25: " + tree.search(25));  // true
        System.out.println("Search 100: " + tree.search(100)); // false

        // 樹高度
        System.out.println("Tree height: " + tree.getTreeHeight());

        // 是否為有效 AVL 樹
        System.out.println("Is valid AVL? " + tree.isValidAVL());
    }
}

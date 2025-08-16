
public class AVLTree {

    private AVLNode root;

    private int getHeight(AVLNode node) {
        return (node != null) ? node.getHeight() : 0;
    }

    public void insert(int data) {
        root = insertNode(root, data);
    }

    private AVLNode insertNode(AVLNode node, int data) {
        if (node == null) {
            return new AVLNode(data);
        }

        if (data < node.getData()) {
            node.setLeft(insertNode(node.getLeft(), data));
        } else if (data > node.getData()) {
            node.setRight(insertNode(node.getRight(), data));
        } else {
            return node;
        }

        node.updateHeight();
        int balance = node.getBalance();

        // LL
        if (balance > 1 && data < node.getLeft().getData()) {
            return rightRotate(node);
        }

        // RR
        if (balance < -1 && data > node.getRight().getData()) {
            return leftRotate(node);
        }

        // LR
        if (balance > 1 && data > node.getLeft().getData()) {
            node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node);
        }

        // RL
        if (balance < -1 && data < node.getRight().getData()) {
            node.setRight(rightRotate(node.getRight()));
            return leftRotate(node);
        }

        return node;
    }

    public boolean search(int data) {
        return searchNode(root, data);
    }

    private boolean searchNode(AVLNode node, int data) {
        if (node == null) {
            return false;
        }
        if (data == node.getData()) {
            return true;
        }
        if (data < node.getData()) {
            return searchNode(node.getLeft(), data);
        }
        return searchNode(node.getRight(), data);
    }

    private AVLNode findMin(AVLNode node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    public void delete(int data) {
        root = deleteNode(root, data);
    }

    private AVLNode deleteNode(AVLNode node, int data) {
        if (node == null) {
            return null;
        }

        if (data < node.getData()) {
            node.setLeft(deleteNode(node.getLeft(), data));
        } else if (data > node.getData()) {
            node.setRight(deleteNode(node.getRight(), data));
        } else {
            if (node.getLeft() == null || node.getRight() == null) {
                AVLNode temp = (node.getLeft() != null) ? node.getLeft() : node.getRight();
                if (temp == null) {
                    return null;
                } else {
                    node.setData(temp.getData());
                    node.setLeft(temp.getLeft());
                    node.setRight(temp.getRight());
                    node.setHeight(temp.getHeight());
                }
            } else {
                AVLNode temp = findMin(node.getRight());
                node.setData(temp.getData());
                node.setRight(deleteNode(node.getRight(), temp.getData()));
            }
        }

        if (node == null) {
            return null;
        }

        node.updateHeight();
        int balance = node.getBalance();

        if (balance > 1 && node.getLeft().getBalance() >= 0) {
            return rightRotate(node);
        }

        if (balance > 1 && node.getLeft().getBalance() < 0) {
            node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node);
        }

        if (balance < -1 && node.getRight().getBalance() <= 0) {
            return leftRotate(node);
        }

        if (balance < -1 && node.getRight().getBalance() > 0) {
            node.setRight(rightRotate(node.getRight()));
            return leftRotate(node);
        }

        return node;
    }

    private AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.getLeft();
        AVLNode T2 = x.getRight();

        x.setRight(y);
        y.setLeft(T2);

        y.updateHeight();
        x.updateHeight();

        return x;
    }

    private AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.getRight();
        AVLNode T2 = y.getLeft();

        y.setLeft(x);
        x.setRight(T2);

        x.updateHeight();
        y.updateHeight();

        return y;
    }

    public boolean isValidAVL() {
        return checkAVL(root) != -1;
    }

    private int checkAVL(AVLNode node) {
        if (node == null) {
            return 0;
        }
        int lh = checkAVL(node.getLeft());
        int rh = checkAVL(node.getRight());

        if (lh == -1 || rh == -1) {
            return -1;
        }
        if (Math.abs(lh - rh) > 1) {
            return -1;
        }

        return Math.max(lh, rh) + 1;
    }

    public void printTree() {
        printInOrder(root);
        System.out.println();
    }

    private void printInOrder(AVLNode node) {
        if (node != null) {
            printInOrder(node.getLeft());
            System.out.print(node.getData() + "(" + node.getBalance() + ") ");
            printInOrder(node.getRight());
        }
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(10);
        tree.insert(25);
        tree.insert(50);

        System.out.println("Tree:");
        tree.printTree();

        System.out.println("Search 25: " + tree.search(25));
        System.out.println("Search 60: " + tree.search(60));

        tree.delete(20);
        System.out.println("After delete 20:");
        tree.printTree();

        System.out.println("Is valid AVL? " + tree.isValidAVL());
    }
}

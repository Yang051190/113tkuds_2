
public class AVLNode {

    private int data;
    private AVLNode left, right;
    private int height;

    public AVLNode(int data) {
        this.data = data;
        this.height = 1;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public AVLNode getLeft() {
        return left;
    }

    public void setLeft(AVLNode left) {
        this.left = left;
    }

    public AVLNode getRight() {
        return right;
    }

    public void setRight(AVLNode right) {
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    // 計算平衡因子
    public int getBalance() {
        int leftHeight = (left != null) ? left.height : 0;
        int rightHeight = (right != null) ? right.height : 0;
        return leftHeight - rightHeight;
    }

    // 更新節點高度
    public void updateHeight() {
        int leftHeight = (left != null) ? left.height : 0;
        int rightHeight = (right != null) ? right.height : 0;
        this.height = Math.max(leftHeight, rightHeight) + 1;
    }

    // ✅ main 方法 for 測試用途
    public static void main(String[] args) {
        AVLNode root = new AVLNode(10);
        AVLNode leftChild = new AVLNode(5);
        AVLNode rightChild = new AVLNode(15);

        root.setLeft(leftChild);
        root.setRight(rightChild);
        root.updateHeight();

        System.out.println("根節點資料: " + root.getData());
        System.out.println("節點高度: " + root.getHeight());
        System.out.println("平衡因子: " + root.getBalance());
    }
}

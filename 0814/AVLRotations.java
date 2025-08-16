
public class AVLRotations {

    // 右旋操作
    // 時間複雜度: O(1), 空間複雜度: O(1)
    public static AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.getLeft();
        AVLNode T2 = x.getRight();

        // 執行旋轉
        x.setRight(y);
        y.setLeft(T2);

        // 更新高度
        y.updateHeight();
        x.updateHeight();

        return x; // 新的根節點
    }

    // 左旋操作
    // 時間複雜度: O(1), 空間複雜度: O(1)
    public static AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.getRight();
        AVLNode T2 = y.getLeft();

        // 執行旋轉
        y.setLeft(x);
        x.setRight(T2);

        // 更新高度
        x.updateHeight();
        y.updateHeight();

        return y; // 新的根節點
    }

    // 測試 main 方法
    public static void main(String[] args) {
        // 建立一個不平衡的樹 (右旋案例)
        AVLNode y = new AVLNode(30);
        AVLNode x = new AVLNode(20);
        AVLNode z = new AVLNode(10);
        y.setLeft(x);
        x.setLeft(z);
        y.updateHeight();
        x.updateHeight();
        z.updateHeight();
        System.out.println("原本根節點: " + y.getData());
        AVLNode newRoot = rightRotate(y);
        System.out.println("右旋後新根節點: " + newRoot.getData());
        System.out.println("新根左子: " + (newRoot.getLeft() != null ? newRoot.getLeft().getData() : "null"));
        System.out.println("新根右子: " + (newRoot.getRight() != null ? newRoot.getRight().getData() : "null"));

        // 建立一個不平衡的樹 (左旋案例)
        AVLNode a = new AVLNode(10);
        AVLNode b = new AVLNode(20);
        AVLNode c = new AVLNode(30);
        a.setRight(b);
        b.setRight(c);
        a.updateHeight();
        b.updateHeight();
        c.updateHeight();
        System.out.println("\n原本根節點: " + a.getData());
        AVLNode newRoot2 = leftRotate(a);
        System.out.println("左旋後新根節點: " + newRoot2.getData());
        System.out.println("新根左子: " + (newRoot2.getLeft() != null ? newRoot2.getLeft().getData() : "null"));
        System.out.println("新根右子: " + (newRoot2.getRight() != null ? newRoot2.getRight().getData() : "null"));
    }
}

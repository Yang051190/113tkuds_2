
import java.util.*;

public class AVLLeaderboardSystem {

    class Node {

        int score;
        int size; // 節點總數（自己 + 左右子樹）
        Set<String> players;
        Node left, right;
        int height;

        Node(int score, String playerId) {
            this.score = score;
            this.players = new HashSet<>();
            this.players.add(playerId);
            this.height = 1;
            this.size = 1;
        }
    }

    private Node root;
    private Map<String, Integer> playerScoreMap = new HashMap<>();

    // 公開 API：新增玩家
    public void addScore(String playerId, int score) {
        if (playerScoreMap.containsKey(playerId)) {
            updateScore(playerId, score);
        } else {
            root = insert(root, score, playerId);
            playerScoreMap.put(playerId, score);
        }
    }

    // 公開 API：更新玩家分數
    public void updateScore(String playerId, int newScore) {
        if (!playerScoreMap.containsKey(playerId)) {
            return;
        }
        int oldScore = playerScoreMap.get(playerId);
        root = remove(root, oldScore, playerId);
        root = insert(root, newScore, playerId);
        playerScoreMap.put(playerId, newScore);
    }

    // 公開 API：取得玩家排名（第幾名）
    public int getRank(String playerId) {
        if (!playerScoreMap.containsKey(playerId)) {
            return -1;
        }
        int score = playerScoreMap.get(playerId);
        return getRank(root, score, playerId);
    }

    // 公開 API：查詢前 K 名玩家
    public List<String> getTopK(int k) {
        List<String> result = new ArrayList<>();
        getTopK(root, result, k);
        return result;
    }

    // 插入節點
    private Node insert(Node node, int score, String playerId) {
        if (node == null) {
            return new Node(score, playerId);
        }

        if (score > node.score) {
            node.left = insert(node.left, score, playerId);
        } else if (score < node.score) {
            node.right = insert(node.right, score, playerId);
        } else {
            node.players.add(playerId);
        }

        return balance(node);
    }

    // 刪除節點
    private Node remove(Node node, int score, String playerId) {
        if (node == null) {
            return null;
        }

        if (score > node.score) {
            node.left = remove(node.left, score, playerId);
        } else if (score < node.score) {
            node.right = remove(node.right, score, playerId);
        } else {
            node.players.remove(playerId);
            if (node.players.isEmpty()) {
                if (node.left == null || node.right == null) {
                    node = (node.left != null) ? node.left : node.right;
                } else {
                    Node min = getMin(node.right);
                    node.score = min.score;
                    node.players = min.players;
                    node.right = deleteMin(node.right);
                }
            }
        }

        return balance(node);
    }

    // AVL 操作
    private Node balance(Node node) {
        updateHeightAndSize(node);
        int balance = getBalance(node);

        if (balance > 1 && getBalance(node.left) >= 0) {
            return rightRotate(node);
        }
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && getBalance(node.right) <= 0) {
            return leftRotate(node);
        }
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private int getBalance(Node node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    private int height(Node node) {
        return (node == null) ? 0 : node.height;
    }

    private int size(Node node) {
        return (node == null) ? 0 : node.size;
    }

    private void updateHeightAndSize(Node node) {
        if (node != null) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
            node.size = node.players.size() + size(node.left) + size(node.right);
        }
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeightAndSize(y);
        updateHeightAndSize(x);
        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeightAndSize(x);
        updateHeightAndSize(y);
        return y;
    }

    private Node getMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        return balance(node);
    }

    // 取得前 K 名玩家（中序反轉）
    private void getTopK(Node node, List<String> result, int k) {
        if (node == null || result.size() >= k) {
            return;
        }
        getTopK(node.left, result, k);
        if (result.size() < k) {
            result.addAll(node.players);
        }
        getTopK(node.right, result, k);
    }

    // 排名查詢：比該玩家高的總人數 + 1
    private int getRank(Node node, int score, String playerId) {
        if (node == null) {
            return 0;
        }

        if (score > node.score) {
            return getRank(node.left, score, playerId);
        } else if (score < node.score) {
            return size(node.left) + node.players.size() + getRank(node.right, score, playerId);
        } else {
            int count = size(node.left);
            for (String p : node.players) {
                if (p.equals(playerId)) {
                    break;
                }
                count++;
            }
            return count + 1;
        }
    }

    // 測試範例
    public static void main(String[] args) {
        AVLLeaderboardSystem leaderboard = new AVLLeaderboardSystem();

        leaderboard.addScore("Alice", 90);
        leaderboard.addScore("Bob", 100);
        leaderboard.addScore("Charlie", 85);
        leaderboard.addScore("David", 100);
        leaderboard.addScore("Eve", 70);

        System.out.println("Bob 的排名: " + leaderboard.getRank("Bob"));       // 1
        System.out.println("Alice 的排名: " + leaderboard.getRank("Alice"));   // 3

        System.out.println("前 3 名玩家: " + leaderboard.getTopK(3));          // Bob, David, Alice

        leaderboard.updateScore("Alice", 110); // Alice 衝上第一名

        System.out.println("Alice 更新後的排名: " + leaderboard.getRank("Alice")); // 1
        System.out.println("前 3 名玩家: " + leaderboard.getTopK(3));              // Alice, Bob, David
    }
}

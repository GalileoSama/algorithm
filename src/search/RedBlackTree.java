package search;

/**
 * @author galileo
 * @date 2019/6/13 20:40
 */
public class RedBlackTree<Key extends Comparable<Key>, Value> {
    private final static  boolean RED = true;
    private final static  boolean BLACK = false;
    private Node root;

    class Node{
        Key key;
        Value value;
        Node left, right;
        boolean color;
        int n;

        public Node(Key key, Value value, boolean color, int n) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.n = n;
        }
    }

    /** 节点数 **/
    public int size(){
        return size(root);
    }

    private int size(Node x){
        if (x == null){
            return 0;
        }
        return x.n;
    }

    /** 判断颜色是否为红色 **/
    private boolean isRed(Node node){
        if (node == null){
            return false;
        }
        return RED == node.color;
    }

    /** 右旋转 **/
    public Node rotateRight(Node h){
        assert isRed(h.left);
        //h的右节点 比h大 继承h的一切成为新的根
        Node x = h.left;

        h.left = x.right;
        x.right = h;

        x.color = h.color;
        h.color = RED;

        x.n = h.n;
        h.n = 1 + size(h.right) + size(h.left);
        return x;
    }

    /** 左旋转 **/
    public Node rotateLeft(Node h){
        assert isRed(h.right);
        //h的右节点 比h大 继承h的一切成为新的根
        Node x = h.right;

        h.right = x.left;
        x.left = h;

        x.color = h.color;
        h.color = RED;

        x.n = h.n;
        h.n = 1 + size(h.left) + size(h.right);
        return x;
    }

    public void flipColor(Node h){
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    /** 递归get **/
    public Value get(Key key){
        return get(root, key);
    }

    private Value get(Node node, Key key){
        if (node == null){
            return null;
        }
        int compare = node.key.compareTo(key);
        if (compare < 0){
            return get(node.right, key);
        }else if (compare > 0){
            return get(node.left, key);
        }else {
            return node.value;
        }
    }

    /** 插入 **/
    public void put(Key key, Value value){
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node h, Key key, Value value){
        if (h == null){
            return new Node(key, value, RED, 1);
        }

        int compare = key.compareTo(h.key);
        if (compare < 0){
            h.left = put(h.left, key, value);
        }else if (compare > 0){
            h.right = put(h.right, key, value);
        }else {
            h.value = value;
        }

        if (!isRed(h.left) && isRed(h.right) ){
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)){
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)){
            flipColor(h);
        }

        h.n = 1 + size(h.left) + size(h.right);
        return h;
    }
}

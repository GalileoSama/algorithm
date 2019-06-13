package search;

import java.security.Key;

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
    public Node retateLeft(Node h){
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
}

package search;

import java.security.Key;

/**
 * @author galileo
 * @date 2019/6/13 20:40
 */
public class RedBlackTree<Key extends Comparable<Key>, Value> {
    private final static  boolean RED = true;
    private final static  boolean BLACK = false;
    Node root;

    class Node{
        Key key;
        Value value;
        Node left, right;
        boolean color;
        int n;

        public Node(Node left, Node right, int n) {
            this.left = left;
            this.right = right;
            this.n = n;
        }
    }
}

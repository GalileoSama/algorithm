package search;

import edu.princeton.cs.algs4.Queue;

import java.util.Random;

/**
 * @author galileo
 * @date 2019/6/10 15:59
 */
public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node{
        private Key key;
        private Value value;
        private Node left, right;
        private int n;

        Node(Key key, Value value, int n) {
            this.key = key;
            this.value = value;
            this.n = n;
        }

        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    public int size(){
        return size(root);
    }

    private int size(Node x){
        if (x == null){
            return 0;
        }
        return x.n;
    }

    /** 循环get **/
    public Value getByLoop(Key key){
        Node node = root;
        while (node != null){
            int compare = node.key.compareTo(key);
            if (compare < 0){
                node = node.right;
            }else if (compare > 0){
                node = node.left;
            }else {
                return node.value;
            }
        }
        return null;
    }
    /** 递归get **/
    public Value getByRecursion(Key key){
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

    /** 循环put 没有n **/
    public void putByLoop(Key key, Value value){
        Node newNode = new Node(key, value);
        if (root == null){
            root = newNode;
            return;
        }

        Node parent = null,node = root;
        while (node != null){
            parent = node;
            int compare = node.key.compareTo(key);
            if (compare < 0){
                node = node.right;
            }else if (compare > 0){
                node = node.left;
            }else {
                node.value =  value;
                return;
            }
        }

        int compare = parent.key.compareTo(key);
        if (compare < 0){
            parent.right = newNode;
        }else {
            parent.left = newNode;
        }
    }

    /** 递归put **/
    public void putByRecursion(Key key, Value value){
        root = put(root, key, value);
    }

    private Node put(Node node, Key key, Value value){
        if (node == null){
            return new Node(key, value, 1);
        }
        int compare = node.key.compareTo(key);
        if (compare < 0){
            node.right = put(node.right, key, value);
        }else if (compare > 0){
            node.left = put(node.left, key, value);
        }else {
            node.value = value;
        }
        node.n = size(node.left)+size(node.right)+1;
        return node;
    }

    /** 递归floor **/
    public Key floor(Key key){
        Node node = floor(root, key);
        if (node == null){
            return null;
        }
        return node.key;
    }

    private Node floor(Node node, Key key){
        if (node == null){
            return null;
        }
        int compare = node.key.compareTo(key);
        if (compare == 0){
            return node;
        }
        //左子树
        if (compare > 0){
            return floor(node.left, key);
        }
        //若右子树，要存在floor，才返回，否则返回根节点node
        Node t = floor(node.right, key);
        if (t != null){
            return t;
        }else {
            return node;
        }
    }

    /** 递归min **/
    public Key min(){
        return min(root).key;
    }

    private Node min(Node node){
        if (node.left == null){
            return node;
        }
        return min(node.left);
    }

    /** 递归max **/
    public Key max(){
        return max(root).key;
    }

    private Node max(Node node){
        if (node.right == null){
            return node;
        }
        return max(node.right);
    }

    /** 递归select **/
    public Key select(int k){
        return select(root, k).key;
    }

    private Node select(Node node, int k){
        if (node == null){
            return null;
        }
        int size = size(node);
        if (size > k){
            return select(node.left, k);
        }
        else if (size < k){
            return select(node.right, k - size - 1);
        }
        else {
           return node;
        }

    }

    /** 递归rank **/
    public int rank(Key key){
        return rank(root, key);
    }

    private int rank(Node node, Key key){
        if (node == null){
            return 0;
        }
        int compare = key.compareTo(node.key);
        if (compare < 0){
            return rank(node.left, key);
        }else if (compare > 0){
            return 1 + size(node.left) + rank(node.right, key);
        }else {
            return size(node.left);
        }
    }

    /** 删除最小的节点 **/
    public void deleteMin(){
        deleteMin(root);
    }

    private Node deleteMin(Node node){
        if (node.left == null){
            return node.right;
        }
        node.left = deleteMin(node.left);
        node.n = 1 + size(node.left) + size(node.right);
        return node;
    }

    /** 删除 **/
    public void delete(Key key){
        root = delete(root, key);
    }

    private Node delete(Node node, Key key){
        if (node == null){
            return null;
        }
        //寻找要删除的节点（键为key）
        int compare = key.compareTo(node.key);
        if (compare < 0){
            node.left = delete(node.left, key);
        }else if (compare > 0){
            node.right = delete(node.right, key);
        }else {
            //若无右子树，把左子树给父节点（同deleteMin）
            if (node.right == null){
                return node.left;
            }
            //若无左子树，把右子树给父节点
            if (node.left == null){
                return node.right;
            }
            //见算法4th P260 ：四个步骤
            Node t = node;
            node = min(t.right);
            node.right = deleteMin(node.right);
            node.left = t.left;
        }
        node.n = 1 + size(node.left) + size(node.right);
        return node;
    }

    /** 范围查询 **/
    public Iterable<Key> keys(){
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi){
        Queue<Key> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node node, Queue<Key> queue, Key lo, Key hi){
        if (node == null){
            return;
        }
        int compareLo = lo.compareTo(node.key);
        int compareHi = hi.compareTo(node.key);
        if (compareLo < 0){
            keys(node.left, queue, lo, hi);
        }
        if (compareLo <= 0 && compareHi >= 0){
            queue.enqueue(node.key);
        }
        if (compareHi > 0){
            keys(node.right, queue, lo, hi);
        }
    }

    /** BST的高度 **/
    public int height(){
        return height(root);
    }

    private int height(Node node){
        if (node == null){
            return -1;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public static void main(String[] args) {
        System.out.println("=====start=====");
        Random random = new Random();
        BST<Integer, Integer> bst = new BST<>();
        System.out.println("======测试递归put======");

        for (int i = 0; i<10;i++){
            int k = random.nextInt(30);
            bst.putByRecursion(k, i);
        }
        //用于测试delete和rank用
        bst.putByRecursion(13, 123);
        Iterable<Integer> keys = bst.keys();
        keys.forEach(k ->System.out.println("keys : " + k ));

        System.out.println("======测试floor======");
        System.out.println(bst.floor(11));
        System.out.println("======测试rank======");
        System.out.println(bst.rank(13));
        System.out.println("======测试min======");
        System.out.println(bst.min());
        System.out.println("======测试max======");
        System.out.println(bst.max());
        System.out.println("======测试height======");
        System.out.println(bst.height());
        System.out.println("======测试deleteMin======");
        bst.deleteMin();
        System.out.println(bst.min());
        System.out.println("======测试delete======");
        bst.delete(13);
        Iterable<Integer> keys1 = bst.keys();
        keys1.forEach(k ->System.out.println("keys : " + k ));
        System.out.println("======end======");
    }
}

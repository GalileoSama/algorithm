package search;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 * @author galileo
 * @date: 2019-05-31 下午3:02
 */
public class SequentialSearchST<Key, Value> {

    private Node first;
    int length;

    private class Node{
        Key key;
        Value value;
        Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public Value get(Key key){
        for (Node x = first;x != null;x = x.next){
            if (x.key.equals(key)){
                //命中
                return x.value;
            }
        }
        //未命中
        return null;
    }

    public void put(Key key, Value value){
        for (Node x = first;x != null;x = x.next){
            if (x.key.equals(key)){
                //命中
                x.value = value;
                return;
            }
        }
        //未命中
        first = new Node(key, value, first);
        length++;
    }

    public int size(){
        return length;
    }

    public void delete(Key key){
        first = delete(first, key);

    }

    /**
     * 根据key递归删除某个节点
     * 从第一个节点开始，设该key对应的节点为node1，其上一个节点是node
     * 删除操作：node.next = node1.next
     * @param node 从该节点开始递归
     * @param key 删除该key对应的node
     * @return
     */
    public Node delete(Node node, Key key){
        if (node == null){
            return null;
        }
        if (node.key.equals(key)){
            length--;
            return node.next;
        }
        node.next = delete(node.next, key);
        return node;
    }

    public Iterable<Key> keys(){

        Queue<Key> queue = new Queue<>();
        for (Node x = first;x != null;x = x.next){
            queue.enqueue(x.key);
        }
        return queue;
    }

    public static void main(String[] args) {
        SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
        String s = "a";
        for (int i = 0; i<10;i++){
            st.put(s+i, i);
        }

        for (String s1 : st.keys()) {
            StdOut.println("key"+s + " value:" + st.get(s1));
        }

        st.delete("a7");

        System.out.println("===after delete===");
        for (String s1 : st.keys()) {
            StdOut.println("key"+s + " value:" + st.get(s1));
        }
    }
}

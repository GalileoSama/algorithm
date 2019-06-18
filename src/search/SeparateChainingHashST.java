package search;

/**
 * @author galileo
 * @date 2019/6/18 15:27
 */
public class SeparateChainingHashST<Key extends Comparable<Key>, Value> {
    /** 数组长度 **/
    private int m;
    /** 链表数量 **/
    private int n;
    /** 链表数组 **/
    private SequentialSearchST<Key, Value>[] st;

    public SeparateChainingHashST() {
        this(997);
    }

    public SeparateChainingHashST(int m) {
        this.m = m;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
        for (int i = 0; i < m; i++){
            st[i] = new SequentialSearchST<>();
        }
    }

    private int hash(Key key){
        return (key.hashCode() & 0x7fffffff) % m;
    }

    public Value get(Key key){
        return st[hash(key)].get(key);
    }

    public void put(Key key, Value value){
        st[hash(key)].put(key, value);
    }

    public void delete(Key key){
        st[hash(key)].delete(key);
    }
}

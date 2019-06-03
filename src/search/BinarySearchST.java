package search;

/**
 * 二分查找
 * @author galileo
 * @date: 2019-06-03 下午3:08
 */
public class BinarySearchST<Key extends Comparable<Key>,Value> {
    private Key[] keys;
    private Value[] values;
    private int length = 0;
    private final static int INIT_CAPACITY=2;

    /**
     * Initializes an empty symbol table.
     */
    public BinarySearchST() {
        this(INIT_CAPACITY);
    }

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
    }

    private boolean isEmpty(){
        return length == 0;
    }

    /**
     * 动态增加数组容量
     * @param capacity 初始化数组大小
     */
    public void resize(int capacity){
        Key[] tempk = (Key[]) new Comparable[capacity];
        Value[] tempv = (Value[]) new Object[capacity];
        if (length >= 0) System.arraycopy(keys, 0, tempk, 0, length);
        if (length >= 0) System.arraycopy(values, 0, tempv, 0, length);
        this.keys = tempk;
        this.values = tempv;
    }

    public Value get(Key key){
        if (isEmpty()){
            return null;
        }
        int rank = rank(key);
        //命中
        //注：未保证一致性，只使用compareTo()，不使用equals()
        if (rank < length && keys[rank].compareTo(key) == 0){
            return values[rank];
        }
        //未命中
        return null;
    }

    public void put(Key key, Value value){
        int rank = rank(key);
        if (value == null){
            delete(key);
            return;
        }
        //若当前数组长度已经达到初始化长度，扩大一倍
        if (keys.length == length){
            resize(2*length);
        }
        //命中
        //注：未保证一致性，只使用compareTo()，不使用equals()
        if (rank < length && keys[rank].compareTo(key) == 0){
            values[rank] = value;
            return;
        }
        //未命中，为新值挪动位置
        for (int i = length;i > rank;i--){
            keys[i] = keys[i-1];
            values[i] = values[i-1];
        }
        keys[rank] = key;
        values[rank] = value;
        length ++;
    }

    public int rank(Key key){
        int lo = 0, hi = length - 1;
        //注：<=
        while (lo <= hi){
            int mid = (lo + hi)/2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp > 0){
                lo = mid + 1;
            }else if (cmp < 0){
                hi = mid - 1;
            }else {
                //1、命中，返回其排名
                return mid;
            }
        }
        //2、未命中，返回小于key的值的数量
        return lo;
    }

    public int size(){
        return length;
    }

    public Key floor(Key key){
        if (isEmpty()){
            return null;
        }
        int rank = rank(key);
        //命中
        //注：未保证一致性，只使用compareTo()，不使用equals()
        if (rank < length && keys[rank].compareTo(key) == 0){
            return keys[rank];
        }
        //未命中
        return keys[rank - 1];
    }

    public void delete(Key key){
        if (isEmpty()){
            return;
        }
        int rank = rank(key);
        if (rank < length && keys[rank].compareTo(key) == 0){
            //注意：i<length - 1 而不是length，否则会溢出
            for (int i = rank;i<length - 1;i++){
                keys[i] = keys[i+1];
                values[i] = values[i+1];
            }
            length--;
            keys[length] = null;
            values[length] = null;
        }
    }


    public static void main(String[] args) {
        System.out.println("=====start=====");
        BinarySearchST<String,Integer> binarySearchST = new BinarySearchST<>();
        binarySearchST.put("1",4);
        binarySearchST.put("2",4);
        binarySearchST.put("3",4);
        binarySearchST.put("4",4);
        binarySearchST.put("5",4);
        System.out.println("size:"+binarySearchST.size());
        System.out.println("key 1 value:"+binarySearchST.get("1"));
        System.out.println("cover key 1 with value 5");
        binarySearchST.put("1",5);
        System.out.println("size:"+binarySearchST.size());
        System.out.println("key 1 value:"+binarySearchST.get("1"));
        System.out.println("delete key 1");
        System.out.println("length："+binarySearchST.size());
        binarySearchST.delete("1");
        System.out.println("length："+binarySearchST.size());

        System.out.println("======end======");
    }
}

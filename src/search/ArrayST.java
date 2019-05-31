package search;

/**
 * 无序数组(动态增加长度) 顺序查找
 * @param <Key>
 * @param <Value>
 */
public class ArrayST<Key, Value> {

    private final static int INIT_CAPACITY=10;
    private Key[] keys;
    private Value[] values;
    private int length = 0;

    public ArrayST() {
        keys = (Key[]) new Object[INIT_CAPACITY];
        values = (Value[]) new Object[INIT_CAPACITY];
    }

    /**
     * 动态增加数组容量
     * @param capacity 初始化数组大小
     */
    public void resize(int capacity){
        Key[] tempk = (Key[]) new Object[capacity];
        Value[] tempv = (Value[]) new Object[capacity];
        if (length >= 0) System.arraycopy(keys, 0, tempk, 0, length);
        if (length >= 0) System.arraycopy(values, 0, tempv, 0, length);
        this.keys = tempk;
        this.values = tempv;
    }

    public Value get(Key key){
        int index = 0;
        int size = size();
        for (;index < size;index++){
            //命中，返回key
            if (keys[index].equals(key)){
                return values[index];
            }
        }
        //未命中，返回null
        return null;
    }

    public void put(Key key, Value value){
        //若当前数组长度已经达到初始化长度，扩大一倍
        if (keys.length == length){
            resize(2*length);
        }
        int index = 0;
        for (;index < length;index++){
            //命中，则覆盖
            if (keys[index].equals(key)){
                values[index] = value;
                return;
            }
        }
        //未命中，新建
        keys[length] = key;
        values[length] = value;
        this.length++;
    }

    public int size(){
        return length;
    }

    public static void main(String[] args) {
        System.out.println("=====start=====");
        ArrayST<Integer,Integer> arrayST = new ArrayST<>();
        arrayST.put(1,3);
        arrayST.put(2,3);
        arrayST.put(3,3);
        arrayST.put(4,3);
        arrayST.put(5,3);
        System.out.println("size:"+arrayST.size());
        System.out.println("key 1 value:"+arrayST.get(1));
        System.out.println("cover key 1 with value 5");
        arrayST.put(1,5);
        System.out.println("size:"+arrayST.size());
        System.out.println("key 1 value:"+arrayST.get(1));
        System.out.println("======end======");
    }
}

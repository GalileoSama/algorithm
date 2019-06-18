package search;

import java.util.Objects;

/**
 * @author galileo
 * @date 2019/6/18 16:20
 */
public class LinearProbingHashST<Key, Value> {
    private static final int INIT_CAPACITY = 4;
    /** 数组长度 **/
    private int m = 16;
    /** 链表数量 **/
    private int n;

    private Key[] keys;
    private Value[] values;

    public LinearProbingHashST() {
       this(INIT_CAPACITY);
    }

    public LinearProbingHashST(int cap) {
        keys = (Key[]) new Objects[cap];
        values = (Value[]) new Objects[cap];
        m = cap;
        n = 0;
    }

    private int hash(Key key){
        return (key.hashCode() & 0x7fffffff) % m;
    }

    private void resize(int cap){
        LinearProbingHashST<Key, Value> t = new LinearProbingHashST<>(cap);
        for (int i = 0;i < m;i++){
            if (keys[i] != null){
                t.put(keys[i], values[i]);
            }
        }
        keys = t.keys;
        values = t.values;
        m = t.m;
    }

    public Value get(Key key){
        for (int i = hash(key); keys[i] != null; i = (i + 1)%m){
            if (keys[i].equals(key)){
                return values[i];
            }
        }
        return null;
    }

    public void put(Key key, Value value){
        if (n > m/2){
            resize(2*m);
        }
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1)%m){
            //命中
            if (keys[i].equals(key)){
                keys[i] = key;
                return;
            }
        }
        //未命中
        keys[i] = key;
        values[i] = value;
        n++;
    }

    public void delete(Key key){
        //todo contains(key)
        //搜索key
        int i = hash(key);
        while (keys[i] != null){
            i = (i + 1) % m;
        }
        //删除
        keys[i] = null;
        values[i] = null;

        //重新插入后面的键值 直到遇到null
        i = (i + 1) % m;
        while (keys[i] != null){
            Key keyToRedo = keys[i];
            Value valueToRedo = values[i];
            //删除
            keys[i] = null;
            values[i] = null;
            //重新插入
            n--;
            put(keyToRedo, valueToRedo);
            i = (i + 1) % m;
        }
        n--;
        //将n控制在 [m/8,m/2]这个范围内
        if (n > 0 && n == m/8){
            resize(m/2);
        }
    }
}

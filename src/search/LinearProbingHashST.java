package search;

import java.util.Objects;

/**
 * @author galileo
 * @date 2019/6/18 16:20
 */
public class LinearProbingHashST<Key, Value> {
    /** 数组长度 **/
    private int m = 16;
    /** 链表数量 **/
    private int n;

    private Key[] keys;
    private Value[] values;

    public LinearProbingHashST() {
        keys = (Key[]) new Objects[m];
        values = (Value[]) new Objects[m];
    }

    private int hash(Key key){
        return (key.hashCode() & 0x7fffffff) % m;
    }

    private void resize(){

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
            resize();
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
        //将m控制在 [m/8,m/2]这个范围内
        if (n > 0 && n == m/8){
            resize();
        }
    }
}

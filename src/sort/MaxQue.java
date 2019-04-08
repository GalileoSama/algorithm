package sort;

/**
 * @author galileo
 * @date 2019/4/8 21:03
 */
public class MaxQue<Key extends Comparable<Key>>{

    private Key[] pq;
    private int N = 0;

    private void exchange(int i, int j){
        Key t = pq[j];
        pq[j] = pq[i];
        pq[i] = t;
    }

    private boolean less(int i, int j){
        return pq[i].compareTo(pq[j]) < 0;
    }

    /**上浮**/
    private void swim(int k){
        while (k > 1 && less(k/2, k)){
            exchange(k/2, k);
            k /= 2;
        }
    }

    /**下沉**/
    private void sink(int k){
        while (k*2 <= N){
            int child = 2 * k;
            //选出较大的子节点
            if (less(child, child + 1)){
                child++;
            }
            if (!less(k ,child)){
                break;
            }
            exchange(k, child);
            k = child;
        }
    }
}

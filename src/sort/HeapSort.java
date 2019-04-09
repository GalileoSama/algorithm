package sort;

/**
 * @author galileo
 * @date 2019/4/9 16:12
 */
public class HeapSort extends ProtoSort{

    /**
     *less和exchange的索引都减一，以使普通数组（索引从0开始）也能使用此排序
     */
    private static void exch(Comparable[] pq, int i, int j){
        Comparable t = pq[j-1];
        pq[j-1] = pq[i-1];
        pq[i-1] = t;
    }

    private static boolean less(Comparable[] pq, int i, int j){
        return pq[i-1].compareTo(pq[j-1]) < 0;
    }

    /**上浮**/
    private static void swim(Comparable[] pq, int k, int N){
        while (k > 1 && less(pq, k/2, k)){
            exch(pq, k/2, k);
            k /= 2;
        }
    }

    /**下沉**/
    private static void sink(Comparable[] pq, int k, int N){
        while (k*2 <= N){
            int child = 2 * k;
            //选出较大的子节点
            if (child<N && less(pq, child, child + 1)){
                child++;
            }
            if (!less(pq, k ,child)){
                break;
            }
            exch(pq, k, child);
            k = child;
        }
    }

    public static void sort(Comparable[] pq){
        int N = pq.length;
        //1、构造堆
        for (int k = N/2; k >= 1; k--){
            sink(pq, k, N);
        }
        while (N > 1){
            exch(pq, 1, N--);
            sink(pq, 1, N);
        }
    }

    /** test */
    public static void main(String[] arg){
        Integer[] a = {1, 4, 5, 2, 6, 11, 8, 123, 12, 14, 31, 3, 51, 77, 888, 91};
        sort(a);
        assert isSorted(a);
        show(a);
    }
}

package sort;

import edu.princeton.cs.algs4.Stopwatch;

/**
 * 插入排序
 * @author 王俊杰
 * @date 2019/3/4 10:01
 */
public class InsertSort extends ProtoSort{
    /** 默认正序排序 */
    public static void sort(Comparable a[]){
        for (int i = 1; i < a.length; i++){
            for (int j = i; j>0 && less(a[j],a[j-1]); j--){
                exchange(a,j,j-1);
            }
        }
    }

    /**
     * 加速的插入排序，不交换
     */
    public static void sortWithoutExchange(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            Comparable temp = a[i];
            int j = i;
            for (; j > 0 && less(temp, a[j - 1]); j--) {
                a[j] = a[j - 1];
            }
            a[j] = temp;
        }
    }

    /** test */
    public static void main(String[] arg){
        Integer[] a = {1, 4, 5, 2, 6, 11, 8};
        sort(a);
        assert isSorted(a);
        show(a);
    }
}

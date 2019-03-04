package sort;

/**
 * @author 王俊杰
 * @date 2019/3/4 14:59
 */
public class ProtoSort {

    /** 比较v是否小于w */
    static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

    /** 交换数组a的第i与第j个索引的值 */
    static void exchange(Comparable[] a, int i, int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /** 显示结果 */
    static void show(Comparable[] a){
        for (Comparable anA : a) {
            System.out.print(anA + " ");
        }
    }

    /** 验证是否按照升序排序 */
    static boolean isSorted(Comparable[] a){
        for (int i = 1;i < a.length;i++){
            if (less(a[i], a[i - 1])){
                return false;
            }
        }
        return true;
    }
}

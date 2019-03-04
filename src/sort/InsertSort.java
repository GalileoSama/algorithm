package sort;

/**
 * 插入排序
 * @author 王俊杰
 * @date 2019/3/4 10:01
 */
public class InsertSort {
    /** 默认正序排序 */
    public static void sort(Comparable a[]){
        for (int i = 1; i < a.length; i++){
            for (int j = i; j>0 && less(a[j],a[j-1]); j--){
                exchange(a,j,j-1);
            }
        }
    }

    /** 比较v是否小于w */
    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

    /** 交换数组a的第i与第j个索引的值 */
    private static void exchange(Comparable[] a, int i, int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /** 显示结果 */
    private static void show(Comparable[] a){
        for (Comparable anA : a) {
            System.out.print(anA + " ");
        }
    }

    /** 验证是否按照升序排序 */
    private static boolean isSorted(Comparable[] a){
        for (int i = 1;i < a.length;i++){
            if (less(a[i], a[i - 1])){
                return false;
            }
        }
        return true;
    }

    /** test */
    public static void main(String[] arg){
        Integer[] a = {1, 4, 5, 2, 6, 11, 8};
        sort(a);
        assert isSorted(a);
        show(a);
    }
}

package sort;

/**
 * @author galileo
 * @date 2019/12/2 10:15
 */
public class Select extends ProtoSort {
    private static void sort(Comparable[] a) {
        //todo 注：外层循环，退出循环条件为a.length-1，也可以为a.length，但是会多一次无用循环
        for (int i = 0; i < a.length-1; i++) {
            //todo 注：min保存下标（即位置），而不是值，方便内层循环完毕后，交换min和i的位置
            int min = i;
            for (int j = i+1; j < a.length; j++) {
                if (less(a[j],a[min]) ) {
                    min = j;
                }
            }
            exchange(a, i, min);
        }
    }
}

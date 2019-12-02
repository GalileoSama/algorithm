package sort;

/**
 * 插入排序
 *
 * @author 王俊杰
 * @date 2019/3/4 10:01
 */
public class InsertSort extends ProtoSort {
    /**
     * 默认正序排序
     * 注意的是，插入排序中a[0]到a[i-1]是有序的，每次将一个a[i]插入到一个有序数组中
     */
    public static void sort(Comparable a[]) {
        //todo 注：i从1开始，因为内层会比较i-1和i
        for (int i = 1; i < a.length; i++) {
            //todo 注：
            // j = i 从i开始从后向前遍历
            // j>0 && less(a[j],a[j-1]  j不能等于0，因为需要j-1与j比较
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exchange(a, j, j - 1);
            }
        }
    }

    /**
     * 加速的插入排序，不交换
     * a[0]到a[i-1]是有序的，每次将一个a[i]插入到一个有序数组中
     * 将a[0]到a[i-1]中，大于a[i]的值向后挪动，空出一个位置插入a[i]
     */
    public static void sortWithoutExchange(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            //todo 不同点：
            //todo 1. temp 保存a[i]
            Comparable temp = a[i];
            int j = i;
            //todo 2. 比较temp和 a[j - 1]
            for (; j > 0 && less(temp, a[j - 1]); j--) {
                //todo 3. 挪动位置。不交换 而是赋值给a[j - 1]
                a[j] = a[j - 1];
            }
            //todo 4. 空出一个位置插入a[i]
            a[j] = temp;
        }
    }

    /**
     * test
     */
    public static void main(String[] arg) {
        Integer[] a = {1, 4, 5, 2, 6, 11, 8};
        sort(a);
        assert isSorted(a);
        show(a);
    }
}

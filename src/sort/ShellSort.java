package sort;

/**
 * @author 王俊杰
 * @date 2019/3/4 15:00
 */
public class ShellSort extends ProtoSort{

    public static void sort(Comparable[] a){
        int N = a.length;
        int h = 1;
        //选择递增序列为3*h+1，从1到N/3
        //即1,4,13,40,121,364,1093...
        while (h < N/3){
            h = 3 * h + 1;
        }
        while (h >= 1){
            //插入排序，使其h有序
            for (int i = h; i < N; i ++){
                for (int j = i; j > h && less(a[j], a[j-h]); j -= h){
                    exchange(a, j, j-h);
                }
            }

            h = h/3;
        }
    }

    public static void main(String[] args){
        Integer[] a = {1, 4, 5, 2, 6, 11, 8};
        sort(a);
        assert isSorted(a);
        show(a);
    }
}

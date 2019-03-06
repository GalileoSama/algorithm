package sort;

/**
 * @author 王俊杰
 * @date 2019/3/6 15:54
 */
public class MergeSort extends ProtoSort{

    private static Comparable[] aux ;

    /** 归并a[low.mid] and a[mid+1,high] */
    private static void merge(Comparable[] a, int low, int mid, int high){
         int i = low;
         int j = mid + 1;

         //复制
        for(int k = low; k <= high; k++){
            aux[k] = a[k];
        }

        //归并 （注!：k<=high的“<=”）
        for (int k = low; k<=high; k++){
            //左半数组消耗完
            if (i > mid){
                a[k] = aux[j++];
            //右半数组消耗完
            }else if (j > high){
                a[k] = aux[i++];
            //左元素 小于 右元素 （注！：aux数组中取 i与j比较 ，而不是 a数组）
            }else if (less(aux[i],aux[j])){
                a[k] = aux[i++];
            //右元素 小于 左元素
            }else {
                a[k] = aux[j++];
            }
        }
    }

    public static void sort(Comparable[] a){
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    /** 自底向上（递归） */
    private static void sort(Comparable[] a, int low, int high){
        if (high <= low) {
            return;
        }
        int mid = (low + high)/2;
        sort(a, low, mid);
        sort(a, mid+1, high);
        merge(a, low, mid, high);
    }

    /** test */
    public static void main(String[] arg){
        Integer[] a = {1, 4, 5, 2, 6, 11, 8};
        sort(a);
        assert isSorted(a);
        show(a);
    }
}

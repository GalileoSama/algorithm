package sort;

import edu.princeton.cs.algs4.*;

/**
 * @author 王俊杰
 * @date 2019/3/4 11:29
 */
public class SortCompare {
    private static double time(String alg, Double[] a) {
        Stopwatch timer = new Stopwatch();
        if (alg.equals("Insertion")) {
            InsertSort.sort(a);
        }
        if (alg.equals("Selection")) {
            Selection.sort(a);
        }
        if (alg.equals("Shell")) {
            ShellSort.sort(a);
        }
        if (alg.equals("Merge")) {
            Merge.sort(a);
        }
        if (alg.equals("Quick")) {
            Quick.sort(a);
        }
        if (alg.equals("Heap")) {
            Heap.sort(a);
        }
        // Exercise 2.1.24
//        if (alg.equals("Ex24")) {
//            Ex24.sort(a);
//        }
        // Exercise 2.1.25
//        if (alg.equals("Ex25")) {
//            Ex25.sort(a);
//        }
        return timer.elapsedTime();
    }

    private static double timeRandomInput(String alg, int N, int T) {
        double total = 0.0;
        Double[] a = new Double[N];
        for (int t = 0; t < T; t++) {
            for (int i = 0; i < N; i++) {
                a[i] = StdRandom.uniform();
            }
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args) {
        String alg1 = "Shell";
        String alg2 = "Insertion";
        int N = 1024 * 64;
        int T = 10;
        double t1 = timeRandomInput(alg1, N, T);
        double t2 = timeRandomInput(alg2, N, T);
        StdOut.printf("For %d random Doubles\n  %s is", N, alg1);
        StdOut.printf(" %.1f times faster than %s\n", t2 / t1, alg2);
    }
}

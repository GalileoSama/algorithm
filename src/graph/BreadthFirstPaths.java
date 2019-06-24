package graph;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

/**
 * @author galileo
 * @date 2019/6/21 10:33
 */
public class BreadthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;
    private int s;

    public BreadthFirstPaths(Graph graph, int s) {
        marked = new boolean[graph.V()];
        edgeTo = new int[graph.V()];
        distTo = new int[graph.V()];
        for (int v = 0; v < graph.V(); v++) {
            distTo[v] = Integer.MAX_VALUE;
        }
        this.s = s;
        bfs(graph, s);
    }

    public void bfs(Graph graph, int s){
        Queue<Integer> queue = new Queue<>();
        distTo[s] = 0;
        marked[s] = true;
        queue.enqueue(s);
        while (!queue.isEmpty()){
            int v = queue.dequeue();
            for (int w : graph.adj(v)){
                if (!hasPathTo(w)){
                    marked[w] = true;
                    distTo[w] = distTo[v] + 1;
                    queue.enqueue(w);
                    edgeTo[w] = v;
                }
            }
        }
    }

    public Iterable<Integer> pathTo(int v){
        if (!hasPathTo(v)){
            return null;
        }
        Stack<Integer> path = new Stack<>();
        for (int x = v;x != s; x = edgeTo[x]){
            path.push(x);
        }
        path.push(s);
        return path;
    }

    /**距离**/
    public int distTo(int v){
        if (!hasPathTo(v)){
            return 0;
        }
        return distTo[v];
    }

    public boolean hasPathTo(int w){
        return marked[w];
    }
}

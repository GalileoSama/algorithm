package graph;

import edu.princeton.cs.algs4.Stack;

/**
 * @author galileo
 * @date 2019/6/21 9:42
 */
public class DepthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private int count;
    private int s;

    public DepthFirstPaths(Graph graph, int s) {
        marked = new boolean[graph.V()];
        edgeTo = new int[graph.V()];
        this.s = s;
        dfs(graph, s);
    }

    public void dfs(Graph graph, int s){
        marked[s] = true;
        count++;

        for (int v : graph.adj(s)){
            if (!marked[v]){
                edgeTo[v] = s;
                dfs(graph, v);
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

    public boolean hasPathTo(int w){
        return marked[w];
    }

    public int count(){
        return count;
    }
}

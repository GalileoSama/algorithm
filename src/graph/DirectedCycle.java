package graph;

import edu.princeton.cs.algs4.Stack;

/**
 * @author galileo
 * @date 2019/6/26 11:25
 */
public class DirectedCycle {
    private boolean[] marked;
    private boolean[] onStack;
    private int[] edgeTo;
    private Stack<Integer> cycle;

    public DirectedCycle(Digraph digraph) {
        marked = new boolean[digraph.V()];
        onStack = new boolean[digraph.V()];
        edgeTo = new int[digraph.V()];

        for (int s = 0;s<digraph.V();s++){
            if (!marked[s]){
                dfs(digraph, s);
            }
        }
    }

    private void dfs(Digraph digraph, int v){
        marked[v] = true;
        onStack[v] = true;

        for (int w : digraph.adj(v)){
            if (hasCycle()){
                return;
            } else if (!marked[w]){
                edgeTo[v] = w;
                dfs(digraph, w);
            }else if (onStack[w]){
                cycle = new Stack<>();
                //cycle start from v, end in w
                for (int x = v; x != w; x = edgeTo[x]){
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle(){
        return cycle != null;
    }

    public Iterable<Integer> cycle(){
        return cycle;
    }
}

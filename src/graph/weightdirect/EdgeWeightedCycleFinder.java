package graph.weightdirect;

import edu.princeton.cs.algs4.Stack;

/**
 * @author galileo
 * @date 2019/7/5 10:20
 */
public class EdgeWeightedCycleFinder {
    private boolean[] marked;
    private boolean[] onStack;
    private DirectedEdge[] edgeTo;
    private Stack<DirectedEdge> cycle;

    public EdgeWeightedCycleFinder(EdgeWeightedDigraph digraph) {
        marked = new boolean[digraph.getV()];
        onStack = new boolean[digraph.getV()];
        edgeTo = new DirectedEdge[digraph.getV()];

        for (int s = 0;s<digraph.getV();s++){
            if (!marked[s]){
                dfs(digraph, s);
            }
        }
    }

    private void dfs(EdgeWeightedDigraph digraph, int v){
        marked[v] = true;
        onStack[v] = true;

        for (DirectedEdge edge : digraph.adj(v)){
            int w = edge.to();
            if (hasCycle()){
                return;
            } else if (!marked[w]){
                edgeTo[v] = edge;
                dfs(digraph, w);
            }else if (onStack[w]){
                cycle = new Stack<>();
                //cycle start from f.from, end in w
                DirectedEdge f = edge;
                while (f.from() != w){
                    cycle.push(f);
                    f = edgeTo[f.from()];
                }
                //此时f == w
                cycle.push(f);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle(){
        return cycle != null;
    }

    public Iterable<DirectedEdge> cycle(){
        return cycle;
    }
}

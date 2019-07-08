package graph.weight_direct;

import edu.princeton.cs.algs4.Queue;

/**
 * @author galileo
 * @date 2019/7/8 15:11
 */
public class BellmanFordSp {
    private Queue<Integer> queue;
    private boolean[] onQ;
    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private int count;
    private Iterable<DirectedEdge> cycle;

    public BellmanFordSp(EdgeWeightedDigraph digraph, int s){
        queue = new Queue<>();
        edgeTo = new DirectedEdge[digraph.getV()];
        distTo = new double[digraph.getV()];
        onQ = new boolean[digraph.getV()];

        //初始化距离为无穷大，方便比较
        for (int i = 0;i<digraph.getV();i++){
            distTo[i] = Double.POSITIVE_INFINITY;
        }

        distTo[s] = 0;
        queue.enqueue(s);
        while (!queue.isEmpty() && !hasNegativeCycle()){
            int v = queue.dequeue();
            relax(digraph, v);
        }
    }

    private void relax(EdgeWeightedDigraph digraph, int v){
        for (DirectedEdge e : digraph.adj(v)){
            int w = e.to();
            if (distTo[w] > e.weight() + distTo[v]){
                distTo[w] = e.weight() + distTo[v];
                edgeTo[w] = e;

                if (!onQ[w]){
                    queue.enqueue(w);
                    onQ[w] = true;
                }
            }
        }

        if (count++ % digraph.getV() == 0){
            findNegativeCycle();
        }
    }

    private void findNegativeCycle(){
        int v = edgeTo.length;
        EdgeWeightedDigraph digraph = new EdgeWeightedDigraph(v);

        for (DirectedEdge edge : edgeTo) {
            if (edge != null) {
                digraph.addEdge(edge);
            }
        }

        EdgeWeightedCycleFinder cycleFinder = new EdgeWeightedCycleFinder(digraph);
        cycle = cycleFinder.cycle();
    }

    private boolean hasNegativeCycle(){
        return cycle != null;
    }

    private Iterable<DirectedEdge> negativeCycle(){
        return cycle;
    }
}

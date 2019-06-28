package graph.weight;

import edu.princeton.cs.algs4.Bag;

/**
 * @author galileo
 * @date 2019/6/28 14:51
 */
public class EdgeWeightedGraph {
    private int e;
    private int v;
    private Bag<Edge>[] adj;

    public EdgeWeightedGraph(int v) {
        //初始化
        this.v = v;
        this.e = 0;
        adj = (Bag<Edge>[])new Bag[v];
        for (int i=0;i<v;i++){
            adj[i] = new Bag<>();
        }
    }

    public int getE() {
        return e;
    }

    public int getV() {
        return v;
    }

    public void addEdge(Edge edge){
        adj[edge.either()].add(edge);
        adj[edge.other(edge.either())].add(edge);
        e++;
    }

    public Iterable<Edge> edges(){
        Bag<Edge> b = new Bag<>();
        for (int i=0;i<v;i++){
            for (Edge e : adj[i]){
                if (e.other(i) > i){
                    b.add(e);
                }
            }
        }
        return b;
    }

    public Iterable<Edge> adj(int v){
        return adj[v];
    }
}

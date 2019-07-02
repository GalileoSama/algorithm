package graph.weight_direct;

import edu.princeton.cs.algs4.Bag;

import java.util.Arrays;

/**
 * @author galileo
 * @date 2019/7/2 11:16
 */
public class EdgeWeightedDigraph {
    private Bag<DirectedEdge>[] adj;
    private final int v;
    private int e;

    public EdgeWeightedDigraph(int v) {
        this.v = v;
        this.e = 0;
        adj = (Bag<DirectedEdge>[]) new Bag[v];

        for (int i = 0;i < v;i++){
            adj[i] = new Bag<>();
        }
    }

    public int getV() {
        return v;
    }

    public int getE() {
        return e;
    }

    public void addEdge(DirectedEdge edge){
        adj[edge.from()].add(edge);
        e++;
    }

    public Iterable<DirectedEdge> adj(int v){
        return adj[v];
    }

    public Iterable<DirectedEdge> edges(){
        Bag<DirectedEdge> edges = new Bag<>();
        for (int i = 0;i < v;i++){
            for (DirectedEdge edge : adj[i]){
                edges.add(edge);
            }
        }
        return edges;
    }

    @Override
    public String toString() {
        return "EdgeWeightedDigraph{" +
                "adj=" + Arrays.toString(adj) +
                ", v=" + v +
                ", e=" + e +
                '}';
    }
}

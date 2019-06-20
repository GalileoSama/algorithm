package graph;

import edu.princeton.cs.algs4.Bag;

/**
 * 图的邻接表表示
 * @author galileo
 * @date 2019/6/20 15:52
 */
public class Graph {
    private int e;
    private int v;
    private Bag<Integer>[] adj;

    public Graph(int v) {
        this.v = v;
        this.e = 0;
        adj = (Bag<Integer>[]) new Bag[v];
        for (int i = 0;i<v;i++) {
            adj[i] = new Bag<>();
        }
    }

    private int V(){
        return v;
    }

    private int E(){
        return e;
    }

    private void addEdge(int w, int v){
        adj[w].add(v);
        adj[v].add(w);
        e++;
    }

    private Iterable<Integer> adj(int v){
        return adj[v];
    }
}

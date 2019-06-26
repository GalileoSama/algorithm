package graph;

import edu.princeton.cs.algs4.Bag;

/**
 * @author galileo
 * @date 2019/6/25 10:21
 */
public class Digraph {
    private int v;
    private int e;
    private Bag<Integer>[] adj;

    public Digraph(int v) {
        this.v = v;
        adj = (Bag<Integer>[]) new Bag[v];
        for (int i = 0;i<v;i++){
            adj[i] = new Bag<>();
        }
    }

    public int V() {
        return v;
    }

    public int E() {
        return e;
    }

    public Iterable<Integer> adj(int v){
        return adj[v];
    }

    public void addEdge(int v, int w){
        adj[v].add(w);
        e++;
    }

    public Digraph reverse(){
        Digraph r = new Digraph(v);
        for (int i = 0;i<v;i++){
            for (int w : adj[i]){
                r.addEdge(w, i);
            }
        }
        return r;
    }
}

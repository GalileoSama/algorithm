package graph.normal;

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

    public int V(){
        return v;
    }

    public int E(){
        return e;
    }

    public void addEdge(int w, int v){
        adj[w].add(v);
        adj[v].add(w);
        e++;
    }

    public Iterable<Integer> adj(int v){
        return adj[v];
    }

    public static void main(String[] args) {
        Graph graph = new Graph(12);
        graph.addEdge(8,4);
        graph.addEdge(2,3);
        graph.addEdge(1,11);
        graph.addEdge(0,6);
        graph.addEdge(3,6);
        graph.addEdge(10,3);
        graph.addEdge(7,11);
        graph.addEdge(7,8);
        graph.addEdge(11,8);
        graph.addEdge(2,0);
        graph.addEdge(6,2);
        graph.addEdge(5,2);
        graph.addEdge(5,10);
        graph.addEdge(5,0);
        graph.addEdge(8,1);
        graph.addEdge(4,1);

        for (int v : graph.adj(0)){
            System.out.println(v);
        }
    }
}

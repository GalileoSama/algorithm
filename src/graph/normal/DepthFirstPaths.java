package graph.normal;

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

        DepthFirstPaths depthFirstPaths = new DepthFirstPaths(graph, 0);

        System.out.println("count:"+depthFirstPaths.count());
        System.out.println("7 path?:"+depthFirstPaths.pathTo(7));
        System.out.println("10 path?:"+depthFirstPaths.pathTo(3));
    }
}

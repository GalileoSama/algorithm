package graph.normal;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

/**
 * @author galileo
 * @date 2019/6/21 10:33
 */
public class BreadthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;
    private int s;

    public BreadthFirstPaths(Graph graph, int s) {
        marked = new boolean[graph.V()];
        edgeTo = new int[graph.V()];
        distTo = new int[graph.V()];
        for (int v = 0; v < graph.V(); v++) {
            distTo[v] = Integer.MAX_VALUE;
        }
        this.s = s;
        bfs(graph, s);
    }

    public void bfs(Graph graph, int s){
        Queue<Integer> queue = new Queue<>();
        distTo[s] = 0;
        marked[s] = true;
        queue.enqueue(s);
        while (!queue.isEmpty()){
            int v = queue.dequeue();
            for (int w : graph.adj(v)){
                if (!hasPathTo(w)){
                    marked[w] = true;
                    distTo[w] = distTo[v] + 1;
                    queue.enqueue(w);
                    edgeTo[w] = v;
                }
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

    /**距离**/
    public int distTo(int v){
        if (!hasPathTo(v)){
            return 0;
        }
        return distTo[v];
    }

    public boolean hasPathTo(int w){
        return marked[w];
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

        BreadthFirstPaths breadthFirstPaths = new BreadthFirstPaths(graph, 0);

        System.out.println("7 path?:"+breadthFirstPaths.pathTo(7));
        System.out.println("dist to 10:"+breadthFirstPaths.distTo(3));
        System.out.println("10 path?:"+breadthFirstPaths.pathTo(3));
    }
}

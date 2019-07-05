package graph.direct;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import graph.weight_direct.DirectedEdge;
import graph.weight_direct.EdgeWeightedDigraph;

/**
 * @author galileo
 * @date 2019/6/26 16:12
 */
public class DepthFirstOrder {
    private boolean[] marked;
    private Queue<Integer> pre;
    private Queue<Integer> post;
    private Stack<Integer> reversePost;

    public DepthFirstOrder(Digraph digraph) {
        marked = new boolean[digraph.V()];
        pre = new Queue<>();
        post = new Queue<>();
        reversePost = new Stack<>();
        for (int s = 0; s<digraph.V(); s++){
            if (!marked[s]){
                dfs(digraph, s);
            }
        }
    }

    public void dfs(Digraph digraph, int v){
        marked[v] = true;
        pre.enqueue(v);

        for (int w : digraph.adj(v)){
            if (!marked[w]){
                dfs(digraph, w);
            }
        }

        post.enqueue(v);
        reversePost.push(v);
    }

    public DepthFirstOrder(EdgeWeightedDigraph digraph) {
        marked = new boolean[digraph.getV()];
        pre = new Queue<>();
        post = new Queue<>();
        reversePost = new Stack<>();
        for (int s = 0; s<digraph.getV(); s++){
            if (!marked[s]){
                dfs(digraph, s);
            }
        }
    }

    public void dfs(EdgeWeightedDigraph digraph, int v){
        marked[v] = true;
        pre.enqueue(v);

        for (DirectedEdge edge : digraph.adj(v)){
            int w = edge.to();
            if (!marked[w]){
                dfs(digraph, w);
            }
        }

        post.enqueue(v);
        reversePost.push(v);
    }

    public Queue<Integer> getPre() {
        return pre;
    }

    public Queue<Integer> getPost() {
        return post;
    }

    public Stack<Integer> getReversePost() {
        return reversePost;
    }

    public static void main(String[] args) {
        Digraph digraph = new Digraph(13);
        digraph.addEdge(4,2);
        digraph.addEdge(2,3);
        digraph.addEdge(3,2);
        digraph.addEdge(6,0);
        digraph.addEdge(0,1);
        digraph.addEdge(2,0);
        digraph.addEdge(11,12);
        digraph.addEdge(12,9);
        digraph.addEdge(9,10);
        digraph.addEdge(9,11);
        digraph.addEdge(8,9);
        digraph.addEdge(10,12);
        digraph.addEdge(11,4);
        digraph.addEdge(4,3);
        digraph.addEdge(3,5);
        digraph.addEdge(7,8);
        digraph.addEdge(8,7);
        digraph.addEdge(5,4);
        digraph.addEdge(0,5);
        digraph.addEdge(6,4);
        digraph.addEdge(6,9);
        digraph.addEdge(7,6);

        DepthFirstOrder depthFirstOrder = new DepthFirstOrder(digraph);
        for (int i : depthFirstOrder.getPost()){
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : depthFirstOrder.getReversePost()){
            System.out.print(i + " ");
        }
        System.out.println();
        DepthFirstOrder depthFirstOrder1 = new DepthFirstOrder(digraph.reverse());
        for (int i : depthFirstOrder1.getReversePost()){
            System.out.print(i + " ");
        }
    }
}

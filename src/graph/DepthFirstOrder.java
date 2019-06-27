package graph;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

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

    public Queue<Integer> getPre() {
        return pre;
    }

    public Queue<Integer> getPost() {
        return post;
    }

    public Stack<Integer> getReversePost() {
        return reversePost;
    }
}

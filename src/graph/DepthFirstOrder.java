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

    public void dfs(Digraph digraph, int s){
        marked[s] = true;
        pre.enqueue(s);

        for (int v : digraph.adj(s)){
            if (!marked[v]){
                dfs(digraph, v);
            }
        }

        post.enqueue(s);
        reversePost.push(s);
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

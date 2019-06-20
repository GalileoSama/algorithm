package graph;

/**
 * @author galileo
 * @date 2019/6/20 17:12
 */
public class DeepFirstSearch {
    private boolean[] marked;
    private int count;

    public DeepFirstSearch(Graph graph, int s) {
        marked = new boolean[graph.V()];

    }

    public void dfs(Graph graph, int s){
        marked[s] = true;
        count++;

        for (int v : graph.adj(s)){
            if (!marked[v]){
                dfs(graph, v);
            }
        }
    }

    public boolean marked(int w){
        return marked[w];
    }

    public int count(){
        return count;
    }
}

package graph;

/**
 * 环图判断
 * @author galileo
 * @date 2019/6/24 15:31
 */
public class Cycle {
    private boolean[] marked;
    private boolean hasCycle;

    public Cycle(Graph graph) {
        marked = new boolean[graph.V()];

        for (int i = 0; i < graph.V(); i++){
            if (!marked[i]){
                dfs(graph, i, i);
            }
        }
    }

    private void dfs(Graph graph, int v, int u){
        marked[v] = true;

        for (int w : graph.adj(v)){
            if (!marked[w]){
                dfs(graph, w, v);
            }else if (w != u){
                hasCycle = true;
            }
        }
    }

    public boolean isHasCycle(){
        return hasCycle;
    }
}

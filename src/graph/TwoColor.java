package graph;

/**
 * 二分图判断
 * @author galileo
 * @date 2019/6/24 15:31
 */
public class TwoColor {
    private boolean[] marked;
    private boolean[] color;
    private boolean isTwoColored = true;

    public TwoColor(Graph graph) {
        marked = new boolean[graph.V()];
        color = new boolean[graph.V()];

        for (int s = 0;s<graph.V();s++){
            if (!marked[s]){
                dfs(graph, s);
            }
        }
    }

    private void dfs(Graph graph, int v){
        marked[v] = true;

        for (int w : graph.adj(v)){
            if (!marked[w]){
                color[w] = !color[v];
                dfs(graph, w);
            }else if (color[w] == color[v]){
                isTwoColored = false;
            }
        }
    }

    public boolean isTwoColored(){
        return isTwoColored;
    }
}

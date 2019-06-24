package graph;

/**
 * @author galileo
 * @date 2019/6/24 9:28
 */
public class CC {
    private int[] id;
    private boolean[] marked;
    private int count;

    public CC(Graph graph) {
        count = 0;
        id = new int[graph.V()];
        marked = new boolean[graph.V()];

        for (int i = 0;i<graph.V();i++){
            if (!marked[i]){
                dfs(graph, i);
                count++;
            }
        }
    }

    private void dfs(Graph graph, int s){
        marked[s] = true;
        id[s] = count;
        for (int v : graph.adj(s)){
            if (!marked[v]){
                dfs(graph, v);
            }
        }
    }

    public boolean connected(int v, int w){
        return id[v] == id[w];
    }

    public int count(){
        return count;
    }

}

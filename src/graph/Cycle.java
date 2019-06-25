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

        Cycle cycle = new Cycle(graph);

        System.out.println("isHasCycle?:"+cycle.isHasCycle());
    }
}

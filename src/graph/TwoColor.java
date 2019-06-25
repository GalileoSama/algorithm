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

        TwoColor twoColor = new TwoColor(graph);

        System.out.println("isTwoColored?:"+twoColor.isTwoColored());
    }
}

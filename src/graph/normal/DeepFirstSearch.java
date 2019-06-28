package graph.normal;

/**
 * @author galileo
 * @date 2019/6/20 17:12
 */
public class DeepFirstSearch {
    private boolean[] marked;
    private int count;

    public DeepFirstSearch(Graph graph, int s) {
        marked = new boolean[graph.V()];
        dfs(graph, s);
    }

    private void dfs(Graph graph, int s){
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

        DeepFirstSearch deepFirstSearch = new DeepFirstSearch(graph, 0);

        System.out.println("count:"+deepFirstSearch.count());
        System.out.println("7?:"+deepFirstSearch.marked(7));
        System.out.println("10?:"+deepFirstSearch.marked(10));

    }
}

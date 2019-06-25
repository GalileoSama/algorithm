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

        CC cc = new CC(graph);

        System.out.println("count:"+cc.count());
        System.out.println("3&5:"+cc.connected(3, 5));
        System.out.println("9&3:"+cc.connected(9,3));
    }

}

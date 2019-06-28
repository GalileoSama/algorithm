package graph;

/**
 * @author galileo
 * @date 2019/6/28 10:45
 */
public class kosarajuSCC {
    private int[] id;
    private boolean[] marked;
    private int count;

    public kosarajuSCC(Digraph digraph){
        id = new int[digraph.V()];
        marked = new boolean[digraph.V()];
        count = 0;

        //传入Gr，而非G
        DepthFirstOrder order = new DepthFirstOrder(digraph.reverse());
        //以逆后续顺序搜索
        for (int s : order.getReversePost()){
            if (!marked[s]){
                dfs(digraph, s);
                count++;
            }
        }

    }

    private void dfs(Digraph digraph, int v){
        marked[v] = true;
        id[v] = count;

        for (int w : digraph.adj(v)){
            if (!marked[w]){
                dfs(digraph, w);
            }
        }
    }

    public boolean stronglyConnected(int w, int v){
        return id[w] == id[v];
    }

    public int id(int v){
        return id[v];
    }

    public int getCount(){
        return count;
    }
}

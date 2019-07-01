package graph.weight;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

/**
 * @author galileo
 * @date 2019/7/1 9:46
 */
public class LazyPrimeMst {
    private boolean[] marked;
    private Queue<Edge> mst;
    private MinPQ<Edge> pq;

    public LazyPrimeMst(EdgeWeightedGraph graph) {
        marked = new boolean[graph.getV()];
        mst = new Queue<>();
        pq = new MinPQ<>();

        visit(graph, 0);
        while (!pq.isEmpty()){
            Edge edge = pq.delMin();
            int v = edge.either();
            int w = edge.other(v);
            //此为延迟，即不管那么多先全添加到优先队列中，到这里才开始检查是否失效（下面的if语句）
            if (marked[v] && marked[w]) {
                continue;
            }
            //添加到最小生成树种
            mst.enqueue(edge);
            if (!marked[v]){
                visit(graph, v);
            }
            if (!marked[w]){
                visit(graph, w);
            }
        }
    }

    private void visit(EdgeWeightedGraph graph, int v){
        marked[v] = true;

        for (Edge edge : graph.adj(v)){
            //边未失效
            if (!marked[edge.other(v)]){
                pq.insert(edge);
            }
        }
    }

    public Iterable<Edge> edges(){
        return mst;
    }
}

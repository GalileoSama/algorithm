package graph.weight;

import edu.princeton.cs.algs4.IndexMinPQ;

/**
 * @author galileo
 * @date 2019/7/1 11:34
 */
public class PrimMst {
    private boolean[] marked;
    /**优先队列 节点和权重**/
    private IndexMinPQ<Double> pq;
    /**关联 节点和边**/
    private Edge[] edgeTo;
    /**关联 节点和权重**/
    private double[] distTo;

    public PrimMst(EdgeWeightedGraph graph){
        //初始化
        marked = new boolean[graph.getV()];
        pq = new IndexMinPQ<>(graph.getV());
        edgeTo = new Edge[graph.getV()];
        distTo = new double[graph.getV()];

        //初始化距离为无穷大，方便比较
        for (int i = 0;i<graph.getV();i++){
            distTo[i] = Double.POSITIVE_INFINITY;
        }

        distTo[0] = 0.0;
        pq.insert(0, 0.0);
        while (!pq.isEmpty()){
            //取出优先队列中最小的节点 加入到生成树中
            int v = pq.delMin();
            visit(graph, v);
        }
    }

    private void visit(EdgeWeightedGraph graph, int v){
        marked[v] = true;

        //寻找与节点v相连的边中 权重小与当前pq的一个边 插入或更新pq
        for (Edge e : graph.adj(v)){
            int w = e.other(v);
            //v-w失效
            if (marked[w]){
                continue;
            }
            //找到树到w的权重更小的边
            if (e.getWeight() < distTo[w]){
                edgeTo[w] = e;
                distTo[w] = e.getWeight();
                if (pq.contains(w)){
                    pq.changeKey(w, e.getWeight());
                }else {
                    pq.insert(w, e.getWeight());
                }
            }
        }
    }

}

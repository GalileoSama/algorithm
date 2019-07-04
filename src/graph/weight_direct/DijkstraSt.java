package graph.weight_direct;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;

/**
 * @author galileo
 * @date 2019/7/3 19:48
 */
public class DijkstraSt {
    /**优先队列 节点和权重**/
    private IndexMinPQ<Double> pq;
    /**关联 节点和边**/
    private DirectedEdge[] edgeTo;
    /**关联 节点和权重**/
    private double[] distTo;

    public DijkstraSt(EdgeWeightedDigraph digraph, int s) {
        pq = new IndexMinPQ<>(digraph.getV());
        edgeTo = new DirectedEdge[digraph.getV()];
        distTo = new double[digraph.getV()];

        //初始化距离为无穷大，方便比较
        for (int i = 0;i<digraph.getV();i++){
            distTo[i] = Double.POSITIVE_INFINITY;
        }

        distTo[s] = 0;
        pq.insert(s, 0.0);
        while (!pq.isEmpty()){
            //取出优先队列中最小的节点(起点s到w的权重最小) 加入到生成树中
            relax(digraph, pq.delMin());
        }
    }

    private void relax(EdgeWeightedDigraph digraph, int v){
        for (DirectedEdge edge : digraph.adj(v)){
            int w = edge.to();
            //该edge有效，松弛？
            if (distTo[w] > distTo[v] + edge.weight()){
                distTo[w] = distTo[v] + edge.weight();
                edgeTo[w] = edge;
                if (pq.contains(w)){
                    pq.changeKey(w, distTo[w]);
                }else {
                    pq.insert(w, distTo[w]);
                }
            }
        }
    }

    public double distTo(int v){
        return distTo[v];
    }

    public boolean hasPathTo(int v){
        return distTo[v]<Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v){
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge x = edgeTo[v];x!=null;x=edgeTo[x.from()]){
            path.push(x);
        }
        return path;
    }
}

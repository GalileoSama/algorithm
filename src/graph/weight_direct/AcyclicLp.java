package graph.weight_direct;

import edu.princeton.cs.algs4.Stack;
import graph.direct.Topological;

/**
 * 最长路径
 * @author galileo
 * @date 2019/7/4 11:26
 */
public class AcyclicLp {
    /**关联 节点和边**/
    private DirectedEdge[] edgeTo;
    /**关联 节点和权重**/
    private double[] distTo;

    public AcyclicLp(EdgeWeightedDigraph digraph, int s){
        edgeTo = new DirectedEdge[digraph.getV()];
        distTo = new double[digraph.getV()];

        //初始化距离为无穷大，方便比较
        for (int i = 0;i<digraph.getV();i++){
            distTo[i] = Double.NEGATIVE_INFINITY;
        }

        distTo[s] = 0;
        Topological topological = new Topological(digraph);
        for (int v:topological.getOrder()){
            relax(digraph, v);
        }
    }

    private void relax(EdgeWeightedDigraph digraph, int v){
        for (DirectedEdge edge : digraph.adj(v)){
            int w = edge.to();
            //该edge有效，松弛？
            if (distTo[w] < distTo[v] + edge.weight()){
                distTo[w] = distTo[v] + edge.weight();
                edgeTo[w] = edge;
            }
        }
    }

    public double distTo(int v){
        return distTo[v];
    }

    public boolean hasPathTo(int v){
        return distTo[v]>Double.NEGATIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v){
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge x = edgeTo[v];x!=null;x=edgeTo[x.from()]){
            path.push(x);
        }
        return path;
    }
}

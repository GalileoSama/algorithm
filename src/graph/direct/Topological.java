package graph.direct;

/**
 * @author galileo
 * @date 2019/6/26 16:19
 */
public class Topological {
    /** 顶点拓扑顺序 **/
    private Iterable<Integer> order;

    public Topological(Digraph digraph) {
        //检测是否是有向无环图
        DirectedCycle directedCycle = new DirectedCycle(digraph);
        if (!directedCycle.hasCycle()){
            DepthFirstOrder depthFirstOrder = new DepthFirstOrder(digraph);
            order = depthFirstOrder.getReversePost();
        }
    }

    public Iterable<Integer> getOrder() {
        return order;
    }

    /**是否是有向无环图**/
    public boolean isDAG(){
        return order != null;
    }
}

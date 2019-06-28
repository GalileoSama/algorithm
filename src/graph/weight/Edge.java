package graph.weight;

/**
 * @author galileo
 * @date 2019/6/28 14:39
 */
public class Edge implements Comparable<Edge>{
    private int v;
    private int w;
    private double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public int either(){
        return v;
    }

    public int other(int v){
        if (v == this.v){
            return w;
        }else if(v == this.w){
            return v;
        }else {
            throw new RuntimeException("没有与v相连的节点");
        }
    }

    @Override
    public int compareTo(Edge that) {
        return Double.compare(this.weight, that.weight);
    }

    @Override
    public String toString(){
        return String.format("%d-%d %.2f",v, w, weight);

    }
}

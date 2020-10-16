package graphs;

public class SP {

    private final EdgeWeightDigraph G;
    private final int s;

    public SP(EdgeWeightDigraph g, int s) {
        G = g;
        this.s = s;
    }

    public double distTo(int v){
        return 0;
    }

    Iterable<DirectedEdge> pathTo(int v){
        return null;
    }
}

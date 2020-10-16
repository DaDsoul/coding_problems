package graphs;

import java.util.ArrayList;
import java.util.List;

public class EdgeWeightDigraph {

    private final int V;
    private final List<DirectedEdge>[] adj;


    public EdgeWeightDigraph(int V, List<DirectedEdge>[] adj) {
        this.V = V;
        this.adj = new List[V];
        for (int v = 0; v < V; v++){
            adj[v] = new ArrayList();
        }
    }

    public void addEdge(DirectedEdge e){
        int v = e.from();
        adj[v].add(e);
    }

    Iterable<DirectedEdge> adj(int v){
        return adj[v];
    }

    public int V(){
        return this.V;
    }
}

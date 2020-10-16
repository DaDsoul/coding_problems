package graphs;

import java.util.*;

public class DijkstraSP {

    public static class Pair{
        int index;
        double distance;

        public Pair(int index, double distance) {
            this.index = index;
            this.distance = distance;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return index == pair.index &&
                    distance == pair.distance;
        }

        @Override
        public int hashCode() {
            return Objects.hash(index, distance);
        }
    }

    private DirectedEdge[] edgeTo;
    private double[] distTo;
    private PriorityQueue<Pair> pq;
    private Set<Integer> set;

    public DijkstraSP(EdgeWeightDigraph G, int s){
        this.edgeTo = new DirectedEdge[G.V()];
        this.distTo = new double[G.V()];
        this.set = new HashSet();
        this.pq = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return Double.compare(o1.distance, o2.distance);
            }
        });

        for (int v = 0; v < G.V(); v++){
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;
        pq.add(new Pair(s, 0.0));
        this.set.add(s);
        while (!pq.isEmpty()) {
            Pair v = pq.remove();
            for (DirectedEdge e: G.adj(v.index)) {
                relax(e);
            }
        }
    }

    private void relax(DirectedEdge e){
        int v = e.from();
        int w = e.to();
        double oldValueOfW = distTo[w];
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] = e.weight();
            edgeTo[w] = e;
            if (set.contains(w)) {
                pq.remove(new Pair(w, oldValueOfW));
            }
            pq.add(new Pair(w, distTo[w]));
        }
    }








}

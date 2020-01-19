class Solution {


    /*
        Computes the time that it will take for all 
        nodes to receive the signal from a source node
        @param K the sourceNode
        @param times the array of edges with start and end nodes
        @param N the total number of nodes
        @return the overall time for signal comming to all nodes from source
    */    
    public int networkDelayTime(int[][] times, int N, int K) {
        

        // The idea is in usage of Dijkstra alogrithm
        
        // graph representation
        Map<Integer, List<int[]>> graph = preprocess(times);
        
        PriorityQueue<int[]> minDist = new PriorityQueue(new Comparator<int[]>(){
            
            // assume, the first value is the node
            // the second value is the distance from source 
            // to that particular node
            public int compare(int[] one, int[] two){
                return Integer.compare(one[1], two[1]);
            }
        });
        
        // map from node to the final distance to source
        int[] distToSource = new int[N+1];
        
        for(int i = 1; i<N+1; i++){
            if (i == K){
                distToSource[i] = 0;
            }else{
                distToSource[i] = Integer.MAX_VALUE;
            }
        }
        
        // nodes whose final distance to source is finalized
        Set<Integer> finalNodes = new HashSet();
        
        // the source node's distance is zero
        minDist.add(new int[]{K, 0});
            
        // stop when all nodes would be finalized
        while(minDist.size() != 0){
            
            int[] nodePair = minDist.remove();
            int node = nodePair[0];
            int dist = nodePair[1];
            
            finalNodes.add(node);
            
            List<int[]> neighbors = graph.getOrDefault(node, new ArrayList());
            System.out.println(node + " BEFORE EXCEPTION");
            for(int[] neighbor: neighbors){
                int neighborNode = neighbor[0];
                int distToNeighbor = neighbor[1];
                int distFromSourceToNeighbor = distToSource[neighborNode];
                
                if (!finalNodes.contains(neighborNode)){
                        if (distToNeighbor + dist < distFromSourceToNeighbor){
                            distToSource[neighborNode] = distToNeighbor + dist;
                            minDist.add(new int[]{neighborNode, distToSource[neighborNode]});
                        }
                }
            }
        }
        
        
        
        int maxDist = Integer.MIN_VALUE;
        
        for(int dist: distToSource){
            maxDist = Math.max(maxDist, dist);
        }
        
        
        return maxDist == Integer.MAX_VALUE ? -1 : maxDist;
    }
    
    public void relax(){
        
    }
    
    
    
    public Map<Integer, List<int[]>> preprocess(int[][] times){
        
        Map<Integer, List<int[]>> resultGraph = new HashMap();
        
        
        for(int[] time: times){
            int start = time[0];
            int end = time[1];
            int weight = time[2];
            
            resultGraph.computeIfAbsent(start, k -> new ArrayList()).add(new int[]{end, weight});
        }
        
        return resultGraph;
    }
    
}
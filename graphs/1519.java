class Solution {
    public int[] countSubTrees(int n, int[][] edges, String labels) {
                
        Map<Integer, List<Integer>> graph = new HashMap(); 
        
        for(int i = 0; i<edges.length; i++){
            int parent  = edges[i][0];
            int child   = edges[i][1];
            
            // undirected graph
            graph.computeIfAbsent(child, k -> new ArrayList()).add(parent);
            graph.computeIfAbsent(parent, k -> new ArrayList()).add(child);
        }
        
        int[] result = new int[n];
        Set<Integer> visited = new HashSet();
        int root = 0; 
        dfs(graph, visited, root, labels, result);
        return result;
    }
    
    public int[] dfs(Map<Integer, List<Integer>> graph, Set<Integer> visited, int node, String labels, int[] result){
        // using the array of all possible chars since, every node has to remember what chars it has on its subtree 
        int[] charCounts = new int[26];
        char label = labels.charAt(node);
        
        if (!visited.contains(node)){
            visited.add(node);
            List<Integer> nodesWithDirectConnection = graph.getOrDefault(node, new ArrayList());
            for(Integer directNode: nodesWithDirectConnection){
                // backtracking till the leaves (bottom nodes)
                int[] directNodeCount = dfs(graph, visited, directNode, labels, result);
                for(int i = 0; i<charCounts.length; i++){
                    charCounts[i] += directNodeCount[i];
                }
            }
            charCounts[label - 'a'] += 1; 
            result[node] = charCounts[label - 'a'];   
        }
        
        return charCounts;
    }
}
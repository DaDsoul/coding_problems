/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    
    public int countHeight(TreeNode root){
        if (root == null) return 0; 
        return 1 + countHeight(root.left);
    }
    
    public int countNodes(TreeNode root) {
        
        if (root == null) return 0;
        
        // 2^h - 1 - overall number of nodes except the last layer 
        // h - the height of the tree 
        // at least one node on the bottom 

        // do binary search on the last layer, 
        // every time you check wheter the pivot exists
        // you do this by doing binary search again, to make sure 
        // that the pivot node exist

        // how second binary search works 
        /*
                2
            3       4
          5   5    3       
        
          for example, you want to chekc that 3 on the bottom exists (its index is 3)
          so every time you check wheter index > pivot -> go right down, otherwise 
          go left down
        */
        
        int height = countHeight(root) - 1;
        int left = 1; 
        int right = (int) Math.pow(2, height); 
        
        while(left < right){
            int pivot = (left + right) / 2 + 1; 
            System.out.println(left + " " + right + " " + pivot);
            if (isExist(pivot, root, height)){
                left = pivot; 
            }else{
                right = pivot - 1;
            }
        }
        System.out.println(right + " " + left);
        
        return left + (int) Math.pow(2, height) - 1;
    }
    
    
    public boolean isExist(int index, TreeNode root, int height){
        
        
        int left = 1; 
        int right = (int) Math.pow(2, height); 
        
        while(left < right){
            int pivot = (left + right) / 2;
            if (index > pivot){
                left = pivot + 1; 
                root = root.right; 
            }else{
                right = pivot; 
                root = root.left;
            }
        }
        
        
        return root != null;
    }
}
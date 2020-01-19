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

    /*
        @param pre the preorder traverse of the tree
        @param post the postorder traverse of the tree
        @return the root of the tree in TreeNode representation
    */

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        return helper(pre, post, 0, pre.length - 1, 0, post.length - 1);
    }
    
    
    public TreeNode helper(int[] pre, int[] post, int preSt, int preEnd, int postSt, int postEnd){
        
        if (preSt > preEnd) return null;
            
        TreeNode node = new TreeNode(pre[preSt]);
        
        if (preSt == preEnd) return node;
        
        int leftSubInPre = preSt + 1;
            
        int index = postSt;
        
        // since the second element in preorder is the root of left subtree
        // find all elements before it in the post order traverse
        for(int i = postSt; i<=postEnd; i++){
           if (pre[leftSubInPre] == post[i]){
               index = i;
               break;
           } 
        }
        
        int leftSubEndInPre = leftSubInPre + (index - postSt);
        
        TreeNode left = helper(pre, post, leftSubInPre, leftSubEndInPre, postSt, index);
        // postEnd - 1 since the last Node is the root
        TreeNode right = helper(pre, post, leftSubEndInPre + 1, preEnd, index + 1, postEnd - 1);
        
        node.left = left;
        node.right = right;
        return node;
    }
    
    
    
    
}
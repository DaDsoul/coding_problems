class Solution {
    public int longestLine(int[][] M) {
        
        if (M == null || M.length == 0 || M[0].length == 0) return 0;
        
        // max consecutive
        int max = 0;
        
        /*
                otential neighbors:
                up, left, right left, right up
                dp[][][4] to indicate the direction of consecutives
        */
        
        int typeCount = 4; 
        int rowSize = M.length; 
        int colSize = M[0].length;
        
        int[][][] dp = new int[rowSize][colSize][4];
        
        
        // base case for the first elem
        if (M[0][0] == 1){
            max = 1;
            for(int i = 0; i<typeCount; i++){
                dp[0][0][i] = 1;
            }
        }
        
        // base case for first row
        for(int i = 1; i<colSize; i++){
            if (M[0][i] == 1){
                dp[0][i][1] = Math.max(dp[0][i - 1][1] + 1, dp[0][i][1]);
                max = Math.max(dp[0][i][1], max);
                dp[0][i][0] = 1; 
                dp[0][i][2] = 1; 
                dp[0][i][3] = 1; 
            }
        }
        
        for(int i = 1; i<rowSize; i++){
            for(int j = 0; j<colSize; j++){
                if (M[i][j] == 1){
                    
                    if (j > 0){
                        dp[i][j][1] = Math.max(dp[i][j - 1][1] + 1, dp[i][j][1]);
                        dp[i][j][2] = Math.max(dp[i - 1][j - 1][2] + 1, dp[i][j][2]);
                        max = Math.max(max, Math.max(dp[i][j][2], dp[i][j][1]));
                    }else{
                        // don't forget in case if you are on the right edge
                        dp[i][j][1] = 1;
                        dp[i][j][2] = 1; 
                    }
                    
                    dp[i][j][0] = Math.max(dp[i][j][0], dp[i - 1][j][0] + 1);
                    max = Math.max(dp[i][j][0], max);
                    
                    if (j < colSize - 1){
                        dp[i][j][3] = Math.max(dp[i - 1][j + 1][3] + 1, dp[i][j][3]);
                        max = Math.max(dp[i][j][3], max);
                    }else{
                        // in case if you on the right edge 
                        dp[i][j][3] = 1;
                    }
                    
                }
            }
        }
        
        return max;
    }
}
class Solution {
    public int matrixScore(int[][] A) {
        // preprocessing rows stage 
        for(int i = 0; i<A.length; i++){
            int firstDigit = A[i][0];
            if (firstDigit == 0){
                for(int j = 0; j<A[i].length; j++){
                    // it can be XOR as well
                     A[i][j] = A[i][j] == 1 ? 0 : 1;
                }
            }
        }

        // preprocessing columns 
        for(int i = 0; i<A[0].length; i++){
            // for one column
            int numberOfOnes = 0; 
            int numberOfZeros = 0; 
            for(int j = 0; j<A.length; j++){
                numberOfOnes += A[j][i]  == 1 ? 1 : 0;
                numberOfZeros += A[j][i]  == 0 ? 1 : 0;
            }
            if (numberOfZeros > numberOfOnes){
                for(int j = 0; j<A.length; j++){
                    A[j][i] = A[j][i] == 1 ? 0 : 1;
                }
            }
        }

        // convertBinaryToDigit , “0011” -> 3

        int totalSum = 0; 

        for(int i = 0; i<A.length; i++){
            int currentNumberInRow = 0;
            for(int j = A[i].length - 1; j>=0; j--){
        //	1 0 1 0		-> 	
                currentNumberInRow += A[i][j]*Math.pow(2, A[i].length - j - 1);
            }
            totalSum += currentNumberInRow;
        }

        return totalSum;
    }
}
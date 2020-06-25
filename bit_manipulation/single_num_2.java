class Solution {
    public int singleNumber(int[] nums) {
        int[] bitMap = new int[32];
        
        for(int j = 0; j<32; j++){
             for(int i = 0; i<nums.length; i++){
                bitMap[j] += (nums[i] >> j) & 1; 
                bitMap[j] %= 3;
            }
        }
        
        int result = 0; 
        for(int i = 0; i<32; i++){
            result |= (bitMap[i] << i);
        }
                
       return result;
    }
}
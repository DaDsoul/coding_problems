class Solution {
    public int[] searchRange(int[] nums, int target) {
        return new int[]{findBorder(nums, target, true), findBorder(nums, target, false)};
    }
    
    
    public int findBorder(int[] nums, int target, boolean forLeftBorder){
        
        int start = 0; 
        int end = nums.length - 1; 
        int index = -1; 
        
        while(start <= end){
            int mid = (start + end) / 2; 
            
            if (nums[mid] > target){
                end = mid - 1; 
            }else if (nums[mid] < target){
                start = mid + 1; 
            }
            
            if (nums[mid] == target){
                if (!forLeftBorder){
                    start = mid + 1; 
                }else{
                    end = mid - 1; 
                }
                
                index = mid;
            }
        }
        
        return index;
    }
}
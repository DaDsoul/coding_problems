class Solution {
    
    public boolean isOperator(char candidate){
        return candidate == '+' || candidate == '-' || candidate == '*' || candidate == '/';
    }
    
    public int calculate(String s) {
        
        if (s == null || s.length() == 0) return 0;
        
        Stack<Integer> nums = new Stack(); 
        char operator = '+';
        int finalResult = 0;
    
        
        for(int i = 0; i<s.length(); i++){
            
            char element = s.charAt(i);
            
            if (Character.isDigit(element)){
                int num = element - '0';
                while(i + 1 < s.length()){
                    element = s.charAt(i + 1);
                    if (Character.isDigit(element)){
                        num *= 10;
                        num += (element - '0');
                    }else{
                        break;
                    }
                    i = i + 1; 
                }
                
                if (operator == '+'){
                    nums.push(num);
                }else if (operator == '-'){
                    nums.push(num*(-1));
                }else if (operator == '*'){
                    nums.push(nums.pop()*num);
                }else if (operator == '/'){
                    nums.push(nums.pop()/num);
                }
                
            }else if (isOperator(element)){
                operator = element;
            }else if (element == ' '){
                continue;
            }
        }
        
        for(Integer num: nums){
            finalResult += num;
        }
        
        return finalResult;
    }
}
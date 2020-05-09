class Solution {
    
    public String extractContent(String file){
        
        StringBuilder result = new StringBuilder("");
        int end = file.length() - 1; 
        
        if (file.charAt(end) != ')'){
            return result.toString();
        }else{
            end -= 1;
            while(file.charAt(end) != '('){
                result.append(file.charAt(end));
                end -= 1;
            }
            
            return result.reverse().toString();
        }
    }
    
    public List<List<String>> findDuplicate(String[] paths) {
        
        Map<String, List<String>> contentToPaths = new HashMap();
        
        for(String path: paths){
            String[] pathSpecifics = path.split(" ");
            String root = pathSpecifics[0];
            for(String file: pathSpecifics){
                if (file.equals(root)) continue;
                String content = extractContent(file);
                if (content == "") continue; 
                String onlyFileName = file.substring(0, file.length() - content.length() - 2);
                String pathToFile = root + '/' + onlyFileName;
                contentToPaths.computeIfAbsent(content, k -> new ArrayList()).add(pathToFile);
            }
        }
        
        List<List<String>> finalAns = new ArrayList(); 
        
        for(String content: contentToPaths.keySet()){
         //   Set<String> 
            List<String> contentPaths = contentToPaths.get(content);
            if (contentPaths.size() > 1)
                finalAns.add(new ArrayList(contentPaths));
        }
        
        return finalAns;
    }
}
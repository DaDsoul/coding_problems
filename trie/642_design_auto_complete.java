class AutocompleteSystem {
    /*
        Implementation would be done 
        with the Trie Data structure

        every trie has the array of 27 tries
        indicating the possible letters and the empty 
        space since the English alphabet contains 26 letters
        
    */

    class Node{

        String sentence; 
        Integer times;

        public Node(String st, Integer t){
            this.sentence = st;
            this.times = t;
        }

    }

    class Trie{
        int times; 

        // 26 English letters plus " " element
        Trie[] letters = new Trie[27];
    }

    public int toInt(char c){
        // if it is empty, assume
        // it is the last elemnt
        // otherwise, a letter
        return c == ' ' ? 26 : c - 'a';
    }


    // insertion of a word into the trie
    public void insert(Trie t, String s, int times){
        for(int i = 0; i<s.length(); i++){
            int index = toInt(s.charAt(i));
            if (t.letters[index] == null){
                t.letters[index] = new Trie();
            }
            t = t.letters[index];
        }

        // last inserted character would indicate the end of a sentense
        // thus will contain times varibale in the Trie
        t.times += times;
    }

    public List<Node> findSentenses(Trie t, String s){
        List<Node> sentences = new ArrayList(); 
        
        // find the position of the S
        for(int i = 0; i<s.length(); i++){
            int index = toInt(s.charAt(i));
            if (t.letters[index] == null){
                return new ArrayList();
            }
            t = t.letters[index];
        }
        // after finding the S, traverse to the Trie to find the 
        // candidate sentences
        traverse(s,t,sentences);
        return sentences; 
    }

    public void traverse(String s, Trie t, List<Node> list){
        if (t.times > 0){
            list.add(new Node(s, t.times));
        }
        for(char i = 'a'; i<='z'; i++){
            int index = toInt(i);
            if (t.letters[index] != null){
                traverse(s + i, t.letters[index], list);
            }
        }
        
        if (t.letters[26] != null){
            traverse(s + ' ', t.letters[26], list);
        }
    }
    Trie root = new Trie();

    public AutocompleteSystem(String[] sentences, int[] times) {
        for(int i = 0; i<sentences.length; i++){
            insert(root, sentences[i], times[i]);            
        }
    }
    
    StringBuilder curString = new StringBuilder("");
    public List<String> input(char c) {
        List<String> result = new ArrayList();
        if (c == '#'){
            insert(root, curString.toString(), 1);
            curString = new StringBuilder("");
        }else{
            curString.append(c);
            List<Node> sentences = findSentenses(root, curString.toString());
            Collections.sort(sentences, new Comparator<Node>(){
                public int compare(Node one, Node two){
                    if (one.times.equals(two.times)){
                        /*
                            if several sentences have the same degree of hot, 
                            you need to use ASCII-code order (smaller one appears first).
                        */
                        return one.sentence.compareTo(two.sentence);
                    }
                    // top 3 hot sentences should be sorted by hot degree
                    return Integer.compare(two.times, one.times);
                }
            });
            

            // If less than 3 hot sentences exist, then just return as many as you can.
            int hotSize = Math.min(3, sentences.size());

            for(int i = 0; i<hotSize; i++){
                result.add(sentences.get(i).sentence);
            }
        }
        return result;
    }
}
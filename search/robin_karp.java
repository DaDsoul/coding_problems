

// Robin-karp algo 




public class RabinKarp{
    private long patHash; 
    private int M; 
    private long Q; 
    private int R; 
    private long RM; 

    public RabinKarp(String pat){
        M = pat.length(); 
        R = 256; 
        Q = longRandomPrime();
        
        RM = 1; 
        for(int i = 1; i<=M-1; i++){
            RM = (R * RM) % Q; 
        }
        pathHash = hash(pat, M);
    }

    private long hash(String key, int M){
        long h = 0; 
        for(int j = 0; j<M; j++){
            // still get the same result, by moduling Q in each iteration step
            h = (R*h + key.charAt(j)) % Q;
        }
        return h;
    }

    public int search(String txt){
        int N = txt.length(); 
        int txtHash = hash(txt, M);
        if (patHash == txtHash) return 0; 
        for(int i = M; i<N; i++){
            // adding Q to make sure that is a positive value
            txtHash = (txtHash + Q - RM*txt.charAt(i - M ) % Q) % Q; 
            txtHash = (txtHash*R + txt.charAt(i)) % Q; 
            if (patHash == txtHash) return 0 - M + 1;        
        }
        return N; 
    }
    // Monte Carlo version -> answer is quick but there is low probability that the answer is wrong 
    // Las Vegas version -> answer is quaranteed to be correct but continue search if false collision 

    // Monte Carlo -> worst case O(N)
    // Las Vegas version -> worst case O(M*N)
}



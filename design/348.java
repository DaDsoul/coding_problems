class TicTacToe {
    
    private int[][] history; 
    private int length; 
    private List<Integer> directions;
    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        this.history = new int[n][n];
        this.length = n; 
        this.directions = Arrays.asList(0, 1, 2, 3);
    }
    
    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) {
        if (row >= this.length || row < 0 || col >= this.length || col < 0){
            return 0;
        }else{
            this.history[row][col] = player;
            
            for(int direction: this.directions){
                int oneSideCheck = checkCase(row, col, player, direction, false);
                int anotherSideCheck = checkCase(row, col, player, direction, true);
                int result = oneSideCheck + anotherSideCheck - 1;                 
                if (result == this.length){
                    return player;
                }
            }
            
            return 0; 
        }
    }
    
    public int checkCase(int row, int col, int player, int direction, boolean isAnotherSide){
        if (row >= this.length || row < 0 || col >= this.length || col < 0){
            return 0;
        }
        
        if (!(this.history[row][col] == player)){
            return 0;
        }
        
        if (direction == 0){
            return isAnotherSide ? 1 + checkCase(row - 1, col - 1, player, direction, isAnotherSide) : 1 + checkCase(row + 1, col + 1, player, direction, isAnotherSide);
        }else if (direction == 1){
            return isAnotherSide ? 1 + checkCase(row - 1, col, player, direction, isAnotherSide) : 1 + checkCase(row + 1, col, player, direction, isAnotherSide);
        }else if (direction == 2){
            return isAnotherSide ? 1 + checkCase(row, col - 1, player, direction, isAnotherSide) : 1 + checkCase(row, col + 1, player, direction, isAnotherSide);
        }else{
            return isAnotherSide ? 1 + checkCase(row - 1, col + 1, player, direction, isAnotherSide) : 1 + checkCase(row + 1, col - 1, player, direction, isAnotherSide);
        }
    }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */
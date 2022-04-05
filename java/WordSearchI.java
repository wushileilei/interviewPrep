//查某个单词是否在矩阵里
// typical dfs
class Solution {
    private int[][] directs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                boolean flag = check(board, word, i, j, 0, visited);
                if(flag){
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean check(char[][] board, String word, int row, int col, int index, boolean[][] visited) {
        if (row >= board.length || row < 0 || col >= board[0].length || col < 0 || visited[row][col]) {
            return false;
        }
        if (board[row][col] != word.charAt(index)) {
            return false;
        } 
        // find
        if (index == (word.length() - 1) && board[row][col] == word.charAt(index)) {
            return true;
        }
        visited[row][col] = true;        
        boolean result = false;
        for (int[] direct : directs) {
        	// check next
            boolean flag = check(board, word, row + direct[0], col + direct[1], index + 1, visited);
            if (flag) {
                result = true;
                break;
            }
        } 
        // backtracking
        visited[row][col] = false;
        return result;

    }
}

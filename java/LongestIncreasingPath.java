// Given an m x n integers matrix, return the length of the longest increasing path in matrix.

// From each cell, you can either move in four directions: left, right, up, or down. You may 
// not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).
/*
    for each cell dfs 
    prunning when not satisfy
    use int[][] memo to store the result of the cell to prevent recalculate
    for exp1: memo[2][0] = 2
    O(mn) time space both
*/
class Solution {
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int row, col;
    
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        row = matrix.length;
        col = matrix[0].length;
        // store len of path from that cell
        int[][] memo = new int[row][col];
        int ans = 0;
        
        // dfs
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                ans = Math.max(ans, dfs(matrix, i, j, memo));
            }
        }
        
        return ans;
    }
    
    private int dfs(int[][] matrix, int i , int j, int[][] memo) {
        // already calculate, don't repeat
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        // add current step
        memo[i][j]++;
        
        // try next step
        for (int[] dir : dirs) {
            int newRow = i + dir[0], newCol = j + dir[1];
            // inboard & increasing
            if (newRow >= 0 && newRow < row && newCol >= 0 && newCol < col 
               && matrix[newRow][newCol] > matrix[i][j]){
                // don't forget + 1
                memo[i][j] = Math.max(memo[i][j], dfs(matrix, newRow, newCol, memo) + 1);
            }
        }
         return memo[i][j];
    }
}
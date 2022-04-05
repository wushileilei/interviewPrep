import java.util.Scanner;

//Write a program to solve a Sudoku puzzle by filling the empty cells.
//
//A sudoku solution must satisfy all of the following rules:
//
//Each of the digits 1-9 must occur exactly once in each row.
//Each of the digits 1-9 must occur exactly once in each column.
//Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
//The '.' character indicates empty cells.
class Soduku {
    /*
        try solution not work stop go back
        backtracking
        worse O(9^N) N = empty cells
        but actually much better. depending the givenboard
    */
    final int rowLen = 9;
    final int colLen = 9;
    
    public void solveSudoku(char[][] board) {

        // boolean for fill constraints col 0 is useless
        boolean[][] rowUsed = new boolean[rowLen][10];
        boolean[][] colUsed = new boolean[colLen][10];
        boolean[][][] boxUsed = new boolean[rowLen / 3][colLen / 3][10];
        
        // initial boolean arrays
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (board[i][j] != '.') {
                    // char to num - '0'
                    int num =(int) (board[i][j] - '0');
                    rowUsed[i][num] = true;
                    colUsed[j][num] = true;
                    boxUsed[i / 3][j / 3][num] = true;
                }
            }
        }
        // try to fill from (0,0)
        backTracking(board, rowUsed, colUsed, boxUsed, 0, 0);
    }
    // 
    private boolean backTracking(char[][] board, boolean[][] rowUsed, boolean[][] colUsed, 
    							 boolean[][][] boxUsed, int row, int col) {
        // to the end of a row
        if (col == colLen) {
            // to next row
            row++;
            col = 0;
            
            // finish all row
            if (row == rowLen) {
            	// solved
                return true;
            }
        }
        
        // already num
        if (board[row][col] != '.') {
        	// next cell
            return backTracking(board, rowUsed, colUsed, boxUsed, row, col + 1);
        }
        else {
            // empty cell
            for (int num = 1; num <= 9; num++) {
                //check used
                boolean flag = !(rowUsed[row][num] || colUsed[col][num] || boxUsed[row / 3][col / 3][num]);
                
                // fill
                if(flag){
                    rowUsed[row][num] = true;
                    colUsed[col][num] = true;
                    boxUsed[row / 3][col / 3][num] = true;
                    // num to char + '0'
                    board[row][col] = (char)(num + '0');
                    
                    // check work?
                    if(backTracking(board, rowUsed, colUsed, boxUsed, row, col + 1)) {
                        return true;
                    }
                    
                    // backtracking
                    else{
                        rowUsed[row][num] = false;
                        colUsed[col][num] = false;
                        boxUsed[row / 3][col / 3][num] = false;
                     board[row][col] = '.';                       
                    }
                }
            }
        }
        
        return false;
    }
    
    public static void main(String[] args) {
    	
    }
    
}

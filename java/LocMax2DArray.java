import java.util.Scanner;

public class LocMax2DArray {
    /* Approach:
        n * m ---> find the biggest ---> must qualify ---> time 0(n * m)
        or 
        binary from the mid row since if mid rowbiggest > neighbor row biggist ---->qualify 
        or the answer should in the bigger side.
        Algorithm: binary search row, col iterator
       time O(mlogn) space O(1)
        
    */
        
    public static int[] findPeakGrid(int[][] mat) {
        int n = mat.length;
        int start = 0, end = n - 1;
        
        while (start + 1 < end) { // exit start end two rows left
            int mid = start + (end - start) / 2;
            
            // col of biggest element
            int midCol = findMaxCol(mat, mid);
            int maxMid = mat[mid][midCol];
            
            // initial a small enough num
            int maxUp = -1, maxDown = -1;
            
            // out of bound risk
            if (mid - 1 >= 0) 
                maxUp = mat[mid - 1][findMaxCol(mat, mid - 1)];
            if (mid + 1 < n) 
                maxDown = mat[mid + 1][findMaxCol(mat, mid + 1)];
            
            if (maxMid > maxDown && maxMid > maxUp) 
                return new int[]{mid, midCol};
            else if(maxDown > maxMid) 
                start = mid;
            else 
                end = mid;
        }
        // 2 row left
        int startCol = findMaxCol(mat, start);
        int maxStart = mat[start][startCol];
        int endCol = findMaxCol(mat, end);
        int maxEnd = mat[end][endCol];
        
        if (maxStart > maxEnd) {
            return new int[]{start, startCol};
        }
        else {
            return new int[]{end, endCol};
        }
    }
    
    // return the biggest col index of a row
    private static int findMaxCol(int[][] mat, int row) {
        int maxCol = 0;
        for (int col = 0; col < mat[row].length; col++) {
            if (mat[row][maxCol] <= mat[row][col]) {
                maxCol = col;
            }
        }   
        return maxCol;
    }
    
    public static void main(String[] args) {
    	System.out.println("Test");
    	//[0, 1] expected
    	// 1 4
    	// 3 2
    	int[][] testCase = { {1,4},{3,2}};
    	int[] result = findPeakGrid(testCase);
    	System.out.print(result[0] + " " + result[1]);
    }
}
import java.util.*;
import java.util.Scanner;
/*
 *  1given 3 * 3 matrix -----> string"123456780" target
 *  
 *  2.define neighbor by index
 *  0 1 2
 *  3 4 5
 *  6 7 8
 *  neighbors[][] = {{},{},{},{},{},{},{},{},{}}; row index col neighbors
 *  
 *  3.bfs when meet duplicates stop(we meet this string before)
 *  // depends
 *  time 9 factorial
 *  space 9 factorial
 */
public class SlidingPuzzle {
	// row index of num ; col neighbors
	private static int[][] neighbors = {{1, 3},{1,4, 2},{1, 5},
								 {0, 4},{1, 3, 5, 7},{2, 4,8},
								 {3, 7},{6, 4, 8},{7, 5}};
	
    static int slidingPuzzlet(int[][] board) {

    	String target = "123456780";
    	
    	// builder the board to string
    	StringBuilder sb = new StringBuilder();
    	for (int[] row : board) {
    		for (int col : row) {
    			sb.append(col);
    		}
    	}
    	String start = new String(sb);
    	// what if we are lucky
    	if (target.equals(start) ) {
    		return 0;
    	}
    	// a set for avoid duplicates
    	Set<String> status = new HashSet<>();
    	status.add(start);
    	
    	// bfs
    	Queue<String> queue = new LinkedList<>();
    	queue.offer(start);	
    	// step count
    	int step = 0;
    	
    	while (!queue.isEmpty()) {
    		step++;
    		int size = queue.size();
    		for (int i = 0; i < size; i++) {
    			String cur = queue.poll();
    			
    			for (String next : getNext(cur)) {
    				if (!status.contains(next)) {
    					if (next.equals(target)) {
    						return step;
    					}
    					status.add(next);
    					queue.offer(next);
    				}
    			}
    		}
    	}
    	
    	// default
    	return -1;
    }
    // find valid next move	
    static List<String> getNext(String cur) {
    	List<String> res = new ArrayList<>();
    	int zero = cur.indexOf('0');
    	// cur board
    	char[] board = cur.toCharArray();
    	// try to move
    	for (int index : neighbors[zero]) {
    		swap(board, index, zero);
    		res.add(new String(board));
    		// move back for another try
    		swap(board, index, zero);
    	}
    	return res;
    }
    
    private static void swap(char[] board, int a, int b) {
    	char temp = board[a];
    	board[a] = board[b];
    	board[b] = temp;
    }
    
    public static void main(String[] args) {
    	//1 2 3
    	//4 0 6
    	//7 5 8
    	// 2 step
    	int[][] startBoard = {{1,2,3},{4, 0, 6},{7, 5, 8}};
    	
    	System.out.println(slidingPuzzlet(startBoard));
    }
}

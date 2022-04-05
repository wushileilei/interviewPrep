
public class Solution {
    /**
     * @param targetMap: 
     * @return: nothing
     */
    // Given a 2D array representing the coordinates on the map, 
    // there are only values 0, 1, 2 on the map. value 0 means 
    // that it can pass, value 1 means not passable, value 2 means 
    // target place. Starting from the coordinates [0,0],You can only 
    // go up, down, left and right. Find the shortest path that can reach 
    // the destination, and return the length of the path.
    /*
        shortest path -----> bfs 
        set visited
        level of the bfs
    */
    private final int TARGET = 2, PASS = 0, NOTPASS = 1;
    public int shortestPath(int[][] targetMap) {
        // Write your code here
        // visited
        int n = targetMap.length, m = targetMap[0].length;
        boolean[][] visited = new boolean[n][m];
        //moving directs
        int[][] move = {{0, 1}, {0, -1}, {1, 0,}, {-1, 0}};

        //queue for bfs [x, y] for the cell
        Queue<int[]> queue = new LinkedList<>();
        // start at the 0,0
        queue.offer(new int[]{0,0});
        // BST level
        int steps = 0;

        // BST
        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                // try to move
                for (int[] next : move) {
                    int x = cur[0] + next[0];
                    int y = cur[1]+ next[1];
                    if (isValid(x, y, targetMap) && !visited[x][y]){
                        queue.offer(new int[]{x, y});
                        visited[x][y] = true;
                        if (targetMap[x][y] == TARGET){
                            return steps + 1;
                        }

                    }
                }
            }
            steps++;
        }

       return -1;
    }
    private boolean isValid(int x, int y, int[][] board) {
        // not in board
        int n = board.length, m = board[0].length;
        if (x < 0 || x >= n || y< 0 || y >= m){
            return false;
        }
        // not pass or visited
        if (board[x][y] == NOTPASS) {
            return false;
        }
        return true;
    }
}
//with K elimination ----> can pass obstacle k times
//base bfs add another flag for track pass 1
//here visited is to mark k left for us to pass -1 means not visited
//BFS对于当前点的下一个点选择，如果grid[i][j]=0则有效入队列 visited[i][j]记录消除障碍次数
//若grid[i][j]=1则看是否还有消除障碍机会，若没有 此点丢弃
//若有消除障碍机会， （上一个点剩余消除障碍机会 - 1）比visited[i][j] 值比大 此点入队， 小则丢弃（贪心）
//例子：k=1, 坐标(0,2)可以为消除(0,1)障碍过来的 visited[0][2] = 0，搜索层级为2
//也可能为不消除任何障碍过来的 visited[0][2] = 1，层级为6，更新visited[0][2] = 1并入队
//因为到后面还需要消除障碍才能到达目标，先消除障碍走到visited[0][2] = 0的肯定到不了目标...
//0 1 0 0 0 1 0 0
//0 1 0 1 0 1 0 1
//0 0 0 1 0 0 1 0

//class Solution {
// public int shortestPath(int[][] grid, int k) {
//     int row = grid.length, col = grid[0].length;
//     if (row == 1 && col == 1) {
//         return 0;
//     }
//
//     // direction matrix
//     int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
//     Deque<int[]> queue = new LinkedList<>();
//     int[][] visited = new int[row][col];
//     for (int i = 0; i < row; ++i) {
//         for (int j = 0; j < col; ++j) {
//             visited[i][j] = -1;
//         }
//     }
//
//     queue.offerLast(new int[]{0, 0 ,k});
//     visited[0][0] = k;
//     int step = 0;
//
//     while (!queue.isEmpty()) {
//         int currSize = queue.size();
//         step++;
//         for (int i = 0; i < currSize; ++i) {
//             int[] currPos = queue.pollFirst();
//
//             for (int j = 0; j < 4; ++j) {
//                 int nx = currPos[0] + dirs[j][0];
//                 int ny = currPos[1] + dirs[j][1];
//                 int currK = currPos[2];
//                 // 保证下一步的位置在矩阵中
//                 if (nx >= 0 && nx < row && ny >= 0 && ny < col) {
//                     // 判断是否到达右下角
//                     // 题目中明确了 grid[0][0] == grid[m-1][n-1] == 0，若目标点可以为1，需要修改此部分
//                     if (nx == row - 1 && ny == col - 1) {
//                         return step;
//                     }
//                     currK = grid[nx][ny] == 0 ? currK : --currK;
//
//                     if (currK >= 0) {
//                         // 1. 对于其他路径到达此点且剩余消除障碍物次数小于等于当前值 —— 剪枝
//                         // 2. 对于其他路径到达此点且剩余消除障碍物次数大于当前值 —— 取代并入队
//
//                         if (visited[nx][ny] == -1 || (visited[nx][ny] != -1 &&
//                                                       currK > visited[nx][ny]) ) {
//                             queue.offerLast(new int[]{nx, ny, currK});
//                             visited[nx][ny] = currK;
//                         }
//                     }
//                 }
//             }
//         }
//     }
//     return -1;
// }
//}

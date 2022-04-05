import java.io.*;

import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
// 给出一个list里有哪些单词可以在矩阵里找到， 用prefix tree
class Solution {
    /*
     * multy match use trie
        1.backtracking for each cell
        2.prefix tree (not in the tree go back)
        ---------------
        build prefix tree----> dfs-----> use a set for duplicates
        exp:
                        ""
        o       p           e           r
        a       e           a           a
                a(end)      t(end)      i
        t                               n(end)
        h(end)
        
        
    */

    // build tree
    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode cur = root;
            char[] array = word.toCharArray();
            for (char ch : array) {
                if (cur.kids[ch - 'a'] == null) {
                    cur.kids[ch - 'a'] = new TrieNode();
                }
                cur = cur.kids[ch - 'a'];
            }
            cur.isEnd = true;
        }
        return root;
    }
    // moving directs
    private int[][] directs= {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    // dfs
    private void dfs(char[][] board, int i, int j, TrieNode node, Set<String> resSet, StringBuilder curWord, boolean[][] visited) {
        // index invalid exit
        if (i >=board.length || i < 0 || j >= board[0].length || j < 0) return;
        // not in trie or visited
        if (node.kids[board[i][j] - 'a']== null || visited[i][j]) return;
        // add char
        curWord.append(board[i][j]);
        
        
        // is it end aa and aaa so cant return
        if (node.kids[board[i][j] - 'a'].isEnd) 
            resSet.add(new String(curWord));
        
        visited[i][j] = true;
        // search next word
        for (int[] direct : directs) {
            dfs(board, i + direct[0], j + direct[1], node.kids[board[i][j] - 'a'], resSet, curWord, visited);
        }
        // backtracking
        curWord.deleteCharAt(curWord.length() - 1);
        visited[i][j] = false;
    }
    
    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = buildTrie(words);
        Set<String> resSet = new HashSet<>();
        boolean[][] visited = new boolean[board.length][board[0].length];
        
        // dfs 
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // board i j ---> start cell  root for trie root for curTriePosition
                dfs(board, i, j, root, resSet, new StringBuilder(), visited);
            }
        }      
        return new ArrayList<String>(resSet);
    }
}
 // node for trie
class TrieNode {
        boolean isEnd = false;
        TrieNode[] kids = new TrieNode[26];
    }
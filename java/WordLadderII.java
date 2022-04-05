class Solution {
    /*
        shortest path----> bfs
        all paths -----> backtracking 
        flag : when to stop bfs
        set for dit
        list for path
        queue
                    hit
       1:    hot. ------
       2:   dot     lot    
       3    dog     log 
        4:  cog     cog
        for shortest set for visited words
    */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ans = new ArrayList<>();
        Set<String> dic = new HashSet<>(wordList);
        Set<String> visited = new HashSet<>();
        
        dic.remove(beginWord);
        if (!dic.contains(endWord)) {
            return ans;
        }
        
        bfs(beginWord,  endWord, ans, dic, visited);
        
        return ans;
    }
    // bfs
    private void bfs(String from, String endWord, List<List<String>> ans, Set<String> dic, Set<String> visited) {
        // begin path
        List<String> path= new ArrayList<>();
        path.add(from);
        boolean findOne = false;
        
        // queue for bfs, PATHS inside
        Queue<List<String>> queue = new LinkedList<>();
        queue.add(path);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            Set<String> subVisited =  new HashSet<>();
            // level search
            for (int i = 0; i < size; i++) {
                List<String> temp = queue.poll();
                String cur = temp.get(temp.size() - 1);
                // if a word is used in last level, then no need to use again.(not shortest)
                List<String> neighbors = getNeighbors(cur, dic);
                for (String neighbor : neighbors) {
                    
                    if (!visited.contains(neighbor) && neighbor.equals(endWord)) {
                        temp.add(neighbor);
                        ans.add(new ArrayList<>(temp));
                        // backtracking
                        temp.remove(temp.size() - 1);
                        findOne = true;
                    }
                    if (!visited.contains(neighbor)){
                        // work but not end
                         temp.add(neighbor);
                        // add for next level search
                        
                        queue.add(new ArrayList<>(temp));
                        temp.remove(temp.size() - 1);
                        subVisited.add(neighbor);
                    }
                }
            }
            visited.addAll(subVisited);
            // word can be used in last level should not be use in next
            
            if (findOne) {
                break;
            }
        }
    }
    
    private List<String> getNeighbors(String cur, Set<String> dic) {
        List<String> ans = new ArrayList<>();
        char[] str = cur.toCharArray();
        for (int i = 0; i < str.length; i++) {
            char old_char = str[i];
            
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (ch == old_char) {
                    continue;
                }
                str[i] = ch;
                String next = String.valueOf(str);
                
                if(dic.contains(next)) {
                    ans.add(new String(next));
                }
                str[i] = old_char;
             
            }
        }
        
        return ans;
    }
}
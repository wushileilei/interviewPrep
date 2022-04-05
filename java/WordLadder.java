class Solution {
    /*
                hit
         (a-z)it    h(a-z)t     hi(a-z)      in dic
                    hot         
                 lot dot 
               dog
               cog
                 
        bfs  set for visited      
    */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // dic set
        Set<String> dic = new HashSet<>();
        for (String word : wordList) {
            dic.add(word);
        }
        
        if (dic.size() == 0 || !dic.contains(endWord)) {
            return 0;
        }
        dic.remove(beginWord);
        int seq = 1;
        
        // bfs queue
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                if (canFindEnd(cur, endWord, dic, queue)) {
                    return seq + 1;
                }
            }            
             seq++;
        }
        
       return 0;
    }
    
    private boolean canFindEnd(String cur, String endWord, Set<String> dic, Queue<String> queue) {
        char[] str = cur.toCharArray();
        for (int i = 0; i < str.length; i++) {
            char ch = str[i];
            
            for(char newChar = 'a'; newChar <= 'z'; newChar++) {
                if (newChar == str[i]) {
                    continue;
                }
                str[i] = newChar;
                String next = String.valueOf(str);
                if (dic.contains(next) && next.equals(endWord)) {
                    return true;
                }
                
                if (dic.contains(next)){
                    queue.add(next);
                }
                dic.remove(next);
            }
            str[i] = ch;
        }
        
        return false;
    }
}
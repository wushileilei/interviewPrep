//Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:
//
//'.' Matches any single character.​​​​
//'*' Matches zero or more of the preceding element.
//The matching should cover the entire input string (not partial).
class RegExpMatch {
    /*
        dp[i][j] means s(0-i) match p (0-j) ?
        right--->left
        1.p in a-z : if p[j] = s[i] dp[i][j] = dp[i-1][j-1]
        2.p is "."  dp[i][j] = dp[i-1][j-1]
        3.p is "*"
         		a) zero  exp: a* ignore dp[i][j] = dp[i][j-2]
              	b) one or more exp: p---> a* or .*  s---> aaa 
              	if s[i] == p[j - 1] || p[j -1] == .----> dp[i][j] = dp[i-1][j]
              	
        4. coner case s or p null so dp row = s[i]+1 col = p[j] + 1 
        -------> when using p & s index-1
        
        T & S O(n + m)
    */
    public static boolean isMatch(String s, String p) {
        int n = s.length(), m = p.length();
        boolean[][] dp = new boolean[n + 1][m + 1];
        // both null
        dp[0][0] = true;
        
        char[] str = s.toCharArray();
        char[] pat = p.toCharArray();
        
        for (int i = 0; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // 1&2
                if (pat[j - 1] != '*') {
                    if (i > 0 && (pat[j - 1] == str[i - 1] || pat[j - 1] == '.')) 
                        dp[i][j] = dp[i - 1][j - 1];
                }
                // 3
                else {
                    if (j > 1) 
                        dp[i][j] |= dp[i][j - 2];
                    if (i > 0 && j > 1 && (str[i - 1] == pat[j - 2] || pat[j - 2] == '.'))
                        dp[i][j] |= dp[i - 1][j];
                }       
            }
        }       
        return dp[n][m];
    }
    
    public static void main(String[] args) {
    	System.out.println("test");
    	// true is expected
    	boolean result = isMatch("catcat", "c*t*t");
    	System.out.println(result);
    }
}
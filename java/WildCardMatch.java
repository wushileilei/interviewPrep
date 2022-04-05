//Given an input string (s) and a pattern (p), implement wildcard pattern matching
//with support for '?' and '*' where:
//'?' Matches any single character.
//'*' Matches any sequence of characters (including the empty sequence).
//The matching should cover the entire input string (not partial).


class WildCardMatch {
    /*
        s p ?--> a-z    * null or any
        dp[i][j] s0-i match p0-j
        right--->left
        coner case s = ''   p = '*'/ p = ''  
        1.p a-z s[i] == p[j] ----> dp[i][j] = dp[i-1][j-1]
        2.p ? dp[i][j] = dp[i-1][j-1]
        3.p * 
            a) empty ---> dp[i][j] = dp[i][j - 1]
            b) exp a* baaa a aaa bbsa --> dp[i][j] = dp[i-1][j]
    */
    public static boolean isMatch(String str, String pattern) {
        int n = str.length(), m = pattern.length();
        char[] s = str.toCharArray();
        char[] p = pattern.toCharArray();
        // add one for empty
        boolean[][] dp = new boolean[n +  1][m + 1];
        
        // initialize
        // both null
        dp[0][0] = true;
        // if s is null but p is * or **** true; but **a not true
        for (int j = 1; j <= m; j++) {
            if (p[j - 1] == '*') {
                dp[0][j] = true;
            }
            else {
                break;
            }
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // 1& 2
                if (p[j - 1] != '*') {
                    if (s[i - 1] == p[j - 1] || p[j - 1] == '?') {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
                else{
                    dp[i][j] = dp[i][j - 1] | dp[i - 1][j];
                }
                
            }
        }
        return dp[n][m];
    }
    
    public static void main(String[] args) {
    	System.out.println("test");
    	// false is expected
    	boolean result = isMatch("aa", "a");
    	System.out.println(result);
    }
}
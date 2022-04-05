import java.util.Scanner;

class BigInteger {
    // what if it is add now
    /*
       1 2 6
         5 6    res[bigger + 1] make num1 is the longer
       res[0000]--->[0012]--- add 7 to it
       1 + 7 = 8
       res[i + 1] + sum --->update[i][i+ 1](i left i+ 1right)
       O(m)
    */
    public static String add(String num1, String num2) {
        if (num1.equals("0")) return num2;
        if (num2.equals("0")) return num1;
        
        // num1 be the longer
        if (num1.length() < num2.length()) {
            String temp = new String(num1);
            num1 = new String(num2);
            num2 = new String (temp);
        }
        
        int n = num1.length(), m = num2.length();
        int[] res = new int[n + 1];
        int j = m - 1;
        int i = n - 1;
        
        while (i >= 0){
            int sum = (num1.charAt(i) - '0');
            if (j >= 0) {
                sum += (num2.charAt(j) - '0');
                j--;
            }
            // 2 + 3 [0 0]
            sum += res[i + 1];
            res[i + 1] = sum % 10;
            res[i] = sum / 10 + res[i];
            i--;
        }
        // ignore leading zero
        int index = 0;
        while (index < res.length && res[index] == 0) {
            index++;
        }
        
        StringBuilder sb = new StringBuilder();
        for (; index < res.length; index++) {
            sb.append(res[index]);
        }
        
        return sb.toString();
    }
    /*
    s1,s2 the product  res[m+n] res[000138] 9*9 ==81 2
    1 2 3
    4 5 6
    ------
        18
       12  s1[1] s2[2]  res[i + j + 1] = 1 res[i + 1] == 0
       12 + 1 = 13
       res[i+j+1] = 13 % 10
       res[i + j] = 13 / 10 + res[i + j]
       O(mn)
*/
    public static String multiply(String num1, String num2) {
    	// connercase
    	if (num1.equals("0") || num2.equals("0")) return "0";
    
    	int[] res = new int[num1.length() + num2.length()];
    	char[] a = num1.toCharArray();
    	char[] b = num2.toCharArray();
    	
    	// right -- left
    	for (int i = a.length - 1; i >= 0; i--) {
    		for (int j = b.length - 1; j >= 0; j--) {
    			// mul
    			int mul = (a[i] - '0') * (b[j] - '0');
            
    			int sum = mul + res[i + j + 1];
    			res[i + j + 1] = sum % 10;
    			res[i + j] += sum / 10;
    		}
    	}
    
    	// toString
    	int index = 0;
    	while (index < res.length && res[index] == 0) {
    		index++;
    	}
    	StringBuilder sb = new StringBuilder();
    	for (; index < res.length; index++) {
    		sb.append(res[index]);
    	}
    
    	return sb.toString();
    }
    public static void main(String[] args) {
    	String num1 = "3", num2 = "4";
    	System.out.println(multiply(num1, num2));
    	System.out.println(add(num1, num2));
    }

}
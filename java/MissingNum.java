///*
// * So the sum of all n elements, i.e sum of numbers from 1 to n can be calculated 
// * using the formula n*(n+1)/2. 注意是1 - n!!!!
// * Now find the sum of all the elements in the array 
// * and subtract it from the sum of first n natural numbers, it will be the value 
// the missing element.
// */5
// 要注意数组的大小是包含了迷失的数只是用0来占位， 还是会另外给出n个数 注意1到n是有n个数字
////Given an array nums containing n distinct numbers in the range [0, n], return 
////the only number in the range that is missing from the array.
//class Solution {
//	// time O(n) space O(1) but if n is very large might overflow
//    // sum of 1 to n num = n(n+1)/2
//    public int missingNumber(int[] nums) {
//        int n = nums.length;
//        int sum = (n + 1) * n /2;
//        for (int num : nums) {
//            sum -= num;
//        }
//        return sum;
//    }
//}
//}

//class Solution {
//// Approach: The approach remains the same but there can be overflow if n is large. In order to avoid integer overflow, pick one number from known numbers and subtract one number from given numbers. This way there won’t have Integer Overflow ever.
//// Algorithm: 
//// Create a variable sum = the begin num which will store the missing number 
//// Traverse the array from start to end.
//// Update the value of sum as sum = sum – array[i] + c and update c as c++.
//// Print the missing number as a sum.
//    
//    public int missingNumber(int[] nums) {
//
//        int total = 1;
//        // nums 3  0  1 4
//        //taotal  1 + 2 + 3 - 3 - 0- 1 =2
//        // 4 left
//        for (int i = 2; i <= nums.length ; i++) {
//            // 1 + 2 + 3 +4
//            total += i;
//            // - 3 - 0
//            total -= nums[i - 2];
//        }
//        total -= nums[nums.length - 1];
//        
//        return total;
//    }
//}Given an array of n unique integers where each element in the array is in the range [1, n].
// The array has all distinct elements and the size of the array is (n-2). Hence Two numbers from t
//he range are missing from this array. Find the two missing numbers.
//Input  : arr[] = {1, 3, 5, 6}
//Output : 2 4
//
//Input : arr[] = {1, 2, 4}
//Output : 3 5
//
//Input : arr[] = {1, 2}
//Output : 3 4
//Input : 1 3 5 6, n = 6
//Sum of missing integers = n*(n+1)/2 - (1+3+5+6) = 6.
//Average of missing integers = 6/2 = 3.
//Sum of array elements less than or equal to average = 1 + 3 = 4
//Sum of natural numbers from 1 to avg = avg*(avg + 1)/2
//                                     = 3*4/2 = 6
//First missing number = 6 - 4 = 2
//Second missing number = Sum of missing integers-First missing number
//Second missing number = 6-2= 4
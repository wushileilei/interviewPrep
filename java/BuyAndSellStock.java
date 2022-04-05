//You are given an array prices where prices[i] is the price of a given stock on the ith day.
//
//You want to maximize your profit by choosing a single day to buy one stock and choosing a different 
//day in the future to sell that stock.
//
//Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
//class Solution {
//    public int maxProfit(int[] prices) {
//        int min = Integer.MAX_VALUE;
//        int max_profit = 0;
//        
//        for (int i = 0; i < prices.length; i++) {
//            if (prices[i] < min) {
//                min = prices[i];
//            }
//            else  if (prices[i] - min > max_profit){
//                max_profit = prices[i] - min;
//            }
//        }
//        
//        return max_profit;
//    }
//}
//You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
//
//On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the 
//stock at any time. However, you can buy it then immediately sell it on the same day.
//
//Find and return the maximum profit you can achieve.
//因为交易次数不受限，如果可以把所有的上坡全部收集到，一定是利益最大化的
//public int maxProfit(int[] arr) {
//        if (arr == null || arr.length <= 1) return 0;
//
//        int ans = 0;
//        for (int i = 1; i < arr.length; i++) {
//            if (arr[i] > arr[i-1]) {  // 卖出有利可图
//                ans += (arr[i] - arr[i-1]);
//            }
//        }
//
//        return ans;
//    }
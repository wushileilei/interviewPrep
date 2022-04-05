//这道题给了一堆某人欠某人多少钱这样的账单，问我们经过优化后最少还剩几个。其实就相当于一堆人出去玩，某些人可能帮另一些人垫付过花费，
//最后结算总花费的时候可能你欠着别人的钱，其他人可能也欠你的欠。我们需要找出简单的方法把所有欠账都还清就行了。
//方法：用银行结算的思路，首先计算出每个人的最后盈亏，然后用随机的算法找到比较可能的最小值。
//how to know which set of pairs give minimum number of transactions?'. One solution idea is just, 
//brute force through all pairs and just take the minimum number of transactions. 
//Another idea is just take some random combinations of pairs and take the minimum number of trans so far.
import java.util.*;
class Solution {
    public int minTransfers(int[][] transactions) {
        if(transactions == null || transactions.length == 0) return 0;
        Map<Integer, Integer> acc = new HashMap<>();
        for(int i = 0;i<transactions.length;i++){
            int id1 = transactions[i][0];
            int id2 = transactions[i][1];
            int m = transactions[i][2];
            acc.put(id1, acc.getOrDefault(id1, 0)-m);
            acc.put(id2, acc.getOrDefault(id2, 0)+m);
        }
        List<Integer> negs = new ArrayList<>();
        List<Integer> poss = new ArrayList<>();
        for(Integer key:acc.keySet()){
            int m = acc.get(key);
            if(m == 0) continue;
            if(m<0) negs.add(-m);
            else poss.add(m);
        }
        int ans = Integer.MAX_VALUE;
        Stack<Integer> stNeg = new Stack<>(), stPos = new Stack<>();
        for(int i =0;i<500;i++){
            for(Integer num:negs) stNeg.push(num);
            for(Integer num:poss) stPos.push(num);
            int cur = 0;
            while(!stNeg.isEmpty()){
                int n = stNeg.pop();
                int p = stPos.pop();
                cur++;
                if(n == p) continue;
                if(n>p){
                    stNeg.push(n-p);
                } else {
                    stPos.push(p-n);
                }
            }
            ans = Math.min(ans, cur);
            Collections.shuffle(negs);
            Collections.shuffle(poss);
        }
        return ans;
    }
}
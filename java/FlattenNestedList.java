/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
// a)间隔打印元素，不用写成迭代器，输入除了integer和list还可能有string。even or odd, maybe need convert to uppercase.
// b)多层list嵌套的情况
// c)要求把超过一位的数字拆成单个digit
// d)打印在拆数字之前，如果遇到连续的数字（值相差1），就求和直到遇到不连续的数字或别的输入，再把所得的和按前一问的要求拆分；如果遇到连
// 数字不在同一层list的情况怎么处理（e.g. 1,5,8,(9, 11), 2里的8和9）
// for print: pust in stack, when need it flatten. make sure 
// top of the stack always be be next element

//public class NestedIterator implements Iterator<Integer> {
// 普通打印
//     // stack for elements
//     Deque<NestedInteger> stack;
//     // initial worse O(n) usually less 
//     public NestedIterator(List<NestedInteger> nestedList) {
//         stack = new LinkedList<>();
//         for (int i = nestedList.size() - 1; i >= 0; i--) {
//             stack.push(nestedList.get(i));
//         }
//     }

//     @Override
//     public Integer next() {
//         return stack.poll().getInteger();
//     }

//     @Override
//     public boolean hasNext() {
//         if(stack.isEmpty()) {
//             return false;
//         }
//         if (stack.peek().isInteger()) {
//             return true;
//         }
//         else {
//             flatten();
//         }
//         return hasNext();
//     }
    
//     private void flatten() {
//         List<NestedInteger> list = stack.poll().getList();
//         for (int i = list.size() - 1; i >= 0; i--) 
//             stack.push(list.get(i));
        
//     }
// // 间隔打印
// public class NestedIterator implements Iterator<Integer> {
//     // stack for elements
//     private Deque<NestedInteger> stack;
//     // 1 print 0 don't print
//     private int flag = 1;
//     // initial worse O(n) usually less 
//     public NestedIterator(List<NestedInteger> nestedList) {
//         stack = new LinkedList<>();
//         for (int i = nestedList.size() - 1; i >= 0; i--) {
//             stack.push(nestedList.get(i));
//         }
//     }

//     @Override
//     public Integer next() {
//         return stack.poll().getInteger();
//     }
//     // make sure stack top is next to print
//     @Override
//     public boolean hasNext() {
//         if(stack.isEmpty()) return false;
        
//         if (stack.peek().isInteger()) {
//             if (flag == 1) {
//                 flag = 0;
//                 return true;
//             }
        
//             if(flag == 0) {
//                 flag = 1;
//                 stack.pop();
//                 if (stack.isEmpty()) return false;
//                 return hasNext();
//              }
//         }
//         // else  
//         flatten();
//         return hasNext();
//     }
//     private void flatten() {
//         List<NestedInteger> list = stack.poll().getList();
//         for (int i = list.size() - 1; i >= 0; i--) 
//             stack.push(list.get(i));
        
//     }
// }

// // 拆分 不间隔打印 123  ----> 1 2 3 when, num > 9 then seperate
// public class NestedIterator implements Iterator<Integer> {
//     // stack for elements
//     Deque<NestedInteger> stack;
//     // initial worse O(n) usually less 
//     public NestedIterator(List<NestedInteger> nestedList) {
//         stack = new LinkedList<>();
//         for (int i = nestedList.size() - 1; i >= 0; i--) {
//             stack.push(nestedList.get(i));
//         }
//     }

//     @Override
//     public Integer next() {
//         numsToNum();
//         return stack.pop().getInteger();
//     }
//     // 123 -->3. 2  1 in stack
//     private void numsToNum() {
//         // int = 0
//         int curInt = stack.peek().getInteger();
//         if (curInt == 0) {
//             return;
//         }
//         stack.pop();
//         // 123 / 10 = 12 ....3.  12 /10 = 1......2.  1 /10 = 0....1
//         while (curInt != 0) {
//             stack.push(new NestedInteger(curInt % 10));
//             curInt /= 10;
//         }
        
//         return;
//     }

//     @Override
//     public boolean hasNext() {
//         if(stack.isEmpty()) {
//             return false;
//         }
//         if (stack.peek().isInteger()) {
//             return true;
//         }
//         else {
//             flatten();
//         }
//         return hasNext();
//     }
    
//     private void flatten() {
        // while(!stack.peek().isInteger()) {
        //     List<NestedInteger> list = stack.poll().getList();
        //      for (int i = list.size() - 1; i >= 0; i--) 
        //         stack.push(list.get(i));
        // }
//     }
// }
// 间隔拆分打印
// public class NestedIterator implements Iterator<Integer> {
//     // stack for elements
//     private Deque<NestedInteger> stack;
//     // 1 print 0 don't print
//     private int flag = 1;
//     // initial worse O(n) usually less 
//     public NestedIterator(List<NestedInteger> nestedList) {
//         stack = new LinkedList<>();
//         for (int i = nestedList.size() - 1; i >= 0; i--) {
//             stack.push(nestedList.get(i));
//         }
//     }

//     @Override
//     public Integer next() {
//         return stack.poll().getInteger();
//     }
//     // 123----> 3. 2. 1 in stack
//     private void numsToNum() {
//         // int = 0
//         int curInt = stack.peek().getInteger();
//         if (curInt == 0) {
//             return;
//         }
//         stack.pop();
//         // 123 / 10 = 12 ....3.  12 /10 = 1......2.  1 /10 = 0....1
//         while (curInt != 0) {
//             stack.push(new NestedInteger(curInt % 10));
//             curInt /= 10;
//         }
        
//         return;
//     }
//     // make sure stack top is next to print
//     @Override
//     public boolean hasNext() {
//         if(stack.isEmpty()) return false;
        
//         if (stack.peek().isInteger()) {
//             numsToNum();
//             if (flag == 1) {
//                 flag = 0;
//                 return true;
//             }
        
//             if(flag == 0) {
//                 flag = 1;
//                 stack.pop();
//                 if (stack.isEmpty()) return false;
//                 return hasNext();
//              }
//         }
//         // else  
//         flatten();
//         return hasNext();
//     }
//     private boolean printOrNot() {
//         if (flag == 1) {
//             flag = 0;
//             return true;
//         }
        
//         if(flag == 0) {
//             stack.pop();
//             if (stack.isEmpty()) return false;
//             flag = 0;
//             return hasNext();
//         }
//         return false;
//     }
    
//     private void flatten() {
//         List<NestedInteger> list = stack.poll().getList();
//         for (int i = list.size() - 1; i >= 0; i--) 
//             stack.push(list.get(i));
        
//     }
// }
//// 间隔拆分求和打印 （e.g. 1,5,8,(9, 11), 2里的8和9）
//public class NestedIterator implements Iterator<Integer> {
//    // stack for elements
//    private Deque<NestedInteger> stack;
//    // 1 print 0 don't print
//    private int flag = 1;
//    // initial worse O(n) usually less 
//    public NestedIterator(List<NestedInteger> nestedList) {
//        stack = new LinkedList<>();
//        for (int i = nestedList.size() - 1; i >= 0; i--) {
//            stack.push(nestedList.get(i));
//        }
//    }
//
//    @Override
//    public Integer next() {
//        return stack.poll().getInteger();
//    }
//    // 123----> 3. 2. 1 in stack
//    private void numsToNum() {
//        // int = 0
//        int curInt = stack.peek().getInteger();
//        if (curInt == 0) {
//            return;
//        }
//        stack.pop();
//        // 123 / 10 = 12 ....3.  12 /10 = 1......2.  1 /10 = 0....1
//        while (curInt != 0) {
//            stack.push(new NestedInteger(curInt % 10));
//            curInt /= 10;
//        }
//        
//        return;
//    }
//    // 7 8 9 11
//    private void addCon() {
//        ArrayList<Integer> conse = new ArrayList<>();
//        int cur, pre;
//        cur = stack.poll().getInteger();
//        nextInt();
//        if(stack.isEmpty()){
//            stack.push(new NestedInteger(cur));
//            return;
//        } 
//        // consecutive
//        while (!stack.isEmpty() && Math.abs(stack.peek().getInteger() - cur) == 1) {
//            pre = cur;
//            cur = stack.poll().getInteger();
//            conse.add(pre);
			// nextInt();
//            if (stack.isEmpty()) break;
//        }
//        conse.add(cur);
//        int sum = 0;
//        for (Integer num : conse) {
//            sum += num;
//        }
//        stack.push(new NestedInteger(sum));
//    }
//    private void nextInt() {
//        if(stack.isEmpty()) {
//            return;
//        }
//        while (!stack.peek().isInteger()) {
//            flatten();
//        }
//    }
//    // make sure stack top is next to print
//    @Override
//    public boolean hasNext() {
//        if(stack.isEmpty()) return false;
//        
//        if (stack.peek().isInteger()) {
//            // deal consecutive
//            addCon();
//            // split
//            numsToNum();
//            if (flag == 1) {
//                flag = 0;
//                return true;
//            }
//        
//            if(flag == 0) {
//                flag = 1;
//                stack.pop();
//                if (stack.isEmpty()) return false;
//                return hasNext();
//             }
//        }
//        // else  
//        flatten();
//        return hasNext();
//    }
//
//    
//    private void flatten() {
//        List<NestedInteger> list = stack.poll().getList();
//        for (int i = list.size() - 1; i >= 0; i--) 
//            stack.push(list.get(i));
//        
//    }
//}

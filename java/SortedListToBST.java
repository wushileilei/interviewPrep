/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {
    /*
        list is sorted
                            midnode
        maketree(0, mid -1)         makeTree(mid + 1, len - 1)
        ......
            
        leftmost listnode head
        down to up
        time O(n) space O(logn)
    */  
    
    private ListNode globalHead;
    
    public TreeNode sortedListToBST(ListNode head) {
        // count the len
        globalHead = head;
        int len = 0;
        ListNode node = head;
        while (node != null) {
            len++;
            node = node.next;
        }
        
        return makeTree(0, len - 1);
    }
    
    private TreeNode makeTree(int left, int right) {
        if (left > right) return null;
        
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode();
        root.left = makeTree(left, mid - 1);
        root.val = globalHead.val;
        globalHead = globalHead.next;
        root.right = makeTree(mid + 1, right);
        return root;
    }
}
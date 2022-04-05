// time O(n) space O(logn)
// recursion mid is the root 
class Solution {
  public TreeNode sortedArrayToBST(int[] nums) {
    return buildBST(nums, 0, nums.length - 1);
  }
  
  private TreeNode buildBST(int[] nums, int l, int r) {
	// exit
    if (l > r) return null;
    int m = l + (r - l) / 2;
    TreeNode root = new TreeNode(nums[m]);
    root.left = buildBST(nums, l, m - 1);
    root.right = buildBST(nums, m + 1, r);
    return root;
  }
}
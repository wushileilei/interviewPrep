// Tree
class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
          this.left = left;
         this.right = right;
	}
 }
 
/*
 * 返回二叉树任意一个local maximal的节点（儿子节点和父节点都要大）
 *  always go to the bigger side 
 */
// time O(logn) no extra space
public class LocalMaxTree {

    public TreeNode localMaxNode(TreeNode root) {
        while (root != null){
            int left = Integer.MIN_VALUE, right = Integer.MIN_VALUE;
            if (root.left != null) {
                left = root.left.val;
            }
            if (root.right != null) {
                right = root.right.val;
            }
            if (root.val > left && root.val > right) {
                return root;
            }
            else {
                root = left > right ? root.left : root.right;
            }
        }
        return root;   
    }

    public static void main(String[] args) {
    	System.out.println("test");
    }
}
//1D array

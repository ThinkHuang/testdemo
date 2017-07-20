/**
 * 
 */
package com.huang.leetcode;

import org.junit.Test;

/**
 * @author dell
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 *       1 
	   /   \
	  2     2
	 /\     /\
	3  4   4  3 
   / \ /\ / \ /\
  5  67 8 8 76  5
	But the following [1,2,2,null,3,null,3] is not:
		1
	   / \
	  2   2
	   \   \
	   3    3
 */
public class IsSymmetric {
	
	private TreeNode p = null;
	
	@Test
	public void test(){
		initTreeNode();
		System.out.println(isSymmetric(p));
	}
	
	private void initTreeNode() {
		p = new TreeNode(1);
		p.left = new TreeNode(2);
		p.left.left = new TreeNode(3);
		p.left.left.right = new TreeNode(4);
		p.right = new TreeNode(2);
		p.right.right = new TreeNode(3);
		p.right.right.left = new TreeNode(5);
	}
	
	
	 public boolean isSymmetric(TreeNode root) {
		 if(root == null) return true;
		 if(root.right == null && root.left == null) return true;
		 if(root.right == null || root.left == null) return false;
		 TreeNode left = root.left;
		 TreeNode right = root.right;
		 if(left.val == right.val){
			 return isSymmetricTree(left,right);
		 }
		 return false;
	 }
	 
	 public boolean isSymmetricTree(TreeNode p, TreeNode q) {
		    if(p == null && q == null)return true;
		    if(p == null || q == null) return false;
			if(p.val == q.val)
				return isSymmetricTree(p.left, q.right) && isSymmetricTree(p.right, q.left);
			return false;
	    }
}

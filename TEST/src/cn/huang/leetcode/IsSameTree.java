package com.huang.leetcode;

import org.junit.Test;

/**
 * @author dell
 *	Given two binary trees, write a function to check if they are equal or not.
 *	Two binary trees are considered equal if they are structurally identical and the nodes have the same value. 
 *  树节点之间包含空的节点
 *  [10,5,15]
 *  [10,5,null,null,15]   ==> true
 */
public class IsSameTree {
	
	private TreeNode p = null;
	private TreeNode q = null;
	
	@Test
	public void test(){
		initTreeNode();
		System.out.println(isSameTree(p, q));
	}
	
	
	private void initTreeNode() {
		p = new TreeNode(3);
		p.left = new TreeNode(1);
		p.right = new TreeNode(5);
		p.right.right = new TreeNode(5);
		
		q = new TreeNode(1);
		q.left = new TreeNode(1);
		q.right = new TreeNode(5);
	}


	public boolean isSameTree(TreeNode p, TreeNode q) {
	    if(p == null && q == null)return true;
	    if(p == null || q == null) return false;
		if(p.val == q.val)
			return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
		return false;
    }

	
	/**
	 * 下面是其延伸品
	 */

	/**
	 * @param p2_left
	 * @param q2_left
	 */
	private boolean isSameLeftNode(TreeNode p2_left, TreeNode q2_left) {
		if((p2_left == null && q2_left != null) || (p2_left != null && q2_left == null))return false;
		else if(p2_left == null && q2_left == null) return true;
		else{
			if(p2_left.val != q2_left.val) return false;
			else{
				if(p2_left.left != null && q2_left.left != null)
					return isSameLeftNode(p2_left.left, q2_left.left);
				else return true;
			}
		}
	}


	/**
	 * @param p2
	 * @param q2
	 * @return
	 */
	private boolean isSameRightNode(TreeNode p2, TreeNode q2) {
		if((p2 == null && q2 != null) || (p2 != null && q2 == null))return false;
		else if(p2 == null && q2 == null) return true;
		else{
			if(p2.val != q2.val) return false;
			else{
				if(p2.right != null && q2.right != null)
					return isSameRightNode(p2.right, q2.right);
				else return true;
			}
		}
	}
}

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
}

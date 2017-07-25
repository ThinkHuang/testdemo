/**
 * 
 */
package com.huang.leetcode;

import org.junit.Test;

/**
 * @author dell
 *
 */
public class MaxDepth {
	
	private TreeNode p = null;
	int depth = 0;
	
	@Test
	public void test(){
		initTreeNode();
		System.out.println(maxDepth(p));
	}
	
	
	public int maxDepth(TreeNode root) {
		 if(root == null){
	            return 0;
	        }
	        return 1+Math.max(maxDepth(root.left),maxDepth(root.right));
    }
	
	/**
	 * 用来判断当前节点是否包含子节点
	 * @param p
	 * @param q
	 * @return
	 */
	 public boolean hasChildren(TreeNode p, TreeNode q) {
		    if(p == null && q == null)return false;
		    if(p != null && q != null) {
		    	depth++;
		    	return hasChildren(p.left,p.right) || hasChildren(q.left,q.right);
		    }
		    else{
		    	if(p != null){
		        	depth++;
		        	return hasChildren(p.left,p.right);
		        }else if(q != null){
		        	depth++;
		        	return hasChildren(q.left,q.right);
		        }else{
		        	return false;
		        }
		    }
	}
	 
	private void initTreeNode() {
		p = new TreeNode(1);
		p.left = new TreeNode(2);
		p.left.left = new TreeNode(3);
		p.left.right = new TreeNode(4);
		p.left.left.left = new TreeNode(5);
		p.left.left.right = new TreeNode(6);
		p.left.right.left = new TreeNode(5);
		p.left.right.right = new TreeNode(6);
		p.right = new TreeNode(7);
		
		p.right.left = new TreeNode(8);
		p.right.right = new TreeNode(9);
		
		p.right.left.left = new TreeNode(10);
		p.right.left.right = new TreeNode(11);
		p.right.right.left = new TreeNode(10);
		p.right.right.right = new TreeNode(11);
		
		p.right.left.left.left = new TreeNode(12);
		p.right.left.left.right = new TreeNode(13);
		p.right.left.right.left = new TreeNode(12);
		p.right.left.right.right = new TreeNode(13);
		p.right.right.left.left = new TreeNode(12);
		p.right.right.left.right = new TreeNode(13);
		p.right.right.right.left = new TreeNode(12);
		p.right.right.right.right = new TreeNode(13);
		p.right.right.right.right.left = new TreeNode(14);
		p.right.right.right.right.right = new TreeNode(15);
		p.right.right.right.right.right.right = new TreeNode(16);
	}
}

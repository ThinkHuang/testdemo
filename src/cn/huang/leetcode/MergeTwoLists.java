package cn.huang.leetcode;

import org.junit.Test;

/**
 * Merge two sorted linked lists and return it as a new list. 
 * The new list should be made by splicing together the nodes of the first two lists
 * @author huangyejun
 * 扩展：去除两个ListNode中的重复节点
 * 
 * 采用递归完成，确实很精彩
 * 总结：递归的条件是，重复做相同的动作
 * 
 * 缺点：如果该ListNode中存在相同的元素是无法区分出来的，
 * 但是如果是这样的话，在前面先进行循环盘点，使其不再出现重复元素
 */
public class MergeTwoLists {
	private ListNode ln1 = null;
	private ListNode ln2 = null;
	
	@Test
	public void test(){
		initListNode();
		//初始化失败
		if(ln1 == null || ln2 == null) return;
		print(mergeTwoLists(ln1, ln2));
	}
	
	private void print(ListNode mergeTwoLists) {
		while(mergeTwoLists != null){
			System.out.println(mergeTwoLists.val);
			mergeTwoLists = mergeTwoLists.next;
		}
	}

	private void initListNode() {
		ln1 = new ListNode(4);
		ListNode subLn1 = new ListNode(13);
		ln1.next = subLn1;
		ListNode subLn2 = new ListNode(25);
		subLn1.next = subLn2;
		ListNode subLn3 = new ListNode(34);
		subLn2.next = subLn3;
		ListNode subLn4 = new ListNode(56);
		subLn3.next = subLn4;
		ListNode subLn5 = new ListNode(89);
		subLn4.next = subLn5;
		
		ln2 = new ListNode(4);
		ListNode subLn6 = new ListNode(8);
		ln2.next = subLn6;
		ListNode subLn7 = new ListNode(9);
		subLn6.next = subLn7;
		ListNode subLn8 = new ListNode(13);
		subLn7.next = subLn8;
		ListNode subLn9 = new ListNode(23);
		subLn8.next = subLn9;
		ListNode subLn10 = new ListNode(89);
		subLn9.next = subLn10;
	}

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if(l1 == null) return l2;
		if(l2 == null) return l1;
		if(l1.val < l2.val){
			/**
			 * 这里有一个很巧妙的点：就是l1永远指向的是自己，而l1.next指向的永远是它的下一个元素，那么这是构成能够使用递归的条件
			 */
			l1.next = mergeTwoLists(l1.next, l2);
			return l1;
		} else if( l1.val == l2.val){//这是扩展，去除里面重复的元素
			l2.next = mergeTwoLists(l1.next, l2.next);
			return l2;
		}
		else{
			l2.next = mergeTwoLists(l1, l2.next);
			return l2;
		}
    }
}


class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
}
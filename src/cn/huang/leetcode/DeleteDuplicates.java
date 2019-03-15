/**
 * 
 */
package com.huang.leetcode;

import org.junit.Test;

/**
 * @author dell
 *
 */
public class DeleteDuplicates {
	private ListNode ln1 = null;
	@Test
	public void test(){
		initListNode();
		//初始化失败
		if(ln1 == null) return;
		print(deleteDuplicates(ln1));
	}
	
	
	public ListNode deleteDuplicates(ListNode head) {
		if(head == null) return head;
        if(head.next == null)return head;
        ListNode temp = head;
        while(head != null){
        	if(head.next == null) break;
            ListNode next = head.next;
            if(head.val == next.val){
                head.next = next.next;
            }else{
            	head = head.next;
            }
        }
        return temp;
    }
	
	
	private void print(ListNode mergeTwoLists) {
		while(mergeTwoLists != null){
			System.out.println(mergeTwoLists.val);
			mergeTwoLists = mergeTwoLists.next;
		}
	}

	private void initListNode() {
		ln1 = new ListNode(8);
		ListNode subLn1 = new ListNode(8);
		ln1.next = subLn1;
		ListNode subLn2 = new ListNode(8);
		subLn1.next = subLn2;
		ListNode subLn3 = new ListNode(34);
		subLn2.next = subLn3;
		ListNode subLn4 = new ListNode(56);
		subLn3.next = subLn4;
		ListNode subLn5 = new ListNode(56);
		subLn4.next = subLn5;
		ListNode subLn6 = new ListNode(89);
		subLn4.next = subLn6;
		ListNode subLn7 = new ListNode(89);
		subLn4.next = subLn7;
		ListNode subLn8 = new ListNode(89);
		subLn4.next = subLn8;
	}
	
	
	class ListNode {
	    int val;
	    ListNode next;
	    ListNode(int x) { val = x; }
		 
	}
	
}



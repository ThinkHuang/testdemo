/**
 * 
 */
package com.huang.leetcode;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * @author dell
 * Given a linked list, determine if it has a cycle in it.
 * Follow up:
 * Can you solve it without using extra space? 
 */
public class HasCycle {
	
	private ListNode ln1 = null;
	
	@Test
	public void test(){
		initListNode();
		System.out.println(hasCycle2(ln1));
	}
	
	/**
	 * 既然会形成环的话，那么其尾指针必然指向其中的一个节点
	 * 判断是环内的节点还是环外的节点？怎么标记这个节点已经被遍历过了？
	 * 怎么判断一个节点是不是尾节点？
	 * first style:采用跑步者和追赶者的思想，如果存在环，那么在追赶者比跑步者快的基础上，追赶者一定会追上跑步者
	 * @param head
	 * @return
	 */
	public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) return false;
        ListNode worker = head;
        ListNode runner = head.next;
        while(worker.next != null && runner.next != null){
        	worker = worker.next;
        	runner = runner.next.next;
        	if(runner == worker) return true;
        }
        return false;
    }
	
	/**
	 * 由于要重新开辟信息的地址空间，所以，不太满足上述条件，但是依然是一种解题的思路
	 * 采用hash table填充Linked List中的数据。利用set表的“排他性”确认是否有环
	 * @param head
	 * @return
	 */
	public boolean hasCycle2(ListNode head){
		if(head == null) return false;
		Set<ListNode> set = new HashSet<ListNode>();
		while(head.next != null){
			set.add(head);
			head = head.next;
			if(set.contains(head)) return true;
		}
		return false;
	}
	
	
	private void initListNode() {//形成了循环链表
		ln1 = new ListNode(4);
		ListNode subLn1 = new ListNode(13);
		ln1.next = subLn1;
		ListNode subLn2 = new ListNode(25);
		subLn1.next = subLn2;
		ListNode subLn3 = new ListNode(13);
		subLn2.next = subLn3;
		ListNode subLn4 = new ListNode(56);
		subLn3.next = subLn4;
		ListNode subLn5 = new ListNode(89);
		subLn4.next = subLn5;
		subLn5.next = subLn2;
		
	}
	
}

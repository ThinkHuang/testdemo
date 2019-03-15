/**
 * 
 */
package leetcode;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * @author dell
 * Write a program to find the node at which the intersection of two singly linked lists begins.

For example, the following two linked lists:

A:          a1 → a2
                   	↘
                     c1 → c2 → c3
                   	↗            
B:     b1 → b2 → b3

begin to intersect at node c1.
前提条件是：A、B两个节点不会再其他位置出现重复
 */
public class GetIntersectionNode {
	
	private ListNode ln1 = null;
	private ListNode ln2 = null;
	
	@Test
	public void test(){
		initListNode();
		ListNode temp = getIntersectionNode(ln1,ln2);
		System.out.println(temp.val);
	}
	
	
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;
//        while(headA.next != null) headA = headA.next;//headA和headB不是双向链表，所以，不能采用这种做法
//        while(headB.next != null) headB = headB.next;
		Set<ListNode> set = new HashSet<ListNode>();
		while(headA != null){
			set.add(headA);
			headA = headA.next;
		}
		while(headB != null){
			if(set.contains(headB)) return headB;
			headB = headB.next;
		}
		return null;
    }
	
	private void initListNode() {
		ln1 = new ListNode(4);
		ListNode subLn1 = new ListNode(33);
		ln1.next = subLn1;
		ListNode subLn2 = new ListNode(25);
		subLn1.next = subLn2;
		ListNode subLn3 = new ListNode(13);
		subLn2.next = subLn3;
		ListNode subLn4 = new ListNode(23);
		subLn3.next = subLn4;
		ListNode subLn5 = new ListNode(89);
		subLn4.next = subLn5;
		
		ln2 = new ListNode(4);
		ListNode subLn6 = new ListNode(8);
		ln2.next = subLn6;
		ListNode subLn7 = new ListNode(9);
		subLn6.next = subLn7;
		subLn7.next = subLn3;
		subLn7.next.next = subLn4;
		subLn7.next.next.next = subLn5;
	}
	
}

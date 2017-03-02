package cn.huang.test;

import java.util.EmptyStackException;

public class Stack {
	/**
	 * ע������Ϊʲô˵������ڴ���������⣬����Ϊ������elements��Ϊ�洢������
	 * ������û��
	 */
	private Object[] elements = new Object[10];
	private int size = 0;
	public void push(Object e){
		ensureCapacity(elements);
		elements[size++] = e;
	}
	
	public Object pop(){
		if(size == 0)
			throw new EmptyStackException();
		return elements[size--];
	}
	
	private void ensureCapacity(Object[] elements) {
		// TODO Auto-generated method stub
		if(elements.length == size){
			Object newObj[] = elements;
			elements = new Object[2*newObj.length + 1];
			System.arraycopy(newObj, 0, elements, 0, size);
		}
	}
}

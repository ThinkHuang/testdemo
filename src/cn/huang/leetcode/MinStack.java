/**
 * 
 */
package leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author dell
 *
 */
public class MinStack {
	
	private List<Integer> sl;
	private Integer minValue;
	
	/** initialize your data structure here. */
    public MinStack() {
        sl = new ArrayList<Integer>();
    }
    
    public void push(int x) {
        sl.add(x);
        setMinValue();
    }
    
	private void setMinValue() {
		if(sl.size() < 1) {
			minValue = Integer.MIN_VALUE;
			return;
		}
		Iterator<Integer> it = sl.iterator();
		minValue = sl.get(0);
		while(it.hasNext()){
			Integer temp = it.next();
			if(temp < minValue)
				minValue = temp;
		}
	}

	public void pop() {
    	if(sl.size() < 1)
    		throw new RuntimeException("MinStack has no elements to pop!");
        sl.get(sl.size() - 1);
        sl.remove(sl.size() - 1);
        setMinValue();
    }
    
    public int top() {
    	if(sl.size() < 1)
    		throw new RuntimeException("MinStack is empty!");
        return sl.get(sl.size() - 1);
    }
    
    public int getMin() {
    	if(sl.size() < 1)
    		throw new RuntimeException("MinStack is empty!");
        return minValue;
    }
    
    public static void main(String[] args) {
    	MinStack stack = new MinStack();
    	stack.push(1);
    	stack.push(-8);
    	stack.push(-4);
    	
    	stack.pop();
    	System.out.println(stack.getMin());
    	System.out.println(stack.top());
	}
}

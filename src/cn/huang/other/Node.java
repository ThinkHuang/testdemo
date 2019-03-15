package other;

public class Node {
	public int value;
	private Node left;
	private Node right;
	
	public void store(int value){
		if(value < this.value){
			if(left == null){
				left = new Node();
				left.value = value;
			}else{
				left.store(value);
			}
		}
		else if(value > this.value){
			if(right == null){
				right = new Node();
				right.value = value;
			}else{
				right.store(value);
			}
		}
	}
	public boolean find(int value){
		System.out.println("happen:" + value);
		if(value == this.value){
			return true;
		}
		else if(value > this.value){
			if(right == null){
				return false;
			}
			return right.find(value);
		}
		else{
			if(left == null){
				return false;
			}
			return left.find(value);
		}
	}
	public void preList(){
		System.out.println(this.value + " ");
		if(left != null)left.preList();
		if(right != null) right.preList();
	}
	
	public void middleList(){
		if(left != null) left.middleList();
		System.out.println(this.value + " ");
		if(right != null) right.middleList();
	}
	
	public void afterList(){
		if(left != null) left.afterList();
		if(right != null) right.afterList();
		System.out.println(this.value + " ");
	}
	
	public static void main(String[] args) {
		int[] data = new int[20];
		for(int i = 0; i < data.length ; i++){
			data[i] = (int)(Math.random()*100) + 1;
			System.out.println(data[i] + " ");
		}
		System.out.println();
		Node node = new Node();
		node.value = data[0];
		for(int i = 0; i < data.length ; i++){
			node.store(data[i]);
		}
		node.find(data[19]);
		System.out.println();
		node.preList();
		System.out.println();
		node.middleList();
		System.out.println();
		node.afterList();
	}
}

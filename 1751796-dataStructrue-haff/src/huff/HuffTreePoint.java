package huff;

public class HuffTreePoint {
	private int parent;
	private int leftChild;
	private int rightChild;
	private char name;
	private String code;
	public HuffTreePoint() {
		name='?';
		parent=-1;
		leftChild=-1;
		rightChild=-1;
		code="";
	}
	public int getParent() {
		return parent;
	}
	public void changeParent(int parent) {
		this.parent=parent;
	}
	public int getLeftChild() {
		return leftChild;
	}
	public void changeLeftChild(int leftChild) {
		this.leftChild=leftChild;
	}
	public int getRightChild() {
		return rightChild;
	}
	public void changeRightChild(int rightChild) {
		this.rightChild=rightChild;
	}
	public char getName() {
		return name;
	}
	public void changeName(char name) {
		this.name=name;
	}
	public String getCode() {
		return this.code;
	}
	public void addCode(String add) {
		this.code=this.code+add;
	}
	@Override
	public String toString() {
		return "HuffTreePoint [parent=" + parent + ", leftChild=" + leftChild + ", rightChild=" + rightChild + ", name="
				+ name + ", code=" + code + "]";
	}
	
}

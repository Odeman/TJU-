package huff;

public class HuffPoint {
	private char name;
	private int num;
	private int place;
	private int section;
	public HuffPoint() {
		
	}
	public HuffPoint(char name, int num, int place, int section){
		this.name=name;
		this.num=num;
		this.place=place;
		this.section=section;
	}
	public void changeName(char name) {
		this.name=name;
	}
	public void changeNum(int num) {
		this.num=num;
	}
	public void changePlace(int place) {
		this.place=place;
	}
	public char getName() {
		return this.name;
	}
	public int getNum() {
		return this.num;
	}
	public int getPlace() {
		return this.place;
	}
	public int getSection() {
		return section;
	}
	public void setSection(int section) {
		this.section = section;
	}
	@Override
	public String toString() {
		return "HuffPoint [name=" + name + ", num=" + num + ", place=" + place + ", section=" + section + "]";
	}
	
}

package changTheWorld_pathfinding;

public class Point {
	private int x;
	private int y;
	private int F;
	private int G;
	private int H;
	private Point parent;
	public Point(int x,int y) {
		this.x=x;
		this.y=y;
	}
	public void changeXY(int x,int y) {
		this.x=x;
		this.y=y;
	}
	public int getX() {
    	return x;
    }
    public int getY() {
    	return y;
    }
    public void changeH(int H) {
		this.H=H;
	}
	public void calF() {
		this.F=this.G+this.H;
	}
	public int getF() {
		return this.F;
	}
	public void changeF(int F) {
		this.F=F;
	}
	public int getG() {
		return this.G;
	}
	public void changeG(int G) {
		this.G=G;
	}
	public Point getParent() {
		return this.parent;
	}
	public void changeParent(Point parentPoint) {
		this.parent=parentPoint;
	}
	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + ", parent=" + parent + "]";
	}
	
}

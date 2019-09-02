package changTheWorld_pathfinding;

import java.util.ArrayList;
import java.util.List;


public class PathFinding {
	private int[][] Points=new int[21][21];
	public static final int STEP=20;
	private ArrayList<Point> openList=new ArrayList<Point>();
	private ArrayList<Point> closeList=new ArrayList<Point>();
	private int startX,startY,endX,endY;
	private Point startPoint,endPoint;
	
	public PathFinding(int[][] myMap,int startX,int startY,int endX,int endY) {
		this.startX=startX;
		this.startY=startY;
		this.endX=endX;
		this.endY=endY;
		startPoint=new Point(startX,startY);
		endPoint=new Point(endX,endY);
		for(int i=0;i<20;i++) {
			for(int j=0;j<20;j++) {
				Points[i][j]=myMap[i][j];
			}
		}
	}
	
	public void ChangeXY(int startX,int startY,int endX,int endY) {
		this.startX=startX;
		this.startY=startY;
		this.endX=endX;
		this.endY=endY;
	}
	
	public void ChangeMap(int[][] myMap) {
		for(int i=0;i<20;i++) {
			for(int j=0;j<20;j++) {
				Points[i][j]=myMap[i][j];
			}
		}
	}
	
	public Point findFMinNodeInOpenList() {
		Point tempPoint=openList.get(0);
		for(Point node:openList) {
			if(node.getF() <tempPoint.getF()) {
				tempPoint=node;
			}
		}
		return tempPoint;
	}
	
	public boolean canReach(int x,int y) {
		if(x>=0 && x<Points.length && y>=0 && y< Points[0].length) {
			if(Points[x][y]==2||Points[x][y]==3) return true; 
			else return false;
		}
		return false;
	}
	
	public static boolean exists(List<Point> tempPoints,int x,int y) {
		for( Point n:tempPoints) {
			if(n.getX()==x && n.getY()==y) {
				return true;
			}
		}
		return false;
	}
	public static Point find(List<Point> tempPoints,Point pointWant) {
		for( Point n:tempPoints) {
			if(n.getX()==pointWant.getX() && n.getY()==pointWant.getY()) {
				return n;
			}
		}
		return null;
	}
	
	public ArrayList<Point> findNeighborPoints(Point currentPoint){
		ArrayList<Point> arrayList=new ArrayList<Point>();
		for(int moveX=-1;moveX<=1;moveX++) {
			for(int moveY=-1;moveY<=1;moveY++) {
				if(Math.abs(moveX)==Math.abs(moveY)) continue;
				if(canReach(currentPoint.getX()+moveX,currentPoint.getY()+moveY)&&!exists(closeList,currentPoint.getX()+moveX,currentPoint.getY()+moveY)) {
					arrayList.add(new Point(currentPoint.getX()+moveX,currentPoint.getY()+moveY));
				}
			}
		}
		return arrayList;
	}
	
	private int calG(Point start,Point point) {
		int G=STEP;
		int parentG=point.getParent()!=null?point.getParent().getG():0;
		return G+parentG;
	}
	
	private int calH(Point end,Point point) {
		int step=Math.abs(point.getX() - end.getX()) + Math.abs(point.getY() - end.getY()); 
		return step*STEP;
	}
	
	private void dealFoundPoint(Point tempStart,Point point) {
		int G=calG(tempStart,point);
		if(G<point.getG()) {
			point.changeParent(tempStart);
			point.changeG(G);
			point.calF();
		}
	}
	
	private void PointIsNotFound(Point tempStart,Point end,Point point) {
		point.changeParent(tempStart);
		point.changeG(calG(tempStart,point));
		point.changeH(calH(end,point));
		point.calF();
		openList.add(point);
	}
	
	public Point findPath(Point startPoint, Point endPoint) {
		openList.add(startPoint);
		while(openList.size()>0){
			//System.out.println("@..");
			Point currentPoint=findFMinNodeInOpenList();
			openList.remove(currentPoint);
			closeList.add(currentPoint);
			ArrayList<Point> neighborPoints=findNeighborPoints(currentPoint);
			for(Point pointTry:neighborPoints) {
				if(exists(openList,pointTry.getX(),pointTry.getY())) {
					dealFoundPoint(currentPoint,pointTry);
				}
				else {
					PointIsNotFound(currentPoint,endPoint,pointTry);
				}
			}
			if(find(openList,endPoint)!=null) {
				return find(openList,endPoint);
			}
		}
		return find(openList,endPoint);
	}	
	public ArrayList<Point> getThePath() {
		//System.out.println("???"+startX+" "+startY+" "+endX+" "+endY);
		startPoint.changeXY(startX, startY);
		endPoint.changeXY(endX, endY);
		///System.out.println("???"+startPoint.getX()+' '+startPoint.getY()+' '+endPoint.getX()+' '+endPoint.getY());
		Point parent=new PathFinding(Points,startX,startY,endX,endY).findPath(startPoint, endPoint);
		//System.out.println("???"+startPoint.getX()+' '+startPoint.getY()+' '+endPoint.getX()+' '+endPoint.getY());
        /*
		for(int i=0;i<Points.length;i++) {
			for(int j=0;j<Points[0].length;j++) {
				System.out.print(Points[i][j]+",");
			}
			System.out.println();
		}
		*/
		ArrayList<Point> arrayList=new ArrayList<Point>();
		while(parent!=null) {
			arrayList.add(new Point(parent.getX(),parent.getY()));
			parent=parent.getParent();
		}
		
		//if(parent!=null) System.out.println(parent.getX()+" "+parent.getY()); else System.out.println("???");
		
		for(int i=0;i<Points.length;i++) {
			for (int j = 0; j < Points[0].length; j++) {  
                if (exists(arrayList, j, i)) {  
                    System.out.print("$, ");  
                } 
                else {  
                    System.out.print(Points[j][i] + ", ");  
                }  
            }  
            System.out.println();  
		}
		
		return arrayList;
	}

}

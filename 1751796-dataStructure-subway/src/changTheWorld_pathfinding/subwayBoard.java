package changTheWorld_pathfinding;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

public class subwayBoard extends JPanel implements MouseListener{
	public static int MARGIN=20;
	public static int GRID_SPAN=35;
	public static int ROWS=20;
	public static int COLS=20;
	private int xPoint,yPoint;
	
	private Color chooseColor=Color.YELLOW;
	private int elementSum=0;
	private int railwaySum=0;
	Node[] elementList=new Node[(ROWS+1)*(COLS)+1];
	Node[] railwayList=new Node[(ROWS+1)*(COLS)+1];
	private int exchangeSum=0;
	private boolean[][] check=new boolean[30][30];//检查是否已经有物体摆放
	private int[][] map=new int[21][21];
	LittleBoy Jack=new LittleBoy(20,20,Color.CYAN);
	
	PathFinding MyPath=new PathFinding(map,Jack.getX(),Jack.getY(),Jack.getX(),Jack.getY());
	private ArrayList<Point> thePath=MyPath.getThePath();
	private String ansPath="";
	private int startX,startY,endX,endY;
	private boolean toBegin=false;
	private boolean toEnd=false;
	private boolean addSubway=false;
	private boolean addThings=false;
    private Image charactor;
	private Image things;
	private int railwayNum;
	
	
	public subwayBoard() {
		getImage();
		setBackground(new Color(183,253,106));
		for(int i=0;i<21;i++) {       //init check array
			for(int j=0;j<21;j++) {
				check[i][j]=false;
				map[i][j]=0;
			}
		}
		MyPath.ChangeMap(map);
		addMouseListener(this);
		addMouseMotionListener(new MouseMotionListener() {
			public void mouseMoved(MouseEvent e) {
			}
            public void mouseDragged(MouseEvent e) {
			}
		});                
	}
	public void getImage() {
		this.charactor=Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Images/chicken2.png"));
	}
	public void getThingsImage(Image temp) {
		this.things=temp;
	}
	public void setToBegin(boolean check) {
		this.toBegin=check;
	}
	public void setToEnd(boolean check) {
		this.toEnd=check;
	}
	public void changeColor(Color myColor) {
		this.chooseColor=myColor;
	}
	
	public boolean isAddSubway() {
		return addSubway;
	}
	public void setAddSubway(boolean addSubway) {
		this.addSubway = addSubway;
	}
	public boolean isAddThings() {
		return addThings;
	}
	public void setAddThings(boolean addThings) {
		this.addThings = addThings;
	}
	public int getRailwayNum() {
		return railwayNum;
	}
	public void setRailwayNum(int railwayNum) {
		this.railwayNum = railwayNum;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		for(int i=0;i<=ROWS;i++) {
			g.drawLine(MARGIN, MARGIN+i*GRID_SPAN, MARGIN+COLS*GRID_SPAN, MARGIN+i*GRID_SPAN);
		}
		for(int i=0;i<=COLS;i++) {
			g.drawLine(MARGIN+i*GRID_SPAN, MARGIN, MARGIN+i*GRID_SPAN, MARGIN+ROWS*GRID_SPAN);
		}
		for(int i=0;i<railwaySum;i++) {
			int xTemp=railwayList[i].getX();
			int yTemp=railwayList[i].getY();
			int xPlace=xTemp*GRID_SPAN+MARGIN;
			int yPlace=yTemp*GRID_SPAN+MARGIN;
			if(xTemp+1>=0&&yTemp>=0&&xTemp+1<=ROWS&&yTemp<=COLS) {
				if(map[xTemp+1][yTemp]==2) {
					g.drawLine(xPlace,yPlace,xPlace+GRID_SPAN,yPlace);
				}
			}
			if(xTemp-1>=0&&yTemp>=0&&xTemp-1<=ROWS&&yTemp<=COLS) {
				if(map[xTemp-1][yTemp]==2) {
					g.drawLine(xPlace,yPlace,xPlace-GRID_SPAN,yPlace);
				}
			}
			if(xTemp>=0&&yTemp+1>=0&&xTemp<=ROWS&&yTemp+1<=COLS) {
				if(map[xTemp][yTemp+1]==2) {
					g.drawLine(xPlace,yPlace,xPlace,yPlace+GRID_SPAN);
				}
			}
			if(xTemp>=0&&yTemp-1>=0&&xTemp<=ROWS&&yTemp-1<=COLS) {
				if(map[xTemp][yTemp-1]==2) {
					g.drawLine(xPlace,yPlace,xPlace,yPlace-GRID_SPAN);
				}
			}
			
			g.setColor(railwayList[i].getColor());
			g.fillRect(xPlace-Node.DIAM/4, yPlace-Node.DIAM/4,Node.DIAM/2,Node.DIAM/2);
			if(railwayList[i].getName()!=null&&railwayList[i].getName()!="") g.drawString(railwayList[i].getName(),xPlace-Node.DIAM/2, yPlace-Node.DIAM/2);
			//g.drawImage(elementList[i].getImage(),xPlace-elementList[i].DIAM*2/3,yPlace-elementList[i].DIAM*2/3,elementList[i].DIAM*4/3,elementList[i].DIAM*4/3,this);
		}
		for(int i=0;i<elementSum;i++) {
			int xPlace=elementList[i].getX()*GRID_SPAN+MARGIN;
			int yPlace=elementList[i].getY()*GRID_SPAN+MARGIN;
			g.setColor(elementList[i].getColor());
			//g.fillRect(xPlace-Node.DIAM/2, yPlace-Node.DIAM/2,Node.DIAM,Node.DIAM);
			g.drawImage(elementList[i].getImage(),xPlace-elementList[i].DIAM*2/3,yPlace-elementList[i].DIAM*2/3,elementList[i].DIAM*4/3,elementList[i].DIAM*4/3,this);
		}
		
		g.setColor(Jack.getColor());
		//g.fillRect(Jack.getX()-Jack.DIAM/2, Jack.getY()-Jack.DIAM/2,Jack.DIAM,Jack.DIAM);
		Point nextPoint;
		int tempX, tempY;
		ansPath=" ";
		for(int i=thePath.size()-1;i>=0;i--) {
			nextPoint=thePath.get(i);
			if(map[nextPoint.getX()][nextPoint.getY()]==3) {
				for(int j=0;j<railwaySum;j++) {
					int xTemp=railwayList[j].getX();
					int yTemp=railwayList[j].getY();
					if(xTemp==nextPoint.getX()&&yTemp==nextPoint.getY()) {
						ansPath=ansPath+"\n\t  "+railwayList[j].getName();
					}
				}
			}
			tempX=(nextPoint.getX()*GRID_SPAN+MARGIN);
            tempY=(nextPoint.getY()*GRID_SPAN+MARGIN);
            g.drawImage(charactor,tempX-Jack.DIAM/2,tempY-Jack.DIAM/2,Jack.DIAM,Jack.DIAM,this);
		}
		if(ansPath==" "&&thePath.size()>0) {
			ansPath="无需换乘";
		}
		else if(thePath.size()==0) {
			ansPath="路径不存在";
		}
		else {
			ansPath="换乘路线：\n\t起点站"+ansPath+"\n\t终点站";
		}
		System.out.println(ansPath);
	}
	public void mousePressed(MouseEvent e) {
		if (toBegin==true) {
			xPoint=(e.getX()-MARGIN+GRID_SPAN/2)/GRID_SPAN;//鼠标点击位置
			yPoint=(e.getY()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
			startX=xPoint;
			startY=yPoint;
			
			toBegin=false;
			toEnd=true;
		}
		else if(toEnd==true) {
			xPoint=(e.getX()-MARGIN+GRID_SPAN/2)/GRID_SPAN;//鼠标点击位置
			yPoint=(e.getY()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
			endX=xPoint;
			endY=yPoint;
			
			MyPath.ChangeXY(startX, startY, endX, endY);
			this.thePath=MyPath.getThePath();
			Point nextPoint;
			boolean flag=false;
			int tempX,tempY;
			for(int i=0;i<thePath.size();i++) {
				nextPoint=thePath.get(i);
				System.out.println(nextPoint.toString());
			}
			repaint();
			this.update(this.getGraphics());
			toEnd=false;
		}
		else if(addThings==true){
			if(e.getButton()==e.BUTTON1) {
				xPoint=(e.getX()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
				yPoint=(e.getY()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
				if(check[xPoint][yPoint]==true)  return;
				if(xPoint<0||xPoint>ROWS||yPoint<0||yPoint>COLS)	return ;
				Node element=new Node(xPoint, yPoint, chooseColor);
				element.setImage(things);
				check[xPoint][yPoint]=true;
				map[xPoint][yPoint]=1;
				MyPath.ChangeMap(map);
				elementList[elementSum++]=element;
				repaint();
			}
			if(e.getButton()==e.BUTTON3) {
				xPoint=(e.getX()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
				yPoint=(e.getY()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
				if(xPoint<0||xPoint>ROWS||yPoint<0||yPoint>COLS)	return ;
				map[xPoint][yPoint]=0;
				MyPath.ChangeMap(map);
				cleanOne(xPoint,yPoint);
			}
		}
		else if(addSubway==true) {
			if(e.getButton()==e.BUTTON1) {
				xPoint=(e.getX()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
				yPoint=(e.getY()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
				if(map[xPoint][yPoint]!=0&&map[xPoint][yPoint]!=2)  return;
				if(xPoint<0||xPoint>ROWS||yPoint<0||yPoint>COLS)	return ;
				if(map[xPoint][yPoint]==0) {
					Node station=new Node(xPoint, yPoint, chooseColor);
					check[xPoint][yPoint]=true;
					map[xPoint][yPoint]=2;
					MyPath.ChangeMap(map);
					railwayList[railwaySum++]=station;
				}
				else if(map[xPoint][yPoint]==2) {
					Node station;
					for(int i=0;i<railwaySum;i++) {
						station=railwayList[i];
						if(station.getX()==xPoint&&station.getY()==yPoint) {
							railwayList[i].changeColor(Color.BLUE);
							map[xPoint][yPoint]=3;
							exchangeSum++;
							railwayList[i].setName("第"+exchangeSum+"换乘站");
						}
					}
				}
				repaint();
			}
			if(e.getButton()==e.BUTTON3) {
				xPoint=(e.getX()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
				yPoint=(e.getY()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
				if(xPoint<0||xPoint>ROWS||yPoint<0||yPoint>COLS)	return ;
				map[xPoint][yPoint]=0;
				MyPath.ChangeMap(map);
				cleanOne(xPoint,yPoint);
			}
		}
	}
	
	public static Point exists(List<Point> tempPoints,int x,int y) {
		for( Point n:tempPoints) {
			if(n.getX()==x && n.getY()==y) {
				return n;
			}
		}
		return null;
	}
	
	public void mouseDragged(MouseEvent e) {
	}
	public void mouseClicked(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e){
	}
	public void cleanAll() {
		System.out.println(thePath.size());
		for(int i=0;i<elementList.length;i++) {
			elementList[i]=null;
		}
		elementSum=0;
		for(int i=0;i<railwayList.length;i++) {
			railwayList[i]=null;
		}
		railwaySum=0;
		exchangeSum=0;
		for(int i=0;i<21;i++) {       //init check array
			for(int j=0;j<21;j++) {
				check[i][j]=false;
				map[i][j]=0;
			}
		}
		MyPath.ChangeMap(map);
		
		thePath.clear();
		repaint();
	}
	public void cleanOne(int xClean,int yClean) {
		if (check[xClean][yClean]==false) return;
		//检查障碍物
		boolean flag=false;
		for(int i=0;i<elementSum;i++) {
			if (elementList[i].getX()==xClean&&elementList[i].getY()==yClean) {
				flag=true;
				for(int j=i;j<elementList.length-1;j++) {
					elementList[j]=elementList[j+1];
				}
				elementList[elementList.length-1]=null;
				break;
			}
		}
		if(flag==true) elementSum--;
		//检查地铁
		if(flag==false) {
			for(int i=0;i<railwaySum;i++) {
				if (railwayList[i].getX()==xClean&&railwayList[i].getY()==yClean) {
					flag=true;
					for(int j=i;j<railwayList.length-1;j++) {
						railwayList[j]=railwayList[j+1];
					}
					railwayList[railwayList.length-1]=null;
					break;
				}
			}
			if(flag==true) railwaySum--;
		}
		check[xClean][yClean]=false;
		repaint();
	}
	public String getAnsPath() {
		return this.ansPath;
	}
    public Dimension getPreferredSize() {
    	return new Dimension(MARGIN*2+GRID_SPAN*COLS,MARGIN*2+GRID_SPAN*ROWS);
    }
}

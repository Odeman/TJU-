package changTheWorld_pathfinding;

import java.awt.*;  
import java.awt.event.MouseAdapter;  
import java.awt.event.MouseEvent;  
import java.awt.event.MouseListener;  
import javax.swing.*;  
import java.util.ArrayList;  
import java.util.Timer;  
import java.util.TimerTask;

public class LittleBoy extends JButton{
	private int x;
	private int y;
	private Color color;
	public static int DIAM=20;   //this is different
	public LittleBoy(int x, int y, Color color) {
		this.x=x;
		this.y=y;
		this.color=color;
	}
    public int getX() {
    	return x;
    }
    public int getY() {
    	return y;
    }
    public Color getColor() {
    	return color;
    }
	public void changeX(int x) {
		this.x+=x;
	}
	public void changeY(int y) {
		this.y+=y;
	}
	public void setXY(int x,int y) {
		this.x=x;
		this.y=y;
	}
	
}

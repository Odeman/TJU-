package changTheWorld_pathfinding;

import java.awt.*;  
import java.awt.event.MouseAdapter;  
import java.awt.event.MouseEvent;  
import java.awt.event.MouseListener;  
import javax.swing.*;  
import java.util.ArrayList;  
import java.util.Timer;  
import java.util.TimerTask;  
  
public class Node extends JButton {
	private int x;
	private int y;
	private Color color;
	private String name;
	private Image image;
	public static int DIAM=35;   //this is different
	public Node(int x,int y,Color color) {
		this.x=x;
		this.y=y;
		this.color=color;
	}
	public void setImage(Image image) {
		this.image=image;
	}
	public Image getImage() {
		return this.image;
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
    public Color getColor() {
    	return color;
    }
    public void changeColor(Color myColor) {
    	this.color=myColor;
    }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    
}  
package changTheWorld_pathfinding;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;  
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;

import changTheWorld_pathfinding.Node;

public class MapAndControl extends JFrame {
	private subwayBoard mainBoard;
	private Panel tools;
	private Panel colorTools;
	private Panel textArea;
	private Button cleanButton;
	private Button exitButton;
	private Button grassButton;
	private Button stone1Button;
	private Button bushButton;
	private Button subway1;
	private Button subway2;
	private Button subway3;
	private Button travel;
	private Button answer;
	private JTextArea notice;
	private JLabel text1;
	private String textShowed;
	private Color myColor;
	
	public MapAndControl() {
		setTitle("CITY_SH 地铁");
		mainBoard=new subwayBoard();
		tools= new Panel();
		colorTools=new Panel();
		textArea=new Panel();
		cleanButton=new Button("清除");
		exitButton=new Button("退出");
		
		grassButton=new Button("草地");
		stone1Button=new Button("石头");
		bushButton=new Button("树木");
		subway1=new Button("1号线");
		subway2=new Button("2号线");
		subway3=new Button("3号线");
		travel=new Button("查询地铁指南");
		answer=new Button("打印查询路径");
		text1=new JLabel("说明");
		notice=new JTextArea(40,20);
		mainBoard.setFocusable(true);
		textShowed="城市地铁模拟系统：\n左上角按钮为退出和清除\n使用左下角按钮可放置景观物和地铁\n使用“查询地铁指南”按钮可以查询路径，使用方法为点击按钮后先点击出发地铁站，然后点击目的地铁站\n\n注意右键可删除已放置的物体";
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		cleanButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textShowed="清除所有";
				notice.setText(textShowed);
				mainBoard.cleanAll();
			}
		});	    
		grassButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textShowed="放置草坪";
				notice.setText(textShowed);
				myColor=Color.BLUE;
				mainBoard.changeColor(myColor);
				//Image temp=Toolkit.getDefaultToolkit().getImage("D:\\code\\java\\picture\\grass1.png");
				Image temp = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Images/grass1.png"));

				mainBoard.getThingsImage(temp);
				mainBoard.setToBegin(false);
				mainBoard.setAddSubway(false);
				mainBoard.setAddThings(true);
			}
		});
		stone1Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textShowed="放置石头";
				notice.setText(textShowed);
				myColor=Color.RED;
				mainBoard.changeColor(myColor);
				//Image temp=Toolkit.getDefaultToolkit().getImage("D:\\code\\java\\picture\\stone1.png");
				Image temp = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Images/stone1.png"));

				mainBoard.getThingsImage(temp);
				mainBoard.setToBegin(false);
				mainBoard.setAddSubway(false);
				mainBoard.setAddThings(true);
				
			}
		});
		bushButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textShowed="放置树木";
				notice.setText(textShowed);
				myColor=Color.GRAY;
				mainBoard.changeColor(myColor);
				//Image temp=Toolkit.getDefaultToolkit().getImage("D:\\code\\java\\picture\\tree.png");
				Image temp = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Images/tree.png"));
				mainBoard.getThingsImage(temp);
				mainBoard.setToBegin(false);
				mainBoard.setAddSubway(false);
				mainBoard.setAddThings(true);
			}
		});
		
		subway1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textShowed="放置地铁一号线\n\n若要放置换乘站则点击原有的地铁站即可";
				notice.setText(textShowed);
				myColor=Color.BLACK;
				mainBoard.changeColor(myColor);
				mainBoard.setRailwayNum(1);
				mainBoard.setToBegin(false);
				mainBoard.setAddSubway(true);
				mainBoard.setAddThings(false);
			}
		});
		subway2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textShowed="放置地铁二号线\n\n若要放置换乘站则点击原有的地铁站即可";
				notice.setText(textShowed);
				myColor=Color.GRAY;
				mainBoard.changeColor(myColor);
				mainBoard.setRailwayNum(2);
				mainBoard.setToBegin(false);
				mainBoard.setAddSubway(true);
				mainBoard.setAddThings(false);
			}
		});
		subway3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textShowed="放置地铁三号线\n\n若要放置换乘站则点击原有的地铁站即可";
				notice.setText(textShowed);
				myColor=Color.YELLOW;
				mainBoard.changeColor(myColor);
				mainBoard.setRailwayNum(3);
				mainBoard.setToBegin(false);
				mainBoard.setAddSubway(true);
				mainBoard.setAddThings(false);
			}
		});
		travel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textShowed="寻找路径：\n先点击出发的地铁站，然后点击目的地地铁站\n随后路径会由特殊图标标记出\n等到路径被标记出之后点击“打印查询路径”可看到换乘指南\n注意两次都要点击在地铁站上";
				notice.setText(textShowed);
				mainBoard.setToBegin(true);
				mainBoard.setAddSubway(false);
				mainBoard.setAddThings(false);
			}
		});
		answer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textShowed=mainBoard.getAnsPath();
				notice.setText(textShowed);
				mainBoard.setToBegin(false);
				mainBoard.setAddSubway(false);
				mainBoard.setAddThings(false);
			}
		});
		tools.setLayout(new FlowLayout(FlowLayout.LEFT));
		tools.add(exitButton);
		tools.add(cleanButton);
		colorTools.setLayout(new FlowLayout(FlowLayout.LEFT));
		colorTools.add(grassButton);
		colorTools.add(stone1Button);
		colorTools.add(bushButton);
		colorTools.add(subway1);
		colorTools.add(subway2);
		colorTools.add(subway3);
		colorTools.add(travel);
		colorTools.add(answer);
		textArea.setLayout(new FlowLayout(FlowLayout.LEADING));
		textArea.add(text1);
		notice.setEditable(false);
		notice.setText(textShowed);
		textArea.add(notice);
		
        //charactors.add(toTheLight);
		add(tools,BorderLayout.NORTH);
		add(colorTools,BorderLayout.SOUTH);
		add(textArea,BorderLayout.EAST);
		add(mainBoard,BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	}
	public static void main(String[] args) {
		MapAndControl citySubway=new MapAndControl();
		citySubway.setLocationRelativeTo(null);
		citySubway.setVisible(true);
	}
}

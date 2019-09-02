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
		setTitle("CITY_SH ����");
		mainBoard=new subwayBoard();
		tools= new Panel();
		colorTools=new Panel();
		textArea=new Panel();
		cleanButton=new Button("���");
		exitButton=new Button("�˳�");
		
		grassButton=new Button("�ݵ�");
		stone1Button=new Button("ʯͷ");
		bushButton=new Button("��ľ");
		subway1=new Button("1����");
		subway2=new Button("2����");
		subway3=new Button("3����");
		travel=new Button("��ѯ����ָ��");
		answer=new Button("��ӡ��ѯ·��");
		text1=new JLabel("˵��");
		notice=new JTextArea(40,20);
		mainBoard.setFocusable(true);
		textShowed="���е���ģ��ϵͳ��\n���Ͻǰ�ťΪ�˳������\nʹ�����½ǰ�ť�ɷ��þ�����͵���\nʹ�á���ѯ����ָ�ϡ���ť���Բ�ѯ·����ʹ�÷���Ϊ�����ť���ȵ����������վ��Ȼ����Ŀ�ĵ���վ\n\nע���Ҽ���ɾ���ѷ��õ�����";
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		cleanButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textShowed="�������";
				notice.setText(textShowed);
				mainBoard.cleanAll();
			}
		});	    
		grassButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textShowed="���ò�ƺ";
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
				textShowed="����ʯͷ";
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
				textShowed="������ľ";
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
				textShowed="���õ���һ����\n\n��Ҫ���û���վ����ԭ�еĵ���վ����";
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
				textShowed="���õ���������\n\n��Ҫ���û���վ����ԭ�еĵ���վ����";
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
				textShowed="���õ���������\n\n��Ҫ���û���վ����ԭ�еĵ���վ����";
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
				textShowed="Ѱ��·����\n�ȵ�������ĵ���վ��Ȼ����Ŀ�ĵص���վ\n���·����������ͼ���ǳ�\n�ȵ�·������ǳ�֮��������ӡ��ѯ·�����ɿ�������ָ��\nע�����ζ�Ҫ����ڵ���վ��";
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

package huff;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class huffMain extends JFrame{
	private JTextArea jtInput=new JTextArea();
	private JTextArea jtOutput=new JTextArea();
	private JScrollPane jscrollInput=new JScrollPane(jtInput);
	private JScrollPane jscrollpOutput=new JScrollPane(jtOutput);
	private String inputString;
	private String outputString;
	private JPanel jpMain;
	private JPanel jpButton;
	private JSplitPane jsplit;
	private JButton send;
	private String sendString;
	private String outString;
	private ArrayList<HuffPoint> charList=new ArrayList<HuffPoint>();
	Huff huff;
	public huffMain() {
		jpMain=new JPanel();
		jtInput.setLineWrap(true);
		jtOutput.setLineWrap(true);
		jtOutput.setEditable(false);
		jsplit=new JSplitPane(JSplitPane.VERTICAL_SPLIT,jtOutput,jtInput);
		jsplit.setDividerLocation(500);
		jsplit.setDividerSize(10);
		
		jpButton=new JPanel();
		send=new JButton("编码");
		
		jpMain.setLayout(new BorderLayout());
		jpButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
		jpMain.add(jsplit);
		jpButton.add(send);
		this.add(jpMain,BorderLayout.CENTER);
		this.add(jpButton,BorderLayout.SOUTH);
		this.setTitle("哈夫曼编码实践1751796");
		this.setSize(600,700);
		this.setLocation(100,100);
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendString=jtInput.getText();
				System.out.println(sendString);
				if(sendString==null||sendString.equals("")) {
					//jtOutput.clear();
					outString="输入为空";
					jtOutput.setText(outString);
				}
				else {
					charList.clear();
					divideString(sendString);
					huff=new Huff(charList);
					huff.buildHuffTree();
					outString=huff.paintHuffTree();
					jtOutput.setText(outString);
				}
			}
		});
		
	}
	public void divideString(String sendIn){
		char temp;
		int tempInt;
		int[] charSumList=new int[270];//空间开大一点防止越界
		HuffPoint tempPoint;
		for(int i=0;i<270;i++) {
			charSumList[i]=0;
		}
		for(int i=0;i<sendIn.length();i++) {
			temp=sendIn.charAt(i);
			if(temp!='\n'&&temp!='\t'&&temp!='\r') {
				tempInt=(int)temp;
				charSumList[tempInt]++;
			}
		}
		int sum=0;
		for(int i=0;i<255;i++) {
			if(charSumList[i]>0) {
				tempPoint=new HuffPoint((char)i,charSumList[i],sum,1);
				System.out.println(tempPoint.toString());
				sum++;
				charList.add(tempPoint);
			}
		}
	}
	public static void main(String[] args) {
		huffMain finalBoard=new huffMain();
		finalBoard.setVisible(true);
	}

}

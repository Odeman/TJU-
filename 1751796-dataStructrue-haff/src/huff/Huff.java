package huff;
import java.util.*;

public class Huff {
	//private int[] nums;
	private Comparator<HuffPoint> hufftreeComparator=new HuffComparator();
	private PriorityQueue<HuffPoint> huffPriorityQueue=new PriorityQueue<HuffPoint>(10,hufftreeComparator);
	private ArrayList<HuffTreePoint> treeList=new ArrayList<HuffTreePoint>();
	private int sumHeight;//sumHeight表示树的高度，用于辅助打印树形
	private int sum; //表示原本点的总数
	public Huff(ArrayList<HuffPoint> dataList) {
		HuffPoint temp;
		HuffTreePoint tempTree;
		this.sumHeight=1;
		this.sum=dataList.size();
		for(int i=0;i<dataList.size();i++) {
			temp=dataList.get(i);
			huffPriorityQueue.add(temp);
			tempTree=new HuffTreePoint();
			tempTree.changeName(temp.getName());
			treeList.add(tempTree);
			//System.out.println(temp.getName()+" "+tempTree.getName());
			//System.out.println(treeList.get(i).toString());
		}
		
	}
	public void buildHuffTree() {
		HuffPoint tempHuff_1;
		HuffPoint tempHuff_2;
		HuffPoint tempHuff_3;
		HuffTreePoint tempHuffTree;
		int tempLeftChild,tempRightChild;
		
		while(huffPriorityQueue.size()>1) {
			tempHuff_1=huffPriorityQueue.poll();
			tempHuff_2=huffPriorityQueue.poll();
			
			tempHuff_3=new HuffPoint();
			tempHuff_3.changeNum(tempHuff_1.getNum()+tempHuff_2.getNum());
			tempHuff_3.changePlace(treeList.size());
			tempHuff_3.changeName('*');
			if(tempHuff_1.getSection()>tempHuff_2.getSection()) {
				tempHuff_3.setSection(tempHuff_1.getSection()+1);
			}
			else {
				tempHuff_3.setSection(tempHuff_2.getSection()+1);
			}
			if(tempHuff_3.getSection()>this.sumHeight) {
				this.sumHeight=tempHuff_3.getSection();
			}
			huffPriorityQueue.add(tempHuff_3);
			tempLeftChild=tempHuff_1.getPlace();
			tempRightChild=tempHuff_2.getPlace();
			
			tempHuffTree=new HuffTreePoint();
			tempHuffTree.changeLeftChild(tempLeftChild);
			tempHuffTree.changeRightChild(tempRightChild);
			tempHuffTree.changeName('*');
			treeList.add(tempHuffTree);
		}
		System.out.println("OK "+this.sumHeight);
		for(int i=0;i<treeList.size();i++) {
			System.out.println(treeList.get(i).toString());
		}
		System.out.println("finish--------------------");
	}
	public String paintHuffTree() {
		int tempPlace=0;
		ArrayList<HuffTreePoint> treeListFinal=new ArrayList<HuffTreePoint>();		
		int head=0;
		int tail=0;
		int section=0;//用于记录每一层哈弗曼树的节点总数
		HuffTreePoint tempHuffTree=new HuffTreePoint();
		tempHuffTree=treeList.get(treeList.size()-1);
		HuffTreePoint tempWhiteHuffTree;//用于在左孩子或者右孩子为空的时候打印空格
		treeListFinal.add(tempHuffTree);
		tail++;
		section=tail;
		String ans="";
		int distanceStart=1;//表示打印树形时每层字符之间的距离
		int distanceChar=1;
		int height=sumHeight;//height也表示树的高度
		if(height==1) {
			distanceStart=0;
			distanceChar=1;
		}
		else {
			for(int i=1;i<height;i++) {
				distanceStart=distanceStart*2;
			}
			for(int i=0;i<height;i++) {
				distanceChar=distanceChar*2;
			}
			distanceStart--;
			distanceChar--;
		}
		for(int i=0;i<distanceStart;i++) {
			ans=ans+"  ";
		}
		while(head<tail&&height>0) {
			//System.out.println("doing"+ans);
			tempHuffTree=treeListFinal.get(head);
			if(tempHuffTree.getName()!=' ') {
				ans=ans+tempHuffTree.getName();
			}
			else {
				ans=ans+tempHuffTree.getName()+' ';
			}
			for(int i=0;i<distanceChar;i++) {
				ans=ans+"  ";
			}
			tempPlace=tempHuffTree.getLeftChild();
			if(tempPlace!=-1) {
				treeList.get(tempPlace).addCode(tempHuffTree.getCode()+"0");
				treeListFinal.add(treeList.get(tempPlace));
				tail++;
			}
			else {
				tempWhiteHuffTree=new HuffTreePoint();
				tempWhiteHuffTree.changeName(' ');
				treeListFinal.add(tempWhiteHuffTree);
				tail++;
			}
			
			tempPlace=tempHuffTree.getRightChild();
			if(tempPlace!=-1) {
				treeList.get(tempPlace).addCode(tempHuffTree.getCode()+"1");
				treeListFinal.add(treeList.get(tempPlace));
				tail++;
			}
			else {
				tempWhiteHuffTree=new HuffTreePoint();
				tempWhiteHuffTree.changeName(' ');
				treeListFinal.add(tempWhiteHuffTree);
				tail++;
			}
			
			head++;
			if(head==section) {
				ans=ans+'\n';
				section=tail;
				height--;
				distanceStart=distanceStart/2;
				distanceChar=distanceChar/2;
				for(int i=0;i<distanceStart;i++) {
					ans=ans+"  ";
				}
			}
			//System.out.println(head+" "+tail+" "+section);
		}
		System.out.println(ans);
		ans+='\n';
		for(int i=0;i<this.sum;i++) {
			ans+=treeList.get(i).getName()+":  "+treeList.get(i).getCode()+'\n';
		}
		return ans;
	}
	
}

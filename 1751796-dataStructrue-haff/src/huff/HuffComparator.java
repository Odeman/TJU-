package huff;

import java.util.Comparator;

public class HuffComparator implements Comparator<HuffPoint>{
	public int compare(HuffPoint x, HuffPoint y) {
		if(x.getNum()<y.getNum()) {
			return -1;
		}
		else if(x.getNum()>y.getNum()) {
			return 1;
		}
		return 0;
	}
}

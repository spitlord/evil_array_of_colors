
public class StatisticsHelper {
	private int[] rvalues, gvalues, bvalues;
	private int[] hvalues, svalues, lvalues;



	StatisticsHelper() {

		rvalues = new int[256];
		gvalues = new int[256];
		bvalues = new int[256];
		hvalues = new int[361];
		svalues = new int[256];
		lvalues = new int[256];
	}

	public void rIncrement(int r){
		rvalues[r]++;
	}

	public void gIncrement(int g){
		gvalues[g]++;
	}

	public void bIncrement(int b){
		bvalues[b]++;
	}

	public void hIncrement(double h){
		hvalues[(int)(Math.round(h))]++;
	}

	public void sIncrement(double s){
		svalues[(int) Math.round(s*255)]++;
	}

	public void lIncrement(double l){
		lvalues[(int) Math.round(l*255)]++;
	}

	public int[] getR(){
		return rvalues;
	}

	public int[] getG(){
		return gvalues;
	}

	public int[] getB(){
		return bvalues;
	}

	public int[] getH(){
		return hvalues;
	}

	public int[] getS(){
		return svalues;
	}

	public int[] getL(){
		return lvalues;
	}
}

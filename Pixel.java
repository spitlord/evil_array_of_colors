
public class Pixel {

	private int argb[];   // 0 - alpha, 1 - red, 2 - green, 3 - blue
	private int bit;	  // the bit form of the color

	Pixel() {
		argb = new int[4];
	}

	public Pixel(int alpha, int red, int green, int blue) throws ColorException {
		if (alpha > 255 || red > 255 || green > 255 || blue > 255) throw new ColorException();
		argb = new int[4];
		argb[0] = alpha;
		argb[1] = red;
		argb[2] = green;
		argb[3] = blue;
		bit = ARGBToBit(argb);
	}

	public Pixel(int bit) throws ColorException {
		argb = new int[4];
		this.bit = bit;
		this.argb = bitToARGB(bit);
	}




	public static int toInt (int pixel) {
		int a = 0;

		return a;
	}




	public static int[] bitToARGB(int bit) {
		int[] a = new int[4];
		a[0] = (int) ((bit >>24) & 0xff);
		a[1] = (int) ((bit >>16) & 0xff);
		a[2] = (int) ((bit >>8) & 0xff);
		a[3] = (int) (bit & 0xff);
		return a;
	}

	public static int ARGBToBit(int[] a) {
		int bit = (a[0]<<24) |  (a[1]<<16) | (a[2]<<8) | a[3];
		return bit;
	}




	// set


	public void setARGB(int alpha, int red, int green, int blue) throws ColorException {
		if (alpha > 255 || red > 255 || green > 255 || blue > 255) throw new ColorException();
		if (alpha < 0 || red < 0 || green < 0 || blue < 0) throw new ColorException();

		argb[0] = alpha;
		argb[1] = red;
		argb[2] = green;
		argb[3] = blue;
		bit = ARGBToBit(argb);
	}

	public void setA (int alpha) throws ColorException {
		if (alpha > 255) throw new ColorException();
		if (alpha < 0) throw new ColorException();
		argb[0] = alpha;
		bit = ARGBToBit(argb);
	}

	public void setR (int red) throws ColorException {
		if (red > 255) throw new ColorException();
		if (red < 0) throw new ColorException();
		argb[1] = red;
		bit = ARGBToBit(argb);
	}

	public void setG (int green) throws ColorException {
		if (green > 255) throw new ColorException();
		if (green < 0) throw new ColorException();
		argb[2] = green;
		bit = ARGBToBit(argb);

	}

	public void setB (int blue) throws ColorException {
		if (blue > 255) throw new ColorException();
		if (blue < 0) throw new ColorException();
		argb[3] = blue;
		bit = ARGBToBit(argb);
	}

	public void setBit(int bit) {
		this.bit = bit;
		argb = bitToARGB(bit);
	}

	// shift

	public void shift(Component c) throws ColorException {
		int a,b;

		if (c == Component.G) {
			a = this.getB();
			b = this.getR();
			this.setB(b);
			this.setR(a);
		}
		else if (c == Component.R) {
			a = this.getB();
			b = this.getG();
			this.setB(b);
			this.setG(a);
		}
		else if (c == Component.B) {
			a = this.getR();
			b = this.getG();
			this.setR(b);
			this.setG(a);
		}
	}


	// get

	public int getA() {
		return argb[0];
	}

	public int getR() {
		return argb[1];
	}

	public int getG() {
		return argb[2];
	}

	public int getB() {
		return argb[3];
	}

	public int getBit() {
		return bit;
	}

	// other things

	public static Pixel average (Pixel a, Pixel b) throws ColorException {
		int bit = (a.bit + b.bit)/2;
		Pixel x = new Pixel(bit);
		return x;
	}

	public static Pixel average (Pixel a, Pixel b, Pixel c, Pixel d) throws ColorException {
		int bit = (a.bit + b.bit + c.bit + d.bit)/4;
		Pixel x = new Pixel(bit);
		return x;
	}



	public Pixel clone() {
		Pixel a = null;
		try {
			a = new Pixel(bit);
		} catch (ColorException e) {}

		return a;
	}

	public String toString() {
		return "alpha: " + argb[0] + "  red: "  + argb[1] +
				"  green: " + argb[2] + "  blue: " + argb[3] + "  bit: " + bit;
	}



}


public class Pixel {


	// this class represents a pixel in ARGB, bit value or an AHSL
	// and used for convenience
	// BufferedImage class functions getRGB() and setRGB()
	// work with the bit representation of color (some big number, e.g. -166666)

	private int argb[];   // 0 - alpha, 1 - red, 2 - green, 3 - blue
	private double ahsl[];	  // 0 - alpha, 1 - hue, 2 saturation, 3 - lightness
	private int bit;	  // the bit form of the color

	Pixel() {
		argb = new int[4];
		ahsl = new double[4];
	}



	public Pixel(int alpha, int red, int green, int blue)  {

		argb = new int[4];
		ahsl = new double[4];

		argb[0] = alpha;

		if (alpha > 255) argb[0] = 255;
		else if (alpha < 0) argb[0] = 0;
		else argb[0] = alpha;

		if (red > 255) argb[1] = 255;
		else if (red < 0) argb[1] = 0;
		else argb[1] = red;

		if (green > 255) argb[2] = 255;
		else if (green < 0) argb[2] = 0;
		else argb[2] = green;

		if (blue > 255) argb[3] = 255;
		else if (blue < 0) argb[3] = 0;
		else argb[3] = blue;

		ahsl = ARGBToAHSL(argb);
		bit = ARGBToBit(argb);
	}

	// constructor based on bit

	public Pixel(int bit) {
		argb = new int[4];
		this.bit = bit;
		argb = bitToARGB(bit);
		ahsl = ARGBToAHSL(argb);
	}


	// conversions

	private static int[] bitToARGB(int bit) {
		int[] a = new int[4];
		a[0] = (int) ((bit >>24) & 0xff);
		a[1] = (int) ((bit >>16) & 0xff);
		a[2] = (int) ((bit >>8) & 0xff);
		a[3] = (int) (bit & 0xff);
		return a;
	}

	private static int ARGBToBit(int[] a) {
		int bit = (a[0]<<24) | (a[1]<<16) | (a[2]<<8) | a[3];
		return bit;
	}

	private static double[] ARGBToAHSL(int[] a) {
		double[] ahsl = new double[4];
		ahsl[0] = a[0];

		double r,g,b, min, max;

		r = a[1]/255.0;
		g = a[2]/255.0;
		b = a[3]/255.0;
		max = Math.max(r, Math.max(g, b));
		min = Math.min(r, Math.min(g, b));
		ahsl[3] = (min+max)/2;

		if (min == max) ahsl[2] = 0;
		else if (ahsl[3] < 0.5) ahsl[2] = (max - min)/(max+min);
		else ahsl[2] = (max - min)/(2.0 - max - min);

		if (ahsl[2] == 0) ahsl[1] = 0;
		else {
			if (r > g && r > b) ahsl[1] = ((g - b)/(max - min))%6;
			else if (g > b) ahsl[1] = 2.0 + (b - r)/(max - min);
			else  ahsl[1] = 4.0 + (r-g)/(max - min);
			ahsl[1]*= 60;
			if(ahsl[1] < 0) ahsl[1] += 360;

		}
		return ahsl;
	}

	private static int[] AHSLToARGB(double [] a) {
		double[] tempARGB = new double[4];
		int[] argb = new int[4];
		argb[0] = (int) a[0];

		if (a[2] == 0) {
			argb[1] = (int) Math.round(255*a[3]);
			argb[2] = (int) Math.round(255*a[3]);
			argb[3] = (int) Math.round(255*a[3]);
		}
		else {
			double c = (1 - Math.abs(2*a[3] - 1)) * a[2];
			double x = c * (1 - Math.abs(((a[1]/60.0)%2) - 1));
			double m = a[3] - c/2.0;

			if (a[1] >= 0 && a[1] < 60) {
				tempARGB[1] = c;
				tempARGB[2] = x;
				tempARGB[3] = 0;
			}
			else if (a[1] >= 60 && a[1] <= 120) {
				tempARGB[1] = x;
				tempARGB[2] = c;
				tempARGB[3] = 0;
			}
			else if (a[1] >= 120 && a[1] < 180) {
				tempARGB[1] = 0;
				tempARGB[2] = c;
				tempARGB[3] = x;
			}
			else if (a[1] >= 180 && a[1] < 240) {
				tempARGB[1] = 0;
				tempARGB[2] = x;
				tempARGB[3] = c;
			}
			else if (a[1] >= 240 && a[1] < 300) {
				tempARGB[1] = x;
				tempARGB[2] = 0;
				tempARGB[3] = c;
			}
			else {
				tempARGB[1] = c;
				tempARGB[2] = 0;
				tempARGB[3] = x;
			}

			argb[1] = (int) Math.round((tempARGB[1] + m) * 255);
			argb[2] = (int) Math.round((tempARGB[2] + m) * 255);
			argb[3] = (int) Math.round((tempARGB[3] + m) * 255);
		}

		return argb;
	}




	// set
	
	
	
	
	
	
	///////// GET FROM BIT. RETURNS COMPONENT (0-255)
	

	public static int bitGetA(int bit) {
		bit = bit >> 24 & 0xff;
		return bit;
	}

	public static int bitGetR(int bit) {
		bit  = bit >> 16 & 0xff;
		return bit;
	}

	public static int bitGetG(int bit) {
		bit  = bit >> 8 & 0xff;
		return bit;
	}

	public static int bitGetB(int bit) {
		bit  = bit & 0xff;
		return bit;
	}

	/////////////// SET BIT



	public static int bitSetA(int bit, int alpha) {

		bit = bit & 0x00FFFFFF;
		alpha = alpha << 24;
		bit = bit | alpha;
		return bit;
	}

	public static int bitSetR(int bit, int red) {

		bit = bit & 0xFF00FFFF;
		red = red << 16;
		bit = bit | red;
		return bit;
	}

	public static int bitSetG(int bit, int green) {
		bit = bit & 0xFFFF00FF;
		green = green << 8;
		bit = bit | green;
		return bit;
	}

	public static int bitSetB(int bit, int blue) {
		bit = bit &  0xFFFFFF00;
		bit = bit | blue;
		return bit;
	}

	
	///////////////////////////////////////


	
	public void setARGB(int alpha, int red, int green, int blue) {

		argb[0] = alpha;
		argb[1] = red;
		argb[2] = green;
		argb[3] = blue;
		ahsl = ARGBToAHSL(argb);
		bit = ARGBToBit(argb);
	}


	public void setH (double hue) {
		if (hue > 359) hue = 359;
		else if (hue < 0) hue = 0;
		else ahsl[1] = hue;
		argb = AHSLToARGB(ahsl);
		bit = ARGBToBit(argb);

	}

	public void setS (double saturation) {

		if (saturation > 1) ahsl[2] = 1;
		else if (saturation < 0) ahsl[2] = 0;
		else ahsl[2] = saturation;
		argb = AHSLToARGB(ahsl);
		bit = ARGBToBit(argb);
	}

	public void setL (double lightness) {
		if (lightness > 1) ahsl[3] = 1;
		else if (lightness < 0) ahsl[3] = 0;
		else ahsl[3] = lightness;
		argb = AHSLToARGB(ahsl);
		bit = ARGBToBit(argb);
	}


	public void setA (int alpha) {
		if (alpha > 255) argb[0] = 255;
		else if (alpha < 0) argb[0] = 0;
		else argb[0] = alpha;
		bit = ARGBToBit(argb);
	}

	public void setR (int red) {
		if (red > 255) argb[1] = 255;
		else if (red < 0) argb[1] = 0;
		else argb[1] = red;
		ahsl = ARGBToAHSL(argb);
		bit = ARGBToBit(argb);
	}

	public void setG (int green)  {
		if (green > 255) argb[2] = 255;
		else if (green < 0) argb[2] = 0;
		else argb[2] = green;
		ahsl = ARGBToAHSL(argb);
		bit = ARGBToBit(argb);
	}

	public void setB (int blue)  {
		if (blue > 255) argb[3] = 255;
		else if (blue < 0) argb[3] = 0;
		else argb[3] = blue;
		ahsl = ARGBToAHSL(argb);
		bit = ARGBToBit(argb);
	}

	public void setBit(int bit) {
		this.bit = bit;
		argb = bitToARGB(bit);
		ahsl = ARGBToAHSL(argb);
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

	public double getH() {
		return ahsl[1];
	}

	public double getS() {
		return ahsl[2];
	}

	public double getL() {
		return ahsl[3];
	}

	public int getBit() {
		return bit;
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

	public int greyscale() {
		return (argb[1]+argb[2]+argb[3])/3;
	}

	public boolean sortaGrey(int treshhold) {
		int x = Math.abs(argb[1]-this.greyscale());
		x += Math.abs(argb[2]-this.greyscale());
		x += Math.abs(argb[3]-this.greyscale());
		x = x/3;

		if (x >= treshhold) return false;
		else return true;
	}



	// middle colors?

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

	public int minRGB() {
		return Math.min(argb[1], Math.min(argb[2], argb[3]));
	}

	public int maxRGB() {
		return Math.max(argb[1], Math.max(argb[2], argb[3]));
	}



	// basiccccccccccccc

	public Pixel clone() {
		Pixel a = null;
			a = new Pixel(bit);
		return a;
	}

	public String toString() {
		return
				    "[alpha: " + argb[0] + "]"
				+ "  [red: "  + argb[1] +"]"
				+ "  [green: " + argb[2] + "]"
				+ "  [blue: " + argb[3] + "]"
				+ "  [bit: " + bit + "]"
				+ "  [hue: " + ahsl[1] + "]"
				+ "  [saturation: " + ahsl[2] + "]"
				+ "  [lightness: " + ahsl[3]+"]";
	}
}

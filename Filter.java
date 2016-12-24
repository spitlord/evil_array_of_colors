import java.awt.image.BufferedImage;

public class Filter {




	public static void greyscale(BufferedImage b) throws ColorException {

		for (int ii = 0; ii < b.getWidth(); ii++) {
			for (int jj = 0; jj < b.getHeight(); jj++) {
				Pixel x = new Pixel(b.getRGB(ii, jj));
				int grey = (x.getB()+x.getG()+x.getR())/3;
				x.setARGB(255, grey, grey, grey);
				b.setRGB(ii, jj, x.getBit());
			}
		}
	}


	public static void sumModularis(BufferedImage b, int modulus, int y, int z) throws ColorException {


		for (int ii = 0; ii < b.getWidth(); ii++) {
			for (int jj = 0; jj < b.getHeight(); jj++) {
				Pixel x = new Pixel(b.getRGB(ii, jj));
				int modular = (x.getB()+x.getG()+x.getR())%256%modulus;
				x.setARGB(255, (x.getR()+modular*y)%256, (x.getG()+modular*z)%256, (x.getB()+modular)%256);
				b.setRGB(ii, jj, x.getBit());
			}
		}
	}


	public static void shiftComp(BufferedImage b, int threshhold) throws ColorException {

		for (int ii = 0; ii < b.getWidth(); ii++) {
			for (int jj = 0; jj < b.getHeight(); jj++) {
				Pixel x = new Pixel(b.getRGB(ii, jj));
				if (x.getB() > threshhold) {
					x.shift(Component.B);
					x.setB(x.getG());
				}
				if (x.getG() > threshhold) {
					x.shift(Component.G);
					x.setG(x.getR());
				}
				if (x.getR() > threshhold) {
					x.shift(Component.R);
					x.setR(x.getB());
				}
				b.setRGB(ii, jj, x.getBit());
			}
		}
	}

	public static void greyscaleContrast(BufferedImage b, double subtract1) throws ColorException {
		int subtract = (int) Math.abs(subtract1);

		for (int ii = 0; ii < b.getWidth(); ii++) {
			for (int jj = 0; jj < b.getHeight(); jj++) {
				Pixel x = new Pixel(b.getRGB(ii, jj));
				int grey = (x.getB()+x.getG()+x.getR())/3;
				if (grey - subtract < 0) grey = 0;
				else if (grey + subtract > 255) grey = 255;
				else if (grey > 128) grey = grey + (int)subtract;
				else if (grey < 128) grey = grey - (int)subtract;

				x.setARGB(255, grey, grey, grey);
				b.setRGB(ii, jj, x.getBit());
			}
		}
	}


	public static void multiplix(BufferedImage b, int c, int d, int e) throws ColorException {

		for (int ii = 0; ii < b.getWidth(); ii++) {
			for (int jj = 0; jj < b.getHeight(); jj++) {
				Pixel x = new Pixel(b.getRGB(ii, jj));
				int grey = (x.getB()+x.getG()+x.getR())/3;
				x.setARGB(255, grey*c%256, grey*d%256, grey*e%256);
				b.setRGB(ii, jj, x.getBit());
			}
		}
	}

	public static void modularis(BufferedImage b) throws ColorException {

		for (int ii = 0; ii < b.getWidth(); ii++) {
			for (int jj = 0; jj < b.getHeight(); jj++) {
				Pixel x = new Pixel(b.getRGB(ii, jj));
				x.setB((int)(x.getB()+0.5*ii)%256);
				x.setR((int)(x.getR()+0.3*ii)%256);
				x.setG((int)(x.getG()+0.6*(jj+ii))%256);
				b.setRGB(ii, jj, x.getBit());
			}
		}
	}


	public static void modularis2(BufferedImage b, int modularity) throws ColorException {

		for (int ii = 0; ii < b.getWidth(); ii++) {
			for (int jj = 0; jj < b.getHeight(); jj++) {
				Pixel x = new Pixel(b.getRGB(ii, jj));
				x.setB((int)(x.getB()+0.01*modularity*ii%70)%256);
				x.setR((int)(x.getR()+0.02*modularity*ii%70+jj)%256);
				x.setG((int)(x.getG()+0.03*modularity*(jj)%70)%256);
				b.setRGB(ii, jj, x.getBit());
			}
		}
	}

	public static void modularis3(BufferedImage b, int modularity) throws ColorException {

		for (int ii = 0; ii < b.getWidth(); ii++) {
			for (int jj = 0; jj < b.getHeight(); jj++) {
				Pixel x = new Pixel(b.getRGB(ii, jj));
				x.setB((int)(x.getB()+0.01*modularity*ii%70)%256);
				x.setR((int)(x.getR()+0.02*modularity*ii%70+jj)%256);
				x.setG((int)(x.getG()+0.03*modularity*(jj)%70+ii)%256);
				b.setRGB(ii, jj, x.getBit());
			}
		}
	}

	public static void modularis5(BufferedImage b, int modularity) throws ColorException {

		for (int ii = 0; ii < b.getWidth(); ii++) {
			for (int jj = 0; jj < b.getHeight(); jj++) {
				Pixel x = new Pixel(b.getRGB(ii, jj));
				x.setB((int)(x.getB()+0.0001*modularity*ii*ii*ii%(jj*ii))%256);
				x.setR((int)(x.getR()+0.0001*modularity*ii*jj%(ii*jj))%256);
				x.setG((int)(x.getG()+0.00011*modularity*jj*jj%ii)%256);
				b.setRGB(ii, jj, x.getBit());
			}
		}
	}

	public static void modularis6(BufferedImage b, int modularity) throws ColorException {

		for (int ii = 0; ii < b.getWidth(); ii++) {
			for (int jj = 0; jj < b.getHeight(); jj++) {
				Pixel x = new Pixel(b.getRGB(ii, jj));
				x.setB((int)(x.getB()*x.getB()*0.001*modularity)%256);
				x.setR((int)(x.getR()*x.getR()*0.001*modularity)%256);
				x.setG((int)(x.getG()*x.getG()*0.001*modularity)%256);
				b.setRGB(ii, jj, x.getBit());
			}
		}
	}

	public static void modularis7(BufferedImage b, int modularity) throws ColorException {

		for (int ii = 0; ii < b.getWidth(); ii++) {
			for (int jj = 0; jj < b.getHeight(); jj++) {
				Pixel x = new Pixel(b.getRGB(ii, jj));
				x.setB((int)(x.getB()+ii*0.04*modularity)%256);
				x.setG((int)(x.getG()+ii*0.04*modularity)%256);
				x.setR((int)(x.getR()+ii*0.04*modularity)%256);
				b.setRGB(ii, jj, x.getBit());
			}
		}
	}

	public static void modularis4(BufferedImage b, int modularity) throws ColorException {

		for (int ii = 0; ii < b.getWidth(); ii++) {
			for (int jj = 0; jj < b.getHeight(); jj++) {
				Pixel x = new Pixel(b.getRGB(ii, jj));
				x.setB((int)(x.getB()+ii*ii*0.04*modularity)%256);
				x.setG((int)(x.getG()+jj*jj*0.04*modularity)%256);
				x.setR((int)(x.getR()+ii*jj*0.04*modularity)%256);
				b.setRGB(ii, jj, x.getBit());
			}
		}
	}



	public static void glitch(BufferedImage b, int modularity) throws ColorException {

		for (int ii = 0; ii < b.getWidth(); ii++) {
			for (int jj = 0; jj < b.getHeight(); jj++) {
				Pixel x = new Pixel(b.getRGB(ii, jj));
				x.setB((int)(x.getB()+0.01*modularity*ii%100+jj)%256);
				x.setR((int)(x.getR()+0.02*modularity*ii%100+jj)%256);
				x.setG((int)(x.getG()+0.03*modularity*modularity*(jj)%100+ii)%256);
				b.setRGB(ii, jj, x.getBit());
			}
		}
	}



	public static void average(BufferedImage b, double repeat, double strength) throws ColorException {
		repeat = (int) Math.abs(repeat);
		strength = (int) Math.abs(strength);

		for (int oo = 0; oo < repeat; oo++) {

			for (int ii = 0; ii < b.getWidth()-1; ii++) {
				for (int jj = 0; jj < b.getHeight()-1; jj++) {
					Pixel i = new Pixel(b.getRGB(ii, jj));
					Pixel j = new Pixel(b.getRGB(ii+1, jj));
					Pixel k = new Pixel(b.getRGB(ii, jj+1));
					Pixel l = new Pixel(b.getRGB(ii+1, jj+1));

					Pixel m = Pixel.average(i, j, k, l);
					Pixel x = Pixel.average(i, m);
					for (int kk = 0; kk < strength; kk++) {
						x = Pixel.average(x, i);
					}
					b.setRGB(ii, jj, x.getBit());
				}
			}
		}
	}



	public static void lowToHigh(BufferedImage b, double threshhold) throws ColorException {
		threshhold = (int) Math.abs(threshhold);


		for (int ii = 0; ii < b.getWidth()-1; ii++) {
			for (int jj = 0; jj < b.getHeight()-1; jj++) {
				Pixel x = new Pixel(b.getRGB(ii, jj));

				if (x.getB() < threshhold ) x.setB(255-x.getB());
				if (x.getR() < threshhold ) x.setR(255-x.getR());
				if (x.getG() < threshhold ) x.setG(255-x.getG());

				b.setRGB(ii, jj, x.getBit());
			}
		}
	}


	public static void mildLowToHigh(BufferedImage b, double threshhold, double mildness) throws ColorException {
		threshhold = (int) Math.abs(threshhold);

		for (int ii = 0; ii < b.getWidth()-1; ii++) {
			for (int jj = 0; jj < b.getHeight()-1; jj++) {
				Pixel x = new Pixel(b.getRGB(ii, jj));

				try {
					if (x.getB() < threshhold ) x.setB(((int)(255-x.getB()*mildness))%256);
					if (x.getR() < threshhold ) x.setR(((int)(255-x.getR()*mildness))%256);
					if (x.getG() < threshhold ) x.setG(((int)(255-x.getG()*mildness))%256);
				} catch (ColorException c) { }

				b.setRGB(ii, jj, x.getBit());
			}
		}
	}


	public static void shift(BufferedImage b, Component c) throws ColorException {
		for (int ii = 0; ii < b.getWidth()-1; ii++) {
			for (int jj = 0; jj < b.getHeight()-1; jj++) {
				Pixel x = new Pixel(b.getRGB(ii, jj));
				x.shift(c);
				b.setRGB(ii, jj, x.getBit());
			}
		}
	}



}

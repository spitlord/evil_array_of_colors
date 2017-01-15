import java.awt.image.BufferedImage;

public class Filter {


	// here you make cool filters @#$%^&*

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
	
	
	public static void colorShift (BufferedImage b, double degree) {
		
		for (int ii = 0; ii < b.getWidth(); ii++) {
			for (int jj = 0; jj < b.getHeight(); jj++) {
				Pixel x = new Pixel(b.getRGB(ii, jj));
				x.setH((x.getH() + degree)%360);
				b.setRGB(ii, jj, x.getBit());
			}
		}
	}


	public static void contrast(BufferedImage b,double factor) throws ColorException {

		for (int ii = 0; ii < b.getWidth(); ii++) {
			for (int jj = 0; jj < b.getHeight(); jj++) {
				Pixel x = new Pixel(b.getRGB(ii, jj));
				x.setARGB(255,
					Math.min((int)(x.getR()*factor),255),
					Math.min((int)(x.getG()*factor),255),
					Math.min((int)(x.getB()*factor),255));
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

	public static void mod(BufferedImage b, double modularity) throws ColorException {
		for (int ii = 0; ii < b.getWidth(); ii++) {
			for (int jj = 0; jj < b.getHeight(); jj++) {
				Pixel x = new Pixel(b.getRGB(ii, jj));
				x.setR((int)(x.getR()*modularity)%256);
				x.setG((int)(x.getG()*modularity)%256);
				x.setB((int)(x.getB()*modularity)%256);
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

	public static void cascade(BufferedImage b) throws ColorException {
		int maximum = 0;
		int maxima[][] = new int[b.getWidth()][3];
		for (int ii = 0; ii < b.getWidth(); ii++) {
			for (int jj = 0; jj < b.getHeight(); jj++) {
				Pixel px = new Pixel(b.getRGB(ii, jj));
				maxima[ii][0] += px.getR();
				maxima[ii][1] += px.getG();
				maxima[ii][2] += px.getB();
			}
			for(int channel=0; channel<3; channel++){
				if(maximum < maxima[ii][channel]){
					maximum = maxima[ii][channel];
				}
			}
		}
		for (int ii = 0; ii < b.getWidth(); ii++) {
			int cumsum[] = new int[3];
			for (int jj = 0; jj < b.getHeight(); jj++) {
				Pixel px = new Pixel(b.getRGB(ii, jj));
				cumsum[0] += px.getR();
				cumsum[1] += px.getG();
				cumsum[2] += px.getB();
				px.setR(255*cumsum[0]/maximum);
				px.setG(255*cumsum[1]/maximum);
				px.setB(255*cumsum[2]/maximum);
				b.setRGB(ii,jj,px.getBit());
			}
		}
	}

	public static void absDel(BufferedImage b) throws ColorException {
		double[][][] nuvoImage = new double[b.getWidth()][b.getHeight()][3];
		double max = 0;
		for (int ii = 1; ii < b.getWidth(); ii++) {
			for (int jj = 1; jj < b.getHeight(); jj++) {
				Pixel px = new Pixel(b.getRGB(ii, jj));
				Pixel px_x = new Pixel(b.getRGB(ii-1, jj));
				Pixel px_y = new Pixel(b.getRGB(ii, jj-1));
				nuvoImage[ii][jj][0] = Math.sqrt(
					Math.pow((int)(px.getR()-px_x.getR()),2)+
					Math.pow((int)(px.getR()-px_y.getR()),2));
				nuvoImage[ii][jj][1] = Math.sqrt(
					Math.pow((int)(px.getG()-px_x.getG()),2)+
					Math.pow((int)(px.getG()-px_y.getG()),2));
				nuvoImage[ii][jj][2] = Math.sqrt(
					Math.pow((int)(px.getB()-px_x.getB()),2)+
					Math.pow((int)(px.getB()-px_y.getB()),2));
				max =
					Math.max( max,
					Math.max( nuvoImage[ii][jj][0],
					Math.max( nuvoImage[ii][jj][1],
					          nuvoImage[ii][jj][2]
				)));
			}
		}
		for (int ii = 0; ii < b.getWidth(); ii++) {
			for (int jj = 0; jj < b.getHeight(); jj++) {
				b.setRGB(ii,jj,new Pixel(255,
					(int)(nuvoImage[ii][jj][0]*255/max),
					(int)(nuvoImage[ii][jj][1]*255/max),
					(int)(nuvoImage[ii][jj][2]*255/max)).getBit());

			}
		}
	}

	public static void absDelBalanced(BufferedImage b) throws ColorException {
		double[][][] nuvoImage = new double[b.getWidth()][b.getHeight()][3];
		double max = 0;
		for (int ii = 1; ii < b.getWidth(); ii++) {
			for (int jj = 1; jj < b.getHeight(); jj++) {
				Pixel px = new Pixel(b.getRGB(ii, jj));
				Pixel px_x = new Pixel(b.getRGB(ii-1, jj));
				Pixel px_y = new Pixel(b.getRGB(ii, jj-1));
				nuvoImage[ii][jj][0] = Math.sqrt(
					Math.pow((int)(px.getR()-px_x.getR()),2)+
					Math.pow((int)(px.getR()-px_y.getR()),2));
				nuvoImage[ii][jj][1] = Math.sqrt(
					Math.pow((int)(px.getG()-px_x.getG()),2)+
					Math.pow((int)(px.getG()-px_y.getG()),2));
				nuvoImage[ii][jj][2] = Math.sqrt(
					Math.pow((int)(px.getB()-px_x.getB()),2)+
					Math.pow((int)(px.getB()-px_y.getB()),2));
				nuvoImage[ii][jj][0] *= px.getR();
				nuvoImage[ii][jj][1] *= px.getG();
				nuvoImage[ii][jj][2] *= px.getB();
				max =
					Math.max( max,
					Math.max( nuvoImage[ii][jj][0],
					Math.max( nuvoImage[ii][jj][1],
					          nuvoImage[ii][jj][2]
				)));
			}
		}
		for (int ii = 0; ii < b.getWidth(); ii++) {
			for (int jj = 0; jj < b.getHeight(); jj++) {
				b.setRGB(ii,jj,new Pixel(255,
					(int)(nuvoImage[ii][jj][0]*255/max),
					(int)(nuvoImage[ii][jj][1]*255/max),
					(int)(nuvoImage[ii][jj][2]*255/max)).getBit());

			}
		}
	}

	public static void normalize(BufferedImage b) throws ColorException {
		int maximum = 0;
		for (int ii = 0; ii < b.getWidth(); ii++) {
			for (int jj = 0; jj < b.getHeight(); jj++) {
				Pixel px = new Pixel(b.getRGB(ii, jj));
				maximum =
					Math.max( maximum, 
					Math.max( px.getR(),
					Math.max( px.getG(),
					          px.getB()
				)));
			}
		}
		for (int ii = 0; ii < b.getWidth(); ii++) {
			for (int jj = 0; jj < b.getHeight(); jj++) {
				Pixel px = new Pixel(b.getRGB(ii, jj));
				px.setR(px.getR()/maximum);
				px.setG(px.getG()/maximum);
				px.setB(px.getB()/maximum);
				b.setRGB(ii,jj,px.getBit());
			}
		}


	}
	
	
	
	public static void addNoize (BufferedImage c, int amount) throws ColorException {
		amount =  Math.abs(amount);
		int a = 0;

		for (int ii = 0; ii < c.getWidth(); ii++) {
			for (int jj = 0; jj < c.getHeight(); jj++) {
				Pixel x = new Pixel(c.getRGB(ii, jj));

					a = (int)(Math.random()*2);
					if (a==0) amount = -amount;
					x.setR(Math.abs((x.getR()+amount))%256);

					a = (int)(Math.random()*2);
					if (a==0) amount = -amount;
					x.setG(Math.abs((x.getG()+amount))%256);

					a = (int)(Math.random()*2);
					if (a==0) amount = -amount;
					x.setB(Math.abs((x.getB()+amount))%256);

					c.setRGB(ii, jj, x.getBit());
			}
		}
	}
	
	
		public static void greyToBlack(BufferedImage b, int treshhold, int change) throws ColorException {
		for (int ii = 0; ii < b.getWidth(); ii++) {
			for (int jj = 0; jj < b.getHeight(); jj++) {
				Pixel x = new Pixel(b.getRGB(ii, jj));

				if (x.sortaGrey(treshhold)) {
					x.setB(((x.getB() - change) >= 0)? (x.getB() - change) : 0);
					x.setR(((x.getR() - change) >= 0)? (x.getR() - change) : 0);
					x.setG(((x.getG() - change) >= 0)? (x.getG() - change) : 0);
				}

					b.setRGB(ii, jj, x.getBit());
			}
		}
	}

	public static void darker(BufferedImage b, int change) throws ColorException {
		for (int ii = 0; ii < b.getWidth(); ii++) {
			for (int jj = 0; jj < b.getHeight(); jj++) {
				Pixel x = new Pixel(b.getRGB(ii, jj));

				x.setB(((x.getB() - change) >= 0)? (x.getB() - change) : 0);
				x.setR(((x.getR() - change) >= 0)? (x.getR() - change) : 0);
				x.setG(((x.getG() - change) >= 0)? (x.getG() - change) : 0);

				b.setRGB(ii, jj, x.getBit());
			}
		}
	}

	public static void greyToWhite(BufferedImage b, int treshhold, int change) throws ColorException {
		for (int ii = 0; ii < b.getWidth(); ii++) {
			for (int jj = 0; jj < b.getHeight(); jj++) {
				Pixel x = new Pixel(b.getRGB(ii, jj));
				if (x.sortaGrey(treshhold)) {
					x.setB(((x.getB() + change) <= 255)? (x.getB() + change) : 255);
					x.setR(((x.getR() + change) <= 255)? (x.getR() + change) : 255);
					x.setG(((x.getG() + change) <= 255)? (x.getG() + change) : 255);
				}

				b.setRGB(ii, jj, x.getBit());
			}
		}
	}

	public static void lighter(BufferedImage b, int change) throws ColorException {
		for (int ii = 0; ii < b.getWidth(); ii++) {
			for (int jj = 0; jj < b.getHeight(); jj++) {
				Pixel x = new Pixel(b.getRGB(ii, jj));
				x.setB(((x.getB() + change) <= 255)? (x.getB() + change) : 255);
				x.setR(((x.getR() + change) <= 255)? (x.getR() + change) : 255);
				x.setG(((x.getG() + change) <= 255)? (x.getG() + change) : 255);

					b.setRGB(ii, jj, x.getBit());
			}
		}
	}
	
		public static void timeLapse(BufferedImage b, int treshhold, int change) throws ColorException {
		for (int ii = 0; ii < b.getWidth(); ii++) {
			for (int jj = 0; jj < b.getHeight(); jj++) {
				Pixel a = new Pixel(b.getRGB(ii, jj));
				try {
					if (a.sortaGrey(treshhold)) {
						if (a.getB() == a.minRGB()) {
							a.setB((Math.abs(a.getB() - change))%256);
						}
						else if (a.getB() == a.maxRGB()) {
							a.setB((a.getB() + change)%256);
						}

						if (a.getG() == a.minRGB()) {
							a.setG((Math.abs(a.getG() - change))%256);
						}
						else if (a.getG() == a.maxRGB()) {
							a.setG((a.getG() + change)%256);
						}

						if (a.getR() == a.minRGB()) {
							a.setR((Math.abs(a.getR() - change))%256);
						}
						else if (a.getR() == a.maxRGB()) {
							a.setR((a.getR() + change)%256);
						}

					}

					b.setRGB(ii, jj, a.getBit());
				} catch(ColorException ce) {}
			}
		}
	}
	
	
	public static void minmaxno(BufferedImage b, int change) throws ColorException {
		for (int ii = 0; ii < b.getWidth(); ii++) {
			for (int jj = 0; jj < b.getHeight(); jj++) {
				Pixel a = new Pixel(b.getRGB(ii, jj));
				try {
					if (a.getB() == a.minRGB()) {
						a.setB((Math.abs(a.getB() - change))%256);
					}
					else if (a.getB() == a.maxRGB()) {
						a.setB((a.getB() + change)%256);
					}

					if (a.getG() == a.minRGB()) {
						a.setG((Math.abs(a.getG() - change))%256);
					}
					else if (a.getG() == a.maxRGB()) {
						a.setG((a.getG() + change)%256);
					}

					if (a.getR() == a.minRGB()) {
						a.setR((Math.abs(a.getR() - change))%256);
					}
					else if (a.getR() == a.maxRGB()) {
						a.setR((a.getR() + change)%256);
					}
					b.setRGB(ii, jj, a.getBit());
				} catch(ColorException ce) {}
			}
		}
	}

	
	
	
	
	
}

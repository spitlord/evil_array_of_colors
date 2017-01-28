import java.awt.image.BufferedImage;

public class Filter {


	// here you make cool filters @#$%^&*


	public static void greyscale(BufferedImage img)  {

		for (int ii = 0; ii < img.getWidth(); ii++) {
			for (int jj = 0; jj < img.getHeight(); jj++) {
				Pixel x = new Pixel(img.getRGB(ii, jj));
				int grey = x.greyscale();
				x.setARGB(255, grey, grey, grey);
				img.setRGB(ii, jj, x.getBit());
			}
		}
	}

	public static void weightedGreyscale(BufferedImage img, double r, double g, double b)  {
		// normalize to 1
		double sum = r + g + b;
		r/=sum; g/=sum; b/=sum;
		int grey;
		for (int ii = 0; ii < img.getWidth(); ii++) {
			for (int jj = 0; jj < img.getHeight(); jj++) {
				Pixel x = new Pixel(img.getRGB(ii, jj));
				grey = (int) (r*x.getR() + g*x.getG() + b*x.getB());
				x.setARGB(255, grey, grey, grey);
				img.setRGB(ii, jj, x.getBit());
			}
		}
	}

	public static void aveGre(BufferedImage img)  {
		int sum = 0;

		for (int ii = 0; ii < img.getWidth(); ii++) {
			for (int jj = 0; jj < img.getHeight(); jj++) {
				Pixel x = new Pixel(img.getRGB(ii, jj));
				sum+=x.getG();
			}
		}
		sum/=img.getWidth()*img.getHeight();

		for (int ii = 0; ii < img.getWidth(); ii++) {
			for (int jj = 0; jj < img.getHeight(); jj++) {
				Pixel x = new Pixel(img.getRGB(ii, jj));
				x.setG(sum);
				img.setRGB(ii, jj, x.getBit());
			}
		}
	}

	public static void aveGre(BufferedImage img, int g)  {


		for (int ii = 0; ii < img.getWidth(); ii++) {
			for (int jj = 0; jj < img.getHeight(); jj++) {
				Pixel x = new Pixel(img.getRGB(ii, jj));
				x.setG(g);
				img.setRGB(ii, jj, x.getBit());
			}
		}
	}


	public static void saturationTreshhold(BufferedImage img, double treshhold)  {
		treshhold *= 0.01;
		Pixel[][] copy = Util.copy(img);
		Filter.colorShift4(img, 0.005);


		for (int ii = 0; ii < img.getWidth(); ii++) {
			for (int jj = 0; jj < img.getHeight(); jj++) {
				Pixel x = new Pixel(img.getRGB(ii, jj));
				if (x.getS() > treshhold) img.setRGB(ii, jj, x.getBit());
				else img.setRGB(ii, jj, copy[ii][jj].getBit());
			}
		}
	}

	public static void hueFuck(BufferedImage img)  {



		for (int ii = 0; ii < img.getWidth()-1; ii++) {
			for (int jj = 0; jj < img.getHeight()-3; jj+=2) {
				Pixel x = new Pixel(img.getRGB(ii, jj));
				Pixel y = new Pixel(img.getRGB(ii, jj+1));
				Pixel z = new Pixel(img.getRGB(ii+1, jj));
				x.setH((y.getH()+z.getH())%360);
				img.setRGB(ii, jj, x.getBit());
			}
		}
	}

	public static void badImpressionism(BufferedImage img)  {


		for (int ii = 0; ii < img.getWidth()-1; ii++) {
			for (int jj = 0; jj < img.getHeight()-1; jj++) {
				Pixel x = new Pixel(img.getRGB(ii, jj));
				Pixel y = new Pixel(img.getRGB(ii, jj+1));
				Pixel z = new Pixel(img.getRGB(ii+1, jj));
				x.setS(Math.sqrt((y.getL()+z.getS()))/2);
				x.setL(Math.sqrt((y.getL()+z.getS()))/2);
				img.setRGB(ii, jj, x.getBit());
			}
		}
	}





	public static void hueFuck3(BufferedImage img)  {


		for (int ii = 0; ii < img.getWidth()-1; ii++) {
			for (int jj = 0; jj < img.getHeight()-1; jj++) {
				Pixel x = new Pixel(img.getRGB(ii, jj));
				Pixel y = new Pixel(img.getRGB(ii, jj+1));
				Pixel z = new Pixel(img.getRGB(ii+1, jj));
				x.setS(Math.sqrt((y.getS()+z.getS()))/2);
				x.setL(Math.sqrt((y.getS()+z.getS()))/2);
				img.setRGB(ii, jj, x.getBit());
			}
		}
	}

	public static void colorShift (BufferedImage img, double degree)  {

		for (int ii = 0; ii < img.getWidth(); ii++) {
			for (int jj = 0; jj < img.getHeight(); jj++) {
				Pixel x = new Pixel(img.getRGB(ii, jj));
				x.setH((x.getH() + degree)%360);
				img.setRGB(ii, jj, x.getBit());
			}
		}
	}

	public static void lightSat (BufferedImage img) {

		double temp = 0;
		for (int ii = 0; ii < img.getWidth(); ii++) {
			for (int jj = 0; jj < img.getHeight(); jj++) {
				Pixel x = new Pixel(img.getRGB(ii, jj));
				temp = x.getL();
				x.setL(x.getS());
				x.setS(temp);
				img.setRGB(ii, jj, x.getBit());
			}
		}
	}


	public static void hueBased (BufferedImage img, double degree) {

		for (int ii = 0; ii < img.getWidth(); ii++) {
			for (int jj = 0; jj < img.getHeight(); jj++) {
				Pixel x = new Pixel(img.getRGB(ii, jj));
				x.setH((x.getS()*x.getL()*360 + degree) %360);
				img.setRGB(ii, jj, x.getBit());
			}
		}
	}

	public static void maxSatLig (BufferedImage img) {
		double max;

		for (int ii = 0; ii < img.getWidth(); ii++) {
			for (int jj = 0; jj < img.getHeight(); jj++) {
				Pixel x = new Pixel(img.getRGB(ii, jj));
				max = Math.max(x.getL(), x.getS());
				x.setS(max);
				x.setL(max);
				img.setRGB(ii, jj, x.getBit());

			}
		}
	}

	public static void destroyWhite (BufferedImage img) {
		double max;

		for (int ii = 0; ii < img.getWidth(); ii++) {
			for (int jj = 0; jj < img.getHeight(); jj++) {
				Pixel x = new Pixel(img.getRGB(ii, jj));
				max = Math.max(x.getL(), x.getS());
				if (x.getL()>0.8) x.setL(1-x.getL());

				img.setRGB(ii, jj, x.getBit());

			}
		}
	}

	public static void hueDestroy (BufferedImage img, double hue, double bound) {

		for (int ii = 0; ii < img.getWidth(); ii++) {
			for (int jj = 0; jj < img.getHeight(); jj++) {
				Pixel x = new Pixel(img.getRGB(ii, jj));
				if (x.getH() > hue + bound || x.getH() < hue - bound)
					x.setH(hue);
				img.setRGB(ii, jj, x.getBit());

			}
		}
	}

	public static void colorShift2 (BufferedImage img, double multiplix)  {

		for (int ii = 0; ii < img.getWidth(); ii++) {
			for (int jj = 0; jj < img.getHeight(); jj++) {
				Pixel x = new Pixel(img.getRGB(ii, jj));
				x.setH((x.getH()*x.getL()*multiplix)%360);
				img.setRGB(ii, jj, x.getBit());
			}
		}
	}

	public static void colorShift3 (BufferedImage img)  {

		for (int ii = 0; ii < img.getWidth(); ii++) {
			for (int jj = 0; jj < img.getHeight(); jj++) {
				Pixel x = new Pixel(img.getRGB(ii, jj));
				x.setH((x.getH()+ii+jj)%360);
				img.setRGB(ii, jj, x.getBit());
			}
		}
	}

	public static void colorShift4 (BufferedImage img, double multiplix)  {

		for (int ii = 0; ii < img.getWidth(); ii++) {
			for (int jj = 0; jj < img.getHeight(); jj++) {
				Pixel x = new Pixel(img.getRGB(ii, jj));
				x.setH((ii*ii*jj*multiplix+x.getH())%360);
				img.setRGB(ii, jj, x.getBit());
			}
		}
	}



	public static void saturation (BufferedImage img, double coef)  {

		for (int ii = 0; ii < img.getWidth(); ii++) {
			for (int jj = 0; jj < img.getHeight(); jj++) {
				Pixel x = new Pixel(img.getRGB(ii, jj));
				x.setS((x.getS()+coef));

				img.setRGB(ii, jj, x.getBit());
			}
		}
	}

	public static void greun(BufferedImage img, int t)  {
		for (int ii = 0; ii < img.getWidth(); ii++) {
			for (int jj = 0; jj < img.getHeight(); jj++) {
				Pixel a = new Pixel(img.getRGB(ii, jj));
					a.setB((a.getB()+a.getG())%256);
					a.setR(a.getR()+a.getB()%256);
					a.setG(a.getG()+ a.getR()%256);
					img.setRGB(ii, jj, a.getBit());
			}
		}
	}


	public static void pont(BufferedImage img, int t)  {
		for (int ii = 0; ii < img.getWidth(); ii++) {
			for (int jj = 0; jj < img.getHeight(); jj++) {
				Pixel a = new Pixel(img.getRGB(ii, jj));

					a.setB((a.getB() + a.getG())%256);
					a.setR((a.getR() + a.getB())%256);
					a.setG((a.getG() + a.getR())%256);
					img.setRGB(ii, jj, a.getBit());
			}
		}
	}


	// replaces neutral colors with whatever you tell it too

	public static void noGrey(BufferedImage img, int treshhold)  {
		for (int ii = 0; ii < img.getWidth(); ii++) {
			for (int jj = 0; jj < img.getHeight(); jj++) {
				Pixel a = new Pixel(img.getRGB(ii, jj));
					if (a.sortaGrey(treshhold)) {
						a.setB((int)(a.getB()+ii)%256);
					}
					if (a.sortaGrey(treshhold)) {
						a.setR((int)(a.getR()+jj)%256);
					}
					if (a.sortaGrey(treshhold)) {
						a.setG((int)(a.getG()+jj+ii)%256);
					}
					if (a.sortaGrey(treshhold)) {a.setG(0);}
					img.setRGB(ii, jj, a.getBit());

			}
		}
	}

	public static void noGrey2(BufferedImage img, int t)  {
		for (int ii = 0; ii < img.getWidth(); ii++) {
			for (int jj = 0; jj < img.getHeight(); jj++) {
				Pixel a = new Pixel(img.getRGB(ii, jj));
					if (a.sortaGrey(140)) {a.setG(0);}
					a.setB((int)(a.getB()+t)%256);
					if (a.sortaGrey(140)) {a.setG(0);}
					a.setR((int)(a.getR()+t*jj)%256);
					if (a.sortaGrey(140)) {a.setG(0);}
					a.setG((int)(a.getG()+t*ii)%256);
					if (a.sortaGrey(140)) {a.setG(0);}
					img.setRGB(ii, jj, a.getBit());

			}
		}
	}


	public static void sumModularis(BufferedImage img, int modulus, int y, int z)  {


		for (int ii = 0; ii < img.getWidth(); ii++) {
			for (int jj = 0; jj < img.getHeight(); jj++) {
				Pixel x = new Pixel(img.getRGB(ii, jj));
				int modular = (x.getB()+x.getG()+x.getR())%256%modulus;
				x.setARGB(255, (x.getR()+modular*y)%256, (x.getG()+modular*z)%256, (x.getB()+modular)%256);
				img.setRGB(ii, jj, x.getBit());
			}
		}
	}


	public static void shiftComp(BufferedImage img, int threshhold)  {

		for (int ii = 0; ii < img.getWidth(); ii++) {
			for (int jj = 0; jj < img.getHeight(); jj++) {
				Pixel x = new Pixel(img.getRGB(ii, jj));
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
				img.setRGB(ii, jj, x.getBit());
			}
		}
	}

	public static void greyscaleContrast(BufferedImage img, double subtract1)  {
		int subtract = (int) Math.abs(subtract1);

		for (int ii = 0; ii < img.getWidth(); ii++) {
			for (int jj = 0; jj < img.getHeight(); jj++) {
				Pixel x = new Pixel(img.getRGB(ii, jj));
				int grey = (x.getB()+x.getG()+x.getR())/3;
				if (grey - subtract < 0) grey = 0;
				else if (grey + subtract > 255) grey = 255;
				else if (grey > 128) grey = grey + (int)subtract;
				else if (grey < 128) grey = grey - (int)subtract;

				x.setARGB(255, grey, grey, grey);
				img.setRGB(ii, jj, x.getBit());
			}
		}
	}


	public static void multiplix(BufferedImage img, int c, int d, int e)  {

		for (int ii = 0; ii < img.getWidth(); ii++) {
			for (int jj = 0; jj < img.getHeight(); jj++) {
				Pixel x = new Pixel(img.getRGB(ii, jj));
				int grey = (x.getB()+x.getG()+x.getR())/3;
				x.setARGB(255, grey*c%256, grey*d%256, grey*e%256);
				img.setRGB(ii, jj, x.getBit());
			}
		}
	}

	public static void modularis(BufferedImage img)  {

		for (int ii = 0; ii < img.getWidth(); ii++) {
			for (int jj = 0; jj < img.getHeight(); jj++) {
				Pixel x = new Pixel(img.getRGB(ii, jj));
				x.setB((int)(x.getB()+0.5*ii)%256);
				x.setR((int)(x.getR()+0.3*ii)%256);
				x.setG((int)(x.getG()+0.6*(jj+ii))%256);
				img.setRGB(ii, jj, x.getBit());
			}
		}
	}


	public static void modularis2(BufferedImage img, int modularity)  {

		for (int ii = 0; ii < img.getWidth(); ii++) {
			for (int jj = 0; jj < img.getHeight(); jj++) {
				Pixel x = new Pixel(img.getRGB(ii, jj));
				x.setB((int)(x.getB()+0.01*modularity*ii%70)%256);
				x.setR((int)(x.getR()+0.02*modularity*ii%70+jj)%256);
				x.setG((int)(x.getG()+0.03*modularity*(jj)%70)%256);
				img.setRGB(ii, jj, x.getBit());
			}
		}
	}

	public static void modularis3(BufferedImage img, int modularity)  {

		for (int ii = 0; ii < img.getWidth(); ii++) {
			for (int jj = 0; jj < img.getHeight(); jj++) {
				Pixel x = new Pixel(img.getRGB(ii, jj));
				x.setB((int)(x.getB()+0.01*modularity*ii%70)%256);
				x.setR((int)(x.getR()+0.02*modularity*ii%70+jj)%256);
				x.setG((int)(x.getG()+0.03*modularity*(jj)%70+ii)%256);
				img.setRGB(ii, jj, x.getBit());
			}
		}
	}

	public static void modularis5(BufferedImage img, int modularity)  {

		for (int ii = 0; ii < img.getWidth(); ii++) {
			for (int jj = 0; jj < img.getHeight(); jj++) {
				Pixel x = new Pixel(img.getRGB(ii, jj));
				x.setB((int)(x.getB()+0.0001*modularity*ii*ii*ii%(jj*ii))%256);
				x.setR((int)(x.getR()+0.0001*modularity*ii*jj%(ii*jj))%256);
				x.setG((int)(x.getG()+0.00011*modularity*jj*jj%ii)%256);
				img.setRGB(ii, jj, x.getBit());
			}
		}
	}

	public static void modularis6(BufferedImage img, int modularity)  {

		for (int ii = 0; ii < img.getWidth(); ii++) {
			for (int jj = 0; jj < img.getHeight(); jj++) {
				Pixel x = new Pixel(img.getRGB(ii, jj));
				x.setB((int)(x.getB()*x.getB()*0.001*modularity)%256);
				x.setR((int)(x.getR()*x.getR()*0.001*modularity)%256);
				x.setG((int)(x.getG()*x.getG()*0.001*modularity)%256);
				img.setRGB(ii, jj, x.getBit());
			}
		}
	}

	public static void modularis7(BufferedImage img, int modularity)  {

		for (int ii = 0; ii < img.getWidth(); ii++) {
			for (int jj = 0; jj < img.getHeight(); jj++) {
				Pixel x = new Pixel(img.getRGB(ii, jj));
				x.setB((int)(x.getB()+ii*0.04*modularity)%256);
				x.setG((int)(x.getG()+ii*0.04*modularity)%256);
				x.setR((int)(x.getR()+ii*0.04*modularity)%256);
				img.setRGB(ii, jj, x.getBit());
			}
		}
	}

	public static void modularis4(BufferedImage img, int modularity)  {

		for (int ii = 0; ii < img.getWidth(); ii++) {
			for (int jj = 0; jj < img.getHeight(); jj++) {
				Pixel x = new Pixel(img.getRGB(ii, jj));
				x.setB((int)(x.getB()+ii*ii*0.04*modularity)%256);
				x.setG((int)(x.getG()+jj*jj*0.04*modularity)%256);
				x.setR((int)(x.getR()+ii*jj*0.04*modularity)%256);
				img.setRGB(ii, jj, x.getBit());
			}
		}
	}



	public static void glitch(BufferedImage img, int modularity)  {

		for (int ii = 0; ii < img.getWidth(); ii++) {
			for (int jj = 0; jj < img.getHeight(); jj++) {
				Pixel x = new Pixel(img.getRGB(ii, jj));
				x.setB((int)(x.getB()+0.01*modularity*ii%100+jj)%256);
				x.setR((int)(x.getR()+0.02*modularity*ii%100+jj)%256);
				x.setG((int)(x.getG()+0.03*modularity*modularity*(jj)%100+ii)%256);
				img.setRGB(ii, jj, x.getBit());
			}
		}
	}


	public static void cascade(BufferedImage img)  {
		int maximum = 0;
		int maxima[][] = new int[img.getWidth()][3];
		for (int ii = 0; ii < img.getWidth(); ii++) {
			for (int jj = 0; jj < img.getHeight(); jj++) {
				Pixel px = new Pixel(img.getRGB(ii, jj));
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
		for (int ii = 0; ii < img.getWidth(); ii++) {
			int cumsum[] = new int[3];
			for (int jj = 0; jj < img.getHeight(); jj++) {
				Pixel px = new Pixel(img.getRGB(ii, jj));
				cumsum[0] += px.getR();
				cumsum[1] += px.getG();
				cumsum[2] += px.getB();
				px.setR(255*cumsum[0]/maximum);
				px.setG(255*cumsum[1]/maximum);
				px.setB(255*cumsum[2]/maximum);
				img.setRGB(ii,jj,px.getBit());
			}
		}
	}




	public static void average(BufferedImage img, double repeat, double strength)  {
		repeat = (int) Math.abs(repeat);
		strength = (int) Math.abs(strength);

		for (int oo = 0; oo < repeat; oo++) {

			for (int ii = 0; ii < img.getWidth()-1; ii++) {
				for (int jj = 0; jj < img.getHeight()-1; jj++) {
					Pixel i = new Pixel(img.getRGB(ii, jj));
					Pixel j = new Pixel(img.getRGB(ii+1, jj));
					Pixel k = new Pixel(img.getRGB(ii, jj+1));
					Pixel l = new Pixel(img.getRGB(ii+1, jj+1));

					Pixel m = Pixel.average(i, j, k, l);
					Pixel x = Pixel.average(i, m);
					for (int kk = 0; kk < strength; kk++) {
						x = Pixel.average(x, i);
					}
					img.setRGB(ii, jj, x.getBit());
				}
			}
		}
	}



	public static void lowToHigh(BufferedImage img, double threshhold)  {
		threshhold = (int) Math.abs(threshhold);


		for (int ii = 0; ii < img.getWidth()-1; ii++) {
			for (int jj = 0; jj < img.getHeight()-1; jj++) {
				Pixel x = new Pixel(img.getRGB(ii, jj));

				if (x.getB() < threshhold ) x.setB(255-x.getB());
				if (x.getR() < threshhold ) x.setR(255-x.getR());
				if (x.getG() < threshhold ) x.setG(255-x.getG());

				img.setRGB(ii, jj, x.getBit());
			}
		}
	}


	public static void mildLowToHigh(BufferedImage img, double threshhold, double mildness)  {
		threshhold = (int) Math.abs(threshhold);

		for (int ii = 0; ii < img.getWidth()-1; ii++) {
			for (int jj = 0; jj < img.getHeight()-1; jj++) {
				Pixel x = new Pixel(img.getRGB(ii, jj));

				if (x.getB() < threshhold ) x.setB(((int)(255-x.getB()*mildness))%256);
				if (x.getR() < threshhold ) x.setR(((int)(255-x.getR()*mildness))%256);
				if (x.getG() < threshhold ) x.setG(((int)(255-x.getG()*mildness))%256);

				img.setRGB(ii, jj, x.getBit());
			}
		}
	}


	public static void shift(BufferedImage img, Component c)  {
		for (int ii = 0; ii < img.getWidth()-1; ii++) {
			for (int jj = 0; jj < img.getHeight()-1; jj++) {
				Pixel x = new Pixel(img.getRGB(ii, jj));
				x.shift(c);
				img.setRGB(ii, jj, x.getBit());
			}
		}
	}


	public static void absDel(BufferedImage img)  {
		double[][][] nuvoImage = new double[img.getWidth()][img.getHeight()][3];
		double max = 0;
		for (int ii = 1; ii < img.getWidth(); ii++) {
			for (int jj = 1; jj < img.getHeight(); jj++) {
				Pixel px = new Pixel(img.getRGB(ii, jj));
				Pixel px_x = new Pixel(img.getRGB(ii-1, jj));
				Pixel px_y = new Pixel(img.getRGB(ii, jj-1));
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
		for (int ii = 0; ii < img.getWidth(); ii++) {
			for (int jj = 0; jj < img.getHeight(); jj++) {
				img.setRGB(ii,jj,new Pixel(255,
					(int)(nuvoImage[ii][jj][0]*255/max),
					(int)(nuvoImage[ii][jj][1]*255/max),
					(int)(nuvoImage[ii][jj][2]*255/max)).getBit());

			}
		}
	}



	public static void absDelBalanced(BufferedImage img)  {
		double[][][] nuvoImage = new double[img.getWidth()][img.getHeight()][3];
		double max = 0;
		for (int ii = 1; ii < img.getWidth(); ii++) {
			for (int jj = 1; jj < img.getHeight(); jj++) {
				Pixel px = new Pixel(img.getRGB(ii, jj));
				Pixel px_x = new Pixel(img.getRGB(ii-1, jj));
				Pixel px_y = new Pixel(img.getRGB(ii, jj-1));
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
		for (int ii = 0; ii < img.getWidth(); ii++) {
			for (int jj = 0; jj < img.getHeight(); jj++) {
				img.setRGB(ii,jj,new Pixel(255,
					(int)(nuvoImage[ii][jj][0]*255/max),
					(int)(nuvoImage[ii][jj][1]*255/max),
					(int)(nuvoImage[ii][jj][2]*255/max)).getBit());

			}
		}
	}

	public static void contrast(BufferedImage img,double factor)  {

		for (int ii = 0; ii < img.getWidth(); ii++) {
			for (int jj = 0; jj < img.getHeight(); jj++) {
				Pixel x = new Pixel(img.getRGB(ii, jj));
				x.setARGB(255,
					Math.min((int)(x.getR()*factor),255),
					Math.min((int)(x.getG()*factor),255),
					Math.min((int)(x.getB()*factor),255));
				img.setRGB(ii, jj, x.getBit());
			}
		}
	}

	public static void normalize(BufferedImage img)  {
		int maximum = 0;
		for (int ii = 0; ii < img.getWidth(); ii++) {
			for (int jj = 0; jj < img.getHeight(); jj++) {
				Pixel px = new Pixel(img.getRGB(ii, jj));
				maximum =
					Math.max( maximum,
					Math.max( px.getR(),
					Math.max( px.getG(),
					          px.getB()
				)));
			}
		}
		for (int ii = 0; ii < img.getWidth(); ii++) {
			for (int jj = 0; jj < img.getHeight(); jj++) {
				Pixel px = new Pixel(img.getRGB(ii, jj));
				px.setR(px.getR()/maximum);
				px.setG(px.getG()/maximum);
				px.setB(px.getB()/maximum);
				img.setRGB(ii,jj,px.getBit());
			}
		}


	}
}




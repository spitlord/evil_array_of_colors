import java.awt.image.BufferedImage;

public class Generator {



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

}

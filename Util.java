import java.awt.image.BufferedImage;

public class Util {

	public static Pixel[][] copy(BufferedImage b) {

		Pixel[][] a = new Pixel[b.getWidth()][b.getHeight()];

		for (int ii = 0; ii < b.getWidth(); ii++) {
			for (int jj = 0; jj < b.getHeight(); jj++) {
				Pixel x = new Pixel(b.getRGB(ii, jj));
				a[ii][jj] = x.clone();
				b.setRGB(ii, jj, x.getBit());
			}
		}
		return a;
	}

	public static void buf (BufferedImage b) {





	}


}

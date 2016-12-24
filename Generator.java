import java.awt.image.BufferedImage;

public class Generator {

	// this class intended to be used for generating shapes
	// there's nothing here insofar


	public static void shape(BufferedImage b) throws ColorException {

		for (int ii = 0; ii < b.getWidth(); ii++) {
			for (int jj = 0; jj < b.getHeight(); jj++) {
				Pixel x = new Pixel(b.getRGB(ii, jj));
			}
		}
	}

}

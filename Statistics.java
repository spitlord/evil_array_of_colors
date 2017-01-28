import java.awt.image.BufferedImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Statistics {


	public static StatisticsHelper RGBandHSLValues(BufferedImage img) {

		StatisticsHelper stat = new StatisticsHelper();

		for (int ii = 0; ii < img.getWidth(); ii++) {
			for (int jj = 0; jj < img.getHeight(); jj++) {
				Pixel x = new Pixel(img.getRGB(ii, jj));
				stat.rIncrement(x.getR());
				stat.gIncrement(x.getG());
				stat.bIncrement(x.getB());
				stat.hIncrement(x.getH());
				stat.sIncrement(x.getS());
				stat.lIncrement(x.getL());
			}
		}
		return stat;
	}



	public static void histrogram(BufferedImage img) {
		double totalPixels = img.getWidth()*img.getHeight();

		StatisticsHelper a =  RGBandHSLValues(img);

		int[] red   = a.getR();
		int[] green = a.getG();
		int[] blue  = a.getB();
		int[] hue   = a.getH();
		int[] sat   = a.getS();
		int[] lig   = a.getL();

		double max;

		Group root = new Group();
		int help = 0;

		Rectangle rekt;
		Line line = new Line(256*3,0,256*3,800);
		line.setStroke(Color.WHITE);
		root.getChildren().add(line);

		for (int ii = 0; ii < 256; ii++) {
			max = max(red);
			rekt = new Rectangle(3,help = (int) Math.round((red[ii]/max*100)));
			rekt.setFill(Color.RED);
			rekt.setX(3*ii);
			rekt.setY(100 - help);
			root.getChildren().add(rekt);

			max = max(green);
			rekt = new Rectangle(3,help = (int) Math.round((green[ii]/max*100)));
			rekt.setFill(Color.GREEN);
			rekt.setX(3*ii);
			rekt.setY(200 - help);
			root.getChildren().add(rekt);

			max = max(blue);
			rekt = new Rectangle(3,help = (int) Math.round((blue[ii]/max*100)));
			rekt.setFill(Color.BLUE);
			rekt.setX(3*ii);
			rekt.setY(300 - help);
			root.getChildren().add(rekt);

			max = max(sat);
			rekt = new Rectangle(3,help = (int) Math.round((sat[ii]/max*100)));
			rekt.setFill(Color.WHITE);
			rekt.setX(3*ii);
			rekt.setY(400 - help);
			root.getChildren().add(rekt);

			max = max(lig);
			rekt = new Rectangle(3,help = (int) Math.round((lig[ii]/max*100)));
			rekt.setFill(Color.WHITE);
			rekt.setX(3*ii);
			rekt.setY(500 - help);
			root.getChildren().add(rekt);

		}

		max = max(hue);

		for (int ii = 0; ii < 361; ii++) {
			rekt = new Rectangle(2,help = (int) Math.round((hue[ii]/max*100)));
			rekt.setFill(Color.hsb(ii, 1, 1));
			rekt.setX(2*ii);
			rekt.setY(600 - help);
			root.getChildren().add(rekt);
		}

		Scene stat = new Scene(root, 800, 800, Color.rgb(66, 66, 66));
		Stage statistics = new Stage();
		statistics.setScene(stat);
		statistics.setTitle("Statistics");
		statistics.show();


	}




	public static double max(int[] a) {
		double max  = 0;

		for (int ii = 0; ii < a.length; ii++) {
			if (a[ii] > max) max = a[ii];
		}

		return max;
	}



}

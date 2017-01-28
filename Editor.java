import java.io.File;
import java.io.FileInputStream;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Editor extends Application {

	// This was inicially intended to be an application that displays initial image and resulting one so it uses javafx.
	// But then I decided that I don't really care about interface at this stage.

	// later it'd be cool to make it more user friendly!
	// as in for now, what3vr


	public static void main(String[] args) {
			launch(args);
	}



	@Override
	public void start(Stage stage) throws Exception {


		// this is the file to be processed
		// provide the path!

		File initFile = new File("/Users/XDXD/Downloads/IMG_4791.jpg");
		FileInputStream inp = new FileInputStream(initFile);
		Image image = new Image(inp);



		// how many output images there will be
		// also, variable time can be used to manipulate stuff


		for (int time = 0; time < 10; time++ ) {


			BufferedImage im = new BufferedImage((int)image.getWidth(), (int)image.getHeight(), BufferedImage.TYPE_INT_ARGB);
			im = ImageIO.read(initFile);

					try {


						Filter.modularis3(im,time);


						} catch (ColorException c) {}



			// directory where you output a file
			// math random is here to make up a name (collision is too unlikely)
			// added time variable, this way it's easy to sort them by which
			// was created first

			File hella = new File("/Users/XDXD/Desktop/genFil/"+ "evilArrayOfColor" + time + "hold" + (int) (9000000*Math.random()) + ".jpg");

			ImageIO.write(im, "jpg", hella );
			FileInputStream resulting = new FileInputStream(hella);


		}


		//////////////////////// STATISTICS BLOCK /////////////////////////////////////////
		
		// one problem with HSL is that saturation of black is undefined(tho low dark things may be fully
		//saturated), but us Humanz don't think of dark colors as very colourfull.
		
		BufferedImage im = new BufferedImage((int)image.getWidth(), (int)image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		im = ImageIO.read(initFile);
		Statistics.histrogram(im);
		
		////////////////////////////////   ????????????????????????????



		ImageView imageV = new ImageView(image);
		imageV.setFitHeight(807);
		imageV.setFitWidth(600);

		GridPane pane = new GridPane();
		pane.setHgap(20);
		pane.setVgap(20);

		pane.getChildren().add(imageV);
		VBox v = new VBox();
		v.getChildren().add(imageV);
		VBox vv = new VBox();
	//	vv.getChildren().add(ginx);

		pane.add(v, 0, 0);
		pane.add(vv, 1, 0);

		Scene scene = new Scene(pane, 2000, 2000);
		stage.setScene(scene);
		stage.show();

	}

}












//
//}
//}


//				for (int kk = 1; kk < 11; kk ++) {
//					Pixel a = new Pixel(im.getRGB(ii, ii));
//					if (Math.random()*5 >2) {
//						try{
//							a.setB(a.getB() + ((int) (Math.random() * 10)));
//							a.setR(a.getR() + ((int) (Math.random() * 10)));
//							a.setG(a.getB() + ((int) (Math.random() * 10)));
//						} catch (Exception c) {}
//					}
//					else {
//						try {
//							a.setB(a.getB() - ((int) a.getB()/kk));
//							a.setR(a.getR() - ((int) (a.getB()/kk)));
//							a.setG(a.getB() - ((int) (a.getB()/kk)));
//						} catch (Exception c) {}
//				}
//				}

//				a.setA(a.getA());


//				a.setA(255);
//				a.setB(0);
//				a.setR(0);
//				a.setG(0);

	//	im.setRGB(ii, jj, a.getBit());
//	}
//}

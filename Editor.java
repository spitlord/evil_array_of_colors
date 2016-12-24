import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

	public static void main(String[] args) {

	//	for (int time = 0; time < 4; time++ ) {
			launch(args);


	}

	@Override
	public void start(Stage stage) throws Exception {

		int pix;

		File initFile = new File("/Users/XDXD/Downloads/IMG_4791.jpg");
		FileInputStream inp = new FileInputStream(initFile);
		Image image = new Image(inp);

		for (int time = 0; time < 1000; time+=10 ) {

			BufferedImage im = new BufferedImage((int)image.getWidth(), (int)image.getHeight(), BufferedImage.TYPE_INT_ARGB);
			im = ImageIO.read(initFile);
	//		System.out.println(im.getHeight() + "  " + im.getWidth());




//			for (int ii = 0; ii <im.getWidth()-6; ii++) {
//				for (int jj = 0; jj <im.getHeight()-6; jj++) {
					try {


						Filter.modularis4(im,time);
//						int tt,ttt,tttt;
//
//						tt =   (int) (x.getB()*5) % 200 + 56;
//						ttt =  (int) (x.getG()*5) % 200 + 56;
//						tttt = (int) (x.getR()*5) % 200 + 56;
//
//
//						x.setARGB(255,tt,ttt,tttt);
//						Pixel y = new Pixel (im.getRGB(ii, jj));
//						Pixel z = Pixel.average(y, x);

//						z = Pixel.average(z, y);


//						if (x.getB() > 100)   x.setB(255-x.getR());
//						if (x.getR () > 100)   x.setR(255-x.getG());
//						if (x.getG () > 100)  x.setG(255-x.getB());
//
//
//						if (x.getB() < 50) x.setB(255-x.getB());
//						if (x.getR () < 50) x.setR(255-x.getR());
//						if (x.getG () < 50) x.setG(255-x.getG());



//						tt = (x.getB()+(jj+1)/(ii+1)*(x.getB()+1)) % 256;
//						ttt = (x.getG()+(ii+1)/(jj+1)*(x.getB()+1)) % 256;
//						tttt = (x.getR()+(jj*ii+1)/(ii+1)*(x.getB()+1)) % 256;


//	             //   	Pixel h = Pixel.average(x, v);
//
//						for (int kk = 0; kk<10; kk++) {
//							h = Pixel.average(v, h);
//						}
//
//						im.setRGB(ii, jj, z.getBit());

						} catch (ColorException c) {}
//
//			}
//			}


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


			File hella = new File("/Users/XDXD/Desktop/genFil/"+ (int) (9000000*Math.random()) + ".jpg");

			ImageIO.write(im, "jpg", hella );
			FileInputStream resulting = new FileInputStream(hella);


		}



	//	Image gin = new Image(resulting);

	//	ImageView ginx = new ImageView(gin);
	//	ginx.setFitHeight(807);
	//	ginx.setFitWidth(600);


		ImageView imageV = new ImageView(image);
		imageV.setFitHeight(807);
		imageV.setFitWidth(600);

		GridPane pane = new GridPane();
		pane.setHgap(20);
		pane.setVgap(20);


		//	pane.add(ginx, 0, 0);
	//	ginx.fitHeightProperty().bind(stage.heightProperty());
//		pane.getChildren().add(imageV);
		pane.getChildren().add(imageV);
		VBox v = new VBox();
		v.getChildren().add(imageV);
	//	ginx.fitHeightProperty().bind(stage.heightProperty());
		VBox vv = new VBox();
	//	vv.getChildren().add(ginx);

		pane.add(v, 0, 0);
		pane.add(vv, 1, 0);



//
//		Scene csene = new Scene(panee, 2000, 1200);
//		Stage second = new Stage();
//
//
//		second.setScene(csene);
//		second.show();
//
		Scene scene = new Scene(pane, 2000, 2000);
		stage.setScene(scene);
		stage.show();

	}

}


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

import java.util.*;

public class CLI {
	// Command Line Interface
	// So i can play with filters without recompiling.
	// usage: filter-code [parameters ...] [additional filters w/ parameters] -- input-file [additional input-files...]
	// runs filters in order on each of the input files and saves with prefix "filtered_"
	// see code for filter codes and # of parameters
	// Example:
	// $ java CLI m2 1.5 C M 1 0 1 -- file1.jpg file2.jpg
	public static void main(String[] args) throws Exception {
		int filternum= 0;
		ArrayList<String> filters   = new ArrayList<String>();
		Queue<Double> arguments = new LinkedList<Double>();
		while (!args[filternum].equals("--")){
			try{
				arguments.add(Double.parseDouble(args[filternum]));
			}catch(NumberFormatException e){
				filters.add(args[filternum]);
			}
			filternum++;
		}
		for (int i=filternum+1; i<args.length; i++){
			Queue<Double> filtParam = new LinkedList<Double>(arguments);
			try{
				System.out.printf("%s: ",args[i]);
				File initFile = new File(args[i]);
				FileInputStream inp = new FileInputStream(initFile);
				Image image = new Image(inp);
				BufferedImage im = new BufferedImage(
					(int)image.getWidth(),
					(int)image.getHeight(),
					BufferedImage.TYPE_INT_ARGB
				);
				im = ImageIO.read(initFile);

				for (int currentFilter=0;currentFilter < filters.size(); currentFilter++){
					String filterName = filters.get(currentFilter);
					System.out.printf("%s ",filterName);

					switch (filterName){
						case "g":
							Filter.greyscale(im);
							break;
						case "c":
							Filter.contrast(im,filtParam.remove());
							break;
						case "sm":
							Filter.sumModularis(im,filtParam.remove().intValue(),filtParam.remove().intValue(),filtParam.remove().intValue());
							break;
						case "sc":
							Filter.shiftComp(im,filtParam.remove().intValue());
							break;
						case "gc":
							Filter.greyscaleContrast(im,filtParam.remove());
							break;
						case "M":
							Filter.multiplix(im,filtParam.remove().intValue(),filtParam.remove().intValue(),filtParam.remove().intValue());
							break;
						case "m":
							Filter.modularis(im);
							break;
						case "m2":
							Filter.modularis2(im,filtParam.remove().intValue());
							break;
						case "m4":
							Filter.modularis4(im,filtParam.remove().intValue());
							break;
						case "m3":
							Filter.modularis3(im,filtParam.remove().intValue());
							break;
						case "m5":
							Filter.modularis5(im,filtParam.remove().intValue());
							break;
						case "m6":
							Filter.modularis6(im,filtParam.remove().intValue());
							break;
						case "m7":
							Filter.modularis7(im,filtParam.remove().intValue());
							break;
						case "mod":
							Filter.mod(im,filtParam.remove());
							break;
						case "G":
							Filter.glitch(im,filtParam.remove().intValue());
							break;
						case "l2h":
							Filter.lowToHigh(im,filtParam.remove());
							break;
						case "a":
							Filter.average(im,filtParam.remove(),filtParam.remove());
							break;
						case "ml2h":
							Filter.mildLowToHigh(im,filtParam.remove(),filtParam.remove());
							break;
						case "s":
							//Filter.shift(im,);
							break;
						case "C":
							Filter.cascade(im);
							break;
						case "ad":
							Filter.absDel(im);
							break;
						case "n":
							Filter.normalize(im);
							break;
						default:
							System.out.println(filterName+" filter not recagnized.");
					}
				}

				File hella = new File("filtered_"+args[i]);
				ImageIO.write(im, "jpg", hella );
				FileInputStream resulting = new FileInputStream(hella);
				System.out.println();
			}catch (Exception e){
				System.out.println("ERROR");
			}
		}
	}
}

package chore.veiw;

import java.util.ArrayList;
import java.util.Random;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SplashScreen {
	String url;
	Stage stage;

	public SplashScreen(String Url) {
		url = Url;
	}

	public void show() {
		Random random = new Random();

		ArrayList<String> txts = new ArrayList<String>();
		txts.add("Creating GUI...");
		txts.add("Loading images from choreSheet/images/icons...");
		txts.add("Making Action Listeners...");
		txts.add("Reading files...");
		txts.add("Intializing classes from src/chore.others/...");
		txts.add("Intializing classes from src/chore.generator/...");
		txts.add("Intializing classes from src/chore.veiw/...");

		Text txt = new Text(txts.get(random.nextInt(7)));
		txt.setStyle("-fx-font-size:50px;");
		ImageView img = new ImageView(new Image("file:images/splashScreens/splashScreen3.png"));
		
		VBox pane = new VBox(50, img, txt);
		pane.setAlignment(Pos.CENTER);
		pane.setStyle("-fx-background-color:#d8e9f0;");

		Scene scene = new Scene(pane);
		stage = new Stage();
		stage.setResizable(false);
		stage.setTitle("Loading");
		stage.setScene(scene);
		stage.getIcons().add(new Image(url));
		stage.show();
	}

	public void close() {
		stage.close();
	}
}

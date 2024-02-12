package chore.veiw;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Loading {
	String url;
	Text txt, txt2;
	Stage stage;

	public Loading(String Url) {
		url = Url;
	}

	public void show() {
		txt = new Text();
		txt.setText("Loading GUI");
		txt.setStyle("-fx-font-size:20;");

		txt2 = new Text();
		txt2.setText("");
		txt2.setStyle("-fx-font-size:20;");

		// ProgressIndicator load = new ProgressIndicator();
		// load.setPrefWidth(100);
		// load.setPrefHeight(100);
		ImageView load = new ImageView(new Image("file:images/loader/loader3.gif"));

		VBox txtBox = new VBox(5, txt, txt2);
		txtBox.setAlignment(Pos.CENTER);
		VBox pane = new VBox(30, load, txtBox);
		pane.setAlignment(Pos.CENTER);
		pane.setStyle("-fx-background-color:#d8e9f0;");

		Scene scene = new Scene(pane);
		scene.setFill(Color.BLACK);

		stage = new Stage();
		stage.setResizable(false);
		stage.setWidth(300);
		stage.setTitle("Loading");
		stage.setScene(scene);
		stage.getIcons().add(new Image(url));
		stage.show();
	}

	public void setLoadingText(String string) {
		txt.setText(string);
	}

	public void setLoadingText(String string, String string2) {
		txt.setText(string);
		txt2.setText(string2);
	}

	public void close() {
		stage.close();
	}
}

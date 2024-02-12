package chore.veiw;

import chore.others.Listener;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TryAgainBox {
	static Listener l;
	static String url;
	
	public TryAgainBox(String Url) {
		url = Url;
	}
	
	public void setListener(Listener li) {
		l = li;
	}
	
	public static void show(String message, String title) {
		Stage stage = new Stage();
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle(title);
		stage.setMinWidth(250);

		Label lbl = new Label();
		lbl.setText(message);
		
		Button btnOk = new Button("Try Again");
		btnOk.setOnAction(e -> {stage.close(); l.tryAgain();});

		Button btnClose = new Button("Close");
		btnClose.setOnAction(e -> {stage.show(); stage.close();});
		
		VBox pane = new VBox(20);
		HBox hbox = new HBox(10, btnClose, btnOk);
		pane.getChildren().addAll(lbl, hbox);
		pane.setAlignment(Pos.CENTER);
		pane.setStyle("-fx-background-color:#d8e9f0;");

		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.getIcons().add(new Image(url));
		stage.showAndWait();
	}
}

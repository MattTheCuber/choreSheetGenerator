package chore.veiw;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MessageBox {
	static String url;
	
	public MessageBox(String Url) {
		url = Url;
	}
	public static void show(String message, String title) {
		Stage stage = new Stage();
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle(title);
		stage.setMinWidth(250);

		Label lbl = new Label();
		lbl.setText(message);
		lbl.setStyle("-fx-font-size:20;");

		Button btnClose = new Button("Close");
		btnClose.setOnAction(e -> {stage.show(); stage.close();});
		
		VBox pane = new VBox(20, lbl, btnClose);
		VBox.setMargin(btnClose, new Insets(0, 5, 5, 500));
		pane.setAlignment(Pos.CENTER);
		pane.setStyle("-fx-background-color:#d8e9f0;");

		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.getIcons().add(new Image(url));
		stage.showAndWait();
	}
}

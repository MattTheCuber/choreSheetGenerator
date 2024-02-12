package chore.veiw;

import chore.others.Listener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UpdatePage {
	Listener l;
	String url;

	public UpdatePage(String Url) {
		url = Url;
	}

	public void setListener(Listener li) {
		l = li;
	}

	public void show(String message, String message2, String version) {
		Stage stage = new Stage();
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Version " + version + " is out!");
		stage.setMinWidth(250);
		
		Label heading = new Label();
		heading.setText("Version " + version + " is available");
		heading.setStyle("-fx-font-size:50px;");
		heading.setTextFill(Color.web("#f33535"));

		Label lbl = new Label();
		lbl.setText(message);
		heading.setStyle("-fx-font-size:20px;");
		heading.setTextFill(Color.web("#f33535"));
		
		Label lbl2 = new Label();
		lbl.setText(message2);
		heading.setStyle("-fx-font-size:20px;");
		heading.setTextFill(Color.web("#f33535"));
		
		Button btnClose = new Button("Continue");
		btnClose.setStyle("-fx-font-size:25px; -fx-background-color:#33425b;");
		btnClose.setTextFill(Color.WHITE);
		btnClose.setOnAction(e -> {
			stage.close();
			l.backClicked(1);
		});

		VBox pane = new VBox(20, heading, lbl, lbl2, btnClose);
		pane.setPadding(new Insets(5, 5, 5, 5));
		pane.setAlignment(Pos.CENTER);
		pane.setStyle("-fx-background-color:#d8e9f0;");

		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.getIcons().add(new Image(url));
		stage.show();
	}
}

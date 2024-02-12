package chore.veiw;

import chore.others.Listener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmationBox {
	static Listener l;
	static String url;

	public ConfirmationBox(String Url) {
		url = Url;
	}

	public void setListener(Listener li) {
		l = li;
	}

	public void show(String message, String title, String txt) {

		Stage stage = new Stage();
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle(title);
		stage.setMinWidth(250);

		Label lbl = new Label();
		lbl.setText(message);

		Button btnOk = new Button("Continue");
		btnOk.setOnAction(e -> {
			if (txt.equals("Close")) {
				stage.close();
				l.backClicked(1);
			} else {
				stage.close();
				l.backClicked(3);
			}
		});

		Button btnClose = new Button(txt);
		btnClose.setOnAction(e -> {
			if (txt.equals("Cancel")) {
				stage.close();
				l.backClicked(1);
			} else {
				stage.close();
			}
		});

		HBox hbox = new HBox(10, btnClose, btnOk);
		VBox pane = new VBox(20, lbl, hbox);
		pane.setPadding(new Insets(5, 5, 5, 5));
		pane.setAlignment(Pos.CENTER);
		pane.setStyle("-fx-background-color:#d8e9f0;");

		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.getIcons().add(new Image(url));
		stage.show();
	}
}

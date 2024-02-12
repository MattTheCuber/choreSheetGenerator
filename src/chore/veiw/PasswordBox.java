package chore.veiw;

import chore.others.Listener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PasswordBox {
	static Listener l;
	static String url;

	public PasswordBox(String Url) {
		url = Url;
	}

	public void setListener(Listener li) {
		l = li;
	}

	public void show(String text, String bText) {
		Stage stage = new Stage();
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Password");
		stage.setMinWidth(250);

		PasswordField pf = new PasswordField();
		pf.setPromptText("Type password here");
		pf.setMaxWidth(200);

		Label lbl = new Label();
		lbl.setText(text);

		Button btnOk = new Button(bText);
		btnOk.setOnAction(e -> {
			stage.close();
			l.checkPassword(pf.getText());
		});

		Button btnClose = new Button("Cancel");
		btnClose.setOnAction(e -> {
			stage.close();
			l.backClicked(1);
		});

		HBox hbox = new HBox(10, btnClose, btnOk);
		VBox pane = new VBox(20, lbl, pf, hbox);
		pane.setPadding(new Insets(5, 5, 5, 5));
		pane.setAlignment(Pos.CENTER);
		pane.setStyle("-fx-background-color:#d8e9f0;");

		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.getIcons().add(new Image(url));
		stage.show();
	}
}

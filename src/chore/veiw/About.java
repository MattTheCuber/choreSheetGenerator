package chore.veiw;

import chore.others.LOG;
import chore.others.Listener;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class About {
	Listener bl;
	ImageView close;
	Stage primaryStage;
	LOG LOG;
	String url, className = "About";

	public About(LOG log, String Url) {
		LOG = log;
		url = Url;
	}

	public void show(String version, String copyright, String proName) {
		primaryStage = new Stage();

		Text versionTxt = new Text("Version: " + version);
		versionTxt.setStyle("-fx-font-size:50px;");

		Text proNameTxt = new Text("Program Name: " + proName);
		proNameTxt.setStyle("-fx-font-size:50px;");

		Text copyrightTxt = new Text("Copyright ©: " + copyright);
		copyrightTxt.setStyle("-fx-font-size:50px;");

		Image one = new Image("file:images/icons/close2.png");
		Image two = new Image("file:images/icons/closePressed.png");
		ImageView logo = new ImageView(new Image("file:images/icons/mv-icon.png"));

		close = new ImageView(one);
		close.setOnMousePressed(e -> actionPerformed(e));
		close.setOnMouseEntered(e -> close.setImage(two));
		close.setOnMouseExited(e -> close.setImage(one));
		close.setStyle("-fx-font-size:25px;");

		HBox copyrightBox = new HBox(copyrightTxt, logo);
		HBox.setMargin(logo, new Insets(15, 0, 0, 5));
		VBox paneMain = new VBox(10, proNameTxt, versionTxt, copyrightBox, close);
		VBox.setMargin(close, new Insets(10, 5, 5, 620));
		paneMain.setPadding(new Insets(10, 10, 10, 10));
		paneMain.setStyle("-fx-background-color:#d8e9f0;");

		Scene scene = new Scene(paneMain);
		primaryStage.setScene(scene);
		primaryStage.setOnCloseRequest(e -> actionPerformed(e));
		primaryStage.setTitle("Chores");
		primaryStage.getIcons().add(new Image(url));
		primaryStage.show();
		LOG.println("StartUpView shown", className, new Throwable().getStackTrace()[0].getLineNumber(), 5);
	}

	public void setListener(Listener bli) {
		this.bl = bli;
	}

	public void actionPerformed(Event e) {
		LOG.println("Action performed", className, new Throwable().getStackTrace()[0].getLineNumber(), 3);
		Object source = (Object) e.getSource();
		if (bl != null) {
			if (source == primaryStage) {
				primaryStage.close();
				bl.closeClicked(2);
			} else if (source == close) {
				primaryStage.close();
				bl.closeClicked(5);
			}
		}
	}

	public void c(Event e) {
		primaryStage.close();
		actionPerformed(e);
	}
}
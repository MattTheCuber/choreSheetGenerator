package chore.veiw;

import chore.others.LOG;
import chore.others.Listener;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Statistics {
	Listener bl;
	ImageView close;
	Stage primaryStage;
	LOG LOG;
	String url, className = "About";

	public Statistics(LOG log, String Url) {
		LOG = log;
		url = Url;
	}

	public void show(Integer wc, Integer cc, Double level, Integer wm, Integer cm) {
		primaryStage = new Stage();
		
		Rectangle r = new Rectangle();
		r.setFill(Color.LIGHTBLUE);
		r.setWidth(75);
		
		Rectangle r2 = new Rectangle();
		r2.setFill(Color.DARKGRAY);
		r2.setWidth(100);

		int totalChores = cc + cm;
		int percent = cc / totalChores * 100;//TODO not right???????????????? percent = 0
		
		Text txt1 = new Text("Level: " + Double.toString(level).charAt(0));
		txt1.setStyle("-fx-font-size:50px;");

		Text txt2 = new Text("Total chores completed: " + cc);
		txt2.setStyle("-fx-font-size:50px;");

		Text txt3 = new Text("Total full weeks completed: " + wc);
		txt3.setStyle("-fx-font-size:50px;");

		Text txt4 = new Text("Total chores missed: " + cm);
		txt4.setStyle("-fx-font-size:50px;");

		Text txt5 = new Text("Total full weeks missed: " + wm);
		txt5.setStyle("-fx-font-size:50px;");

		Text txt6 = new Text(percent + "% of your chores you have completed");
		txt6.setStyle("-fx-font-size:50px;");

		Image one = new Image("file:icons/close2.png");
		Image two = new Image("file:icons/closePressed.png");

		close = new ImageView(one);
		close.setOnMousePressed(e -> actionPerformed(e));
		close.setOnMouseEntered(e -> close.setImage(two));
		close.setOnMouseExited(e -> close.setImage(one));
		close.setStyle("-fx-font-size:25px;");

		StackPane spane = new StackPane(r2, r, txt2);
		HBox hbox = new HBox(txt1, spane);
		
		VBox paneMain = new VBox(10, hbox, txt2, txt3, txt4, txt5, txt6, close);
		VBox.setMargin(close, new Insets(10, 5, 5, 620));
		paneMain.setPadding(new Insets(10, 10, 10, 10));
		paneMain.setStyle("-fx-background-color:#d8e9f0;");

		Scene scene = new Scene(paneMain);
		primaryStage.setScene(scene);
		primaryStage.setOnCloseRequest(e -> actionPerformed(e));
		primaryStage.setTitle("Chores");
		primaryStage.getIcons().add(new Image(url));
		primaryStage.show();
		LOG.println("Statistics shown", className, new Throwable().getStackTrace()[0].getLineNumber(), 5);
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
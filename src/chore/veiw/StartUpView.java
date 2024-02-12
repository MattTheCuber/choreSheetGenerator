package chore.veiw;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import chore.others.LOG;
import chore.others.Listener;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class StartUpView {
	Listener bl;
	Button julia, rachel, matthew, eli, lydia, mercy, plus;
	ImageView close, help;
	Button mncs, cacc;
	Stage primaryStage;
	Text plusTxt, time2, pmAm;
	Timeline tl;
	LOG LOG;
	int am, s;
	String url;
	VBox options, paneMain;
	Image one = new Image("file:images/icons/close.png");
	Image two = new Image("file:images/icons/closePressed.png");
	/*
	 * Image one2 = new Image("file:images/icons/help.png"); Image two2 = new
	 * Image("file:images/icons/helpPressed.png");
	 */
	MenuItem aboutItem, closeItem, backItem, statItem, mncsItem, caccItem;

	public StartUpView(LOG log, String Url) {
		LOG = log;
		url = Url;
	}

	public void startApp() {
		am = Calendar.AM;
		primaryStage = new Stage();

		time2 = new Text("--:--:-- ");
		time2.setFont(new Font("LCD", 70));
		time2.setFill(Color.web("#f33535"));

		pmAm = new Text("--");
		pmAm.setFont(new Font("LCD", 30));
		pmAm.setFill(Color.web("#f33535"));

		KeyFrame kf = new KeyFrame(Duration.millis(1000), e -> startTime());
		tl = new Timeline(kf);
		tl.setCycleCount(Animation.INDEFINITE);
		tl.play();

		aboutItem = new MenuItem("About " + bl.getProName() + "\t\t");
		aboutItem.setStyle("-fx-text-fill:#33425b;");
		aboutItem.setOnAction(e -> actionPerformed(e));

		closeItem = new MenuItem("Close\t\t\t\t");
		closeItem.setStyle("-fx-text-fill:#33425b;");
		closeItem.setOnAction(e -> actionPerformed(e));

		backItem = new MenuItem("Back\t\t\t\t\t");
		backItem.setStyle("-fx-text-fill:#33425b;");
		backItem.setOnAction(e -> actionPerformed(e));

		mncsItem = new MenuItem("Make new sheet\t\t");
		mncsItem.setStyle("-fx-text-fill:#33425b;");
		mncsItem.setOnAction(e -> actionPerformed(e));

		caccItem = new MenuItem("Clear all complete chores");
		caccItem.setStyle("-fx-text-fill:#33425b;");
		caccItem.setOnAction(e -> actionPerformed(e));

		/*
		 * statItem = new MenuItem("Statistics\t\t\t    ");
		 * statItem.setOnAction(e -> actionPerformed(e));
		 * statItem.setStyle("-fx-text-fill:#f33535;");
		 */

		Menu menu = new Menu("Menu");
		menu.setStyle("-fx-color:#f33535;");
		menu.getItems().addAll(aboutItem, caccItem, mncsItem, backItem, closeItem);

		MenuBar mb = new MenuBar(menu);
		mb.setPrefWidth(800);
		mb.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

		Text heading = new Text("Choose your chore sheet");
		heading.setId("heading");
		heading.setFill(Color.web("#f33535"));

		plusTxt = new Text("Options");
		plusTxt.setOnMousePressed(e -> actionPerformed(e));
		plusTxt.setId("plusT");

		mncs = new Button("Make New Sheet");
		mncs.setId("optionsB");
		mncs.setTextFill(Color.WHITE);
		mncs.setOnMousePressed(e -> actionPerformed(e));

		cacc = new Button("Clear all complete chores");
		cacc.setId("optionsB");
		cacc.setTextFill(Color.WHITE);
		cacc.setOnMousePressed(e -> actionPerformed(e));

		plus = new Button("+");
		plus.setPrefWidth(35);
		plus.setId("plusB");
		plus.setTextFill(Color.WHITE);
		plus.setOnAction(e -> actionPerformed(e));

		julia = new Button("Julia");
		julia.setOnAction(e -> c(e));
		julia.setId("people");
		julia.setTextFill(Color.WHITE);

		rachel = new Button("Rachel");
		rachel.setOnAction(e -> c(e));
		rachel.setId("people");
		rachel.setTextFill(Color.WHITE);

		matthew = new Button("Matthew");
		matthew.setOnAction(e -> c(e));
		matthew.setId("people");
		matthew.setTextFill(Color.WHITE);

		eli = new Button("Eli");
		eli.setOnAction(e -> c(e));
		eli.setId("people");
		eli.setTextFill(Color.WHITE);

		lydia = new Button("Lydia");
		lydia.setOnAction(e -> c(e));
		lydia.setId("people");
		lydia.setTextFill(Color.WHITE);

		mercy = new Button("Mercy");
		mercy.setOnAction(e -> c(e));
		mercy.setId("people");
		mercy.setTextFill(Color.WHITE);

		close = new ImageView(one);
		close.setOnMousePressed(e -> actionPerformed(e));
		close.setOnMouseEntered(e -> close.setImage(two));
		close.setOnMouseExited(e -> close.setImage(one));

		/*
		 * help = new ImageView(one2); help.setOnMousePressed(e ->
		 * actionPerformed(e)); help.setOnMouseEntered(e ->
		 * help.setImage(two2)); help.setOnMouseExited(e ->
		 * help.setImage(one2));
		 */

		HBox buttons = new HBox(10, julia, rachel, matthew, eli, lydia, mercy);
		HBox add = new HBox(10, plus, plusTxt);
		options = new VBox(5);

		StackPane spane = new StackPane(mb, heading);
		StackPane.setAlignment(heading, Pos.CENTER_LEFT);
		spane.setId("spane");

		HBox time = new HBox(time2, pmAm);
		time.setAlignment(Pos.CENTER);
		HBox.setMargin(pmAm, new Insets(-12.5, 0, 0, 0));
		time.setPadding(new Insets(0, 0, -32.5, 0));

		paneMain = new VBox(20, time, buttons, add, options, close);
		paneMain.setId("paneMain");
		VBox.setMargin(close, new Insets(10, 5, 10, 820));
		VBox.setMargin(add, new Insets(5, 5, 5, 50));
		VBox.setMargin(options, new Insets(5, 5, 5, 100));
		VBox.setMargin(buttons, new Insets(20, 15, 5, 50));
		VBox.setMargin(heading, new Insets(0, 0, 0, 5));

		BorderPane bpane = new BorderPane();
		bpane.setTop(spane);
		bpane.setCenter(paneMain);
		Scene scene = new Scene(bpane);
		scene.getStylesheets().add("file:css/StartUpView.css");

		primaryStage.setScene(scene);
		primaryStage.setOnCloseRequest(e -> actionPerformed(e));
		primaryStage.setTitle("Chores");
		primaryStage.setResizable(false);
		primaryStage.getIcons().add(new Image(url));

		/*
		 * try { Thread.sleep(500); } catch (InterruptedException e1) {
		 * LOG.println(e1.toString(), "StartUpView", new
		 * Throwable().getStackTrace()[1].getLineNumber(), 1); }
		 */

		bl.closeSplashScreen();
		primaryStage.show();
		LOG.println("StartUpView shown", "StartUpView", new Throwable().getStackTrace()[0].getLineNumber(), 5);
	}

	public void setListener(Listener bli) {
		this.bl = bli;
	}

	public void actionPerformed(Event e) {
		LOG.println("Action performed", "StartUpView", new Throwable().getStackTrace()[0].getLineNumber(), 3);
		Object source = (Object) e.getSource();
		if (bl != null) {
			if (source == primaryStage) {
				primaryStage.close();
				bl.closeClicked(2);
			} else if (source == close) {
				primaryStage.close();
				bl.closeClicked(5);
			} else if (source == julia) {
				bl.setYourName("Julia");
				bl.personClicked("Julia");
			} else if (source == rachel) {
				bl.setYourName("Rachel");
				bl.personClicked("Rachel");
			} else if (source == matthew) {
				bl.setYourName("Matthew");
				bl.personClicked("Matthew");
			} else if (source == eli) {
				bl.setYourName("Eli");
				bl.personClicked("Elias");
			} else if (source == lydia) {
				bl.setYourName("Lydia");
				bl.personClicked("Lydia");
			} else if (source == mercy) {
				bl.setYourName("Mercy");
				bl.personClicked("Mercy");
			} else if ((source == plus) || (source == plusTxt)) {
				toggle();
			} else if (source == aboutItem) {
				bl.about();
			} else if (source == backItem) {
				if ((bl.getYourName().equals("")) || (bl.getYourName().equals(null))) {// TODO
																						// ?????
					MessageBox.show("Please choose a user first.", "No user chosen");
				} else {
					primaryStage.close();
					bl.backClicked(2);
				}
			} else if (source == closeItem) {
				primaryStage.close();
				bl.closeClicked(0);
			} else if (source == statItem) {
				bl.openAllStatistics();
			} else if ((source == mncs)||(source == mncsItem)) {
				primaryStage.close();
				bl.makeNewChoresSheet();
			} else if ((source == cacc)||(source == caccItem)) {
				primaryStage.close();
				bl.clearAllCompleteChores();
			}
		}
	}

	private void toggle() {
		if (options.getChildren().size() == 0) {
			options.getChildren().addAll(mncs, cacc);
			primaryStage.setHeight(575);
		} else {
			options.getChildren().removeAll(mncs, cacc);
			primaryStage.setHeight(440);
		}

		if (plus.getText().equals("+")) {
			plus.setText("-");
		} else {
			plus.setText("+");
		}
	}

	public void c(Event e) {
		primaryStage.close();
		actionPerformed(e);
	}

	@SuppressWarnings("deprecation")
	public void startTime() {
		Date today = new Date();
		if (s != today.getSeconds()) {
			int h = today.getHours();
			int m = today.getMinutes();
			s = today.getSeconds();
			time2.setText(checkHour(h) + ":" + checkDigit(m) + " ");
			if (am != Calendar.AM) {
				am = Calendar.AM;
				pmAm.setText(new SimpleDateFormat("a").format(today));
			}
			checkFirst();
		}
	}

	private void checkFirst() {
		if (pmAm.getText().equals("--")) {
			pmAm.setText(new SimpleDateFormat("a").format(new Date()));
		}
	}

	public String checkDigit(int i) {
		String newString = "--";
		if (Integer.toString(i).length() == 1) {
			newString = "0" + Integer.toString(i);
		} else {
			newString = Integer.toString(i);
		}
		return newString;
	}

	public String checkHour(int i) {
		String newString = "--";
		switch (i) {
		case 13:
			newString = "1";
			break;
		case 14:
			newString = "2";
			break;
		case 15:
			newString = "3";
			break;
		case 16:
			newString = "4";
			break;
		case 17:
			newString = "5";
			break;
		case 18:
			newString = "6";
			break;
		case 19:
			newString = "7";
			break;
		case 20:
			newString = "8";
			break;
		case 21:
			newString = "9";
			break;
		case 22:
			newString = "10";
			break;
		case 23:
			newString = "11";
			break;
		case 24:
			newString = "12";
			break;
		default:
			newString = Integer.toString(i);
		}
		return newString;
	}
}
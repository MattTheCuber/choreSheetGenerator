package chore.veiw;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import chore.model.ChoresModel;
import chore.others.LOG;
import chore.others.Listener;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainView {
	TableView<ChoresModel> table;
	Stage primaryStage;
	Listener bl;
	ImageView close;
	StackPane spane;
	LOG LOG;
	String className = "MainView", url;
	MenuItem aboutItem, clearItem, closeItem, backItem, statItem;
	Image one = new Image("file:images/icons/close.png");
	Image two = new Image("file:images/icons/closePressed.png");
	Text time2, pmAm;
	Timeline tl;
	int s, am;

	public MainView(LOG log, String Url) {
		LOG = log;
		url = Url;
	}

	@SuppressWarnings({ "unchecked" })
	public void startChoreView() {
		am = Calendar.AM;
		bl.showLoadingScreen();
		checkDate();
		primaryStage = new Stage();

		/*
		 * double w = getWidth(levelDbl);
		 * 
		 * String levelStr = Long.toString(Math.round(Math.floor(levelDbl)));
		 * Rectangle back = new Rectangle(500, 50);
		 * back.setFill(Color.web("#29252c")); back.setStyle(
		 * "-fx-stroke:black; -fx-stroke-width:2.5;"); Rectangle front = new
		 * Rectangle( *w* 166.6, 50); // 0.25 = 125, 0.75 = 375... // 1/3 =
		 * 166.6..., 2/3 = 333.3... front.setFill(Color.web("#33425b"));
		 * front.setStyle("-fx-stroke:black; -fx-stroke-width:2.5;");
		 * 
		 * Text levelTxt = new Text("Level " + levelStr);
		 * levelTxt.setFill(Color.web("#f33535")); levelTxt.setStyle(
		 * "-fx-font-size:20px; -fx-font-weight:bold;");
		 */

		clearItem = new MenuItem("Clear complete chores");
		clearItem.setOnAction(e -> actionPerformed(e));
		clearItem.setStyle("-fx-text-fill:#33425b;");

		aboutItem = new MenuItem("About " + bl.getProName() + "\t    ");
		aboutItem.setOnAction(e -> actionPerformed(e));
		aboutItem.setStyle("-fx-text-fill:#33425b;");

		closeItem = new MenuItem("Close\t\t\t    ");
		closeItem.setStyle("-fx-text-fill:#33425b;");
		closeItem.setOnAction(e -> actionPerformed(e));

		backItem = new MenuItem("Back\t\t\t\t    ");
		backItem.setStyle("-fx-text-fill:#33425b;");
		backItem.setOnAction(e -> actionPerformed(e));

		/*
		 * statItem = new MenuItem("Statistics\t\t\t    ");
		 * statItem.setOnAction(e -> actionPerformed(e));
		 * statItem.setStyle("-fx-text-fill:#f33535;");
		 */

		Menu menu = new Menu("Menu");
		menu.setStyle("-fx-color:#f33535;");
		menu.getItems().addAll(clearItem, aboutItem, backItem, closeItem);

		MenuBar mb = new MenuBar(menu);
		mb.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

		Text heading = new Text(" " + bl.getYourName() + "'s Chores");
		heading.setId("heading");
		heading.setFill(Color.web("#f33535"));

		bl.setLoadingText("Making table");
		table = new TableView<ChoresModel>();
		table.setMaxHeight(356);
		table.setMaxWidth(1587);

		TableColumn<ChoresModel, String> colMon = new TableColumn<ChoresModel, String>("Monday");
		colMon.setMinWidth(264.1);
		colMon.setSortable(false);
		colMon.setCellValueFactory(new PropertyValueFactory<ChoresModel, String>("Monday"));
		colMon.setStyle("-fx-font-size:30px; -fx-text-fill:#33425b;");

		TableColumn<ChoresModel, String> colTue = new TableColumn<ChoresModel, String>("Tuesday");
		colTue.setMinWidth(264.1);
		colTue.setSortable(false);
		colTue.setCellValueFactory(new PropertyValueFactory<ChoresModel, String>("Tuesday"));
		colTue.setStyle("-fx-font-size:30px;");

		TableColumn<ChoresModel, String> colWed = new TableColumn<ChoresModel, String>("Wednesday");
		colWed.setMinWidth(264.1);
		colWed.setSortable(false);
		colWed.setCellValueFactory(new PropertyValueFactory<ChoresModel, String>("Wednesday"));
		colWed.setStyle("-fx-font-size:30px;");

		TableColumn<ChoresModel, String> colThu = new TableColumn<ChoresModel, String>("Thursday");
		colThu.setMinWidth(264.1);
		colThu.setSortable(false);
		colThu.setCellValueFactory(new PropertyValueFactory<ChoresModel, String>("Thursday"));
		colThu.setStyle("-fx-font-size:30px;");

		TableColumn<ChoresModel, String> colFri = new TableColumn<ChoresModel, String>("Friday");
		colFri.setMinWidth(264.1);
		colFri.setSortable(false);
		colFri.setCellValueFactory(new PropertyValueFactory<ChoresModel, String>("Friday"));
		colFri.setStyle("-fx-font-size:30px;");

		TableColumn<ChoresModel, String> colSat = new TableColumn<ChoresModel, String>("Saturday");
		colSat.setMinWidth(264.1);
		colSat.setSortable(false);
		colSat.setCellValueFactory(new PropertyValueFactory<ChoresModel, String>("Saturday"));
		colSat.setStyle("-fx-font-size:30px;");

		close = new ImageView(one);
		close.setOnMousePressed(e -> actionPerformed(e));
		close.setOnMouseEntered(e -> close.setImage(two));
		close.setOnMouseExited(e -> close.setImage(one));
		close.setStyle("-fx-font-size:25px;");

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

		bl.setLoadingText("Moving GUI to scene");
		// StackPane level = new StackPane(back, front, levelTxt);
		// StackPane.setAlignment(front, Pos.CENTER_LEFT);

		Region spacer = new Region();
		HBox bottom = new HBox(1525, spacer, close);
		HBox time = new HBox(time2, pmAm);
		time.setAlignment(Pos.CENTER);
		time.setPadding(new Insets(2.5, 0, 5, 0));
		HBox.setMargin(pmAm, new Insets(-10, 0, 0, 0));

		VBox paneMain = new VBox(10, heading, time, table, bottom);
		paneMain.setPadding(new Insets(10, 5, 2.5, 10));
		VBox.setMargin(time, new Insets(-20, 0, -10, 0));

		spane = new StackPane(paneMain);
		spane.setStyle("-fx-background-color:#d8e9f0;");

		table.getColumns().addAll(colMon, colTue, colWed, colThu, colFri, colSat);
		bl.setLoadingText("Loading chores into table", "This may take a while");
		table.setItems(bl.loadData());
		table.setOnMouseClicked(e -> actionPerformed(e));
		table.setFixedCellSize(147);
		/*
		 * table.setRowFactory(new Callback<TableView<ChoresModel>,
		 * TableRow<ChoresModel>>() {
		 * 
		 * @Override public TableRow<ChoresModel> call(TableView<ChoresModel>
		 * paramP) { return new TableRow<ChoresModel>() {
		 * 
		 * @Override protected void updateItem(ChoresModel paramT, boolean
		 * paramBoolean) { String style = "-fx-background-color: green;";
		 * setStyle(style);
		 * 
		 * super.updateItem(paramT, paramBoolean); } }; } });
		 *
		 * table.setRowFactory(tv -> new TableRow<ChoresModel>() {
		 * 
		 * @Override public void updateItem(ChoresModel item, boolean empty) {
		 * super.updateItem(item, empty); setStyle(
		 * "-fx-background-color:#33425b; -fx-text-fill:#f33535;"); } });
		 */

		StackPane spane2 = new StackPane(mb, heading);
		StackPane.setAlignment(heading, Pos.CENTER_LEFT);

		BorderPane bpane = new BorderPane();
		bpane.setTop(spane2);
		bpane.setCenter(spane);
		Scene scene = new Scene(bpane);
		scene.getStylesheets().add("file:css/StartUpView.css");

		bl.setLoadingText("Making stage");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setOnCloseRequest(e -> actionPerformed(e));
		primaryStage.setTitle(bl.getYourName() + "'s Chores");
		primaryStage.getIcons().add(new Image(url));
		bl.setLoadingText("Finished");
		bl.closeLoading();
		primaryStage.show();
		LOG.println("MainView shown", className, new Throwable().getStackTrace()[0].getLineNumber(), 5);
	}

	/*
	 * private double getWidth(Double levelDbl) { int lastChar =
	 * Integer.parseInt(Double.toString(levelDbl).substring(Double.toString(
	 * levelDbl).length() - 1)); int width = 0; switch (lastChar) { case 0:
	 * width = 0; break; case 1: width = 50; break; case 2: width = 100; break;
	 * case 3: width = 150; break; case 4: width = 200; break; case 5: width =
	 * 250; break; case 6: width = 300; break; case 7: width = 350; break; case
	 * 8: width = 400; break; case 9: width = 450; } return width; }
	 */

	public void checkDate() {
		Date d = new Date();
		if (d.toString().contains("Sat")) {
			if (bl.getIfAllComplete()) {
				LOG.println(5);
				LOG.println("All chore complete", className, new Throwable().getStackTrace()[0].getLineNumber(), 5);
			}
		}
	}

	public void setListener(Listener bli) {
		this.bl = bli;
	}

	@SuppressWarnings("rawtypes")
	public void actionPerformed(Event e) {
		LOG.println("Action performed", className, new Throwable().getStackTrace()[0].getLineNumber(), 3);
		Object source = (Object) e.getSource();
		if (bl != null) {
			if (source == close) {
				tl.stop();
				primaryStage.close();
				bl.closeClicked(5);
			} else if (source == primaryStage) {
				tl.stop();
				primaryStage.close();
				bl.closeClicked(2);
			} else if (source == table) {
				ObservableList<TablePosition> selectedCells = table.getSelectionModel().getSelectedCells();
				TablePosition selectedCell = selectedCells.get(0);
				int x = selectedCell.getColumn();
				int y = selectedCell.getRow();
				bl.finishChore(x, y);
			} else if (source == aboutItem) {
				bl.about();
			} else if (source == clearItem) {
				bl.clearTable();
			} else if (source == backItem) {
				tl.stop();
				primaryStage.close();
				bl.backClicked(1);
			} else if (source == closeItem) {
				tl.stop();
				primaryStage.close();
				bl.closeClicked(0);
			} else if (source == statItem) {
				bl.openStatistics();
			}
		}
	}

	public void addX(int x, int y, boolean addOrRemove) {
		ArrayList<ImageView> ivs = new ArrayList<ImageView>();
		ivs.add(new ImageView());
		ivs.add(new ImageView());
		ivs.add(new ImageView());
		ivs.add(new ImageView());
		ivs.add(new ImageView());
		ivs.add(new ImageView());
		ivs.add(new ImageView());
		ivs.add(new ImageView());
		ivs.add(new ImageView());
		ivs.add(new ImageView());
		ivs.add(new ImageView());
		ivs.add(new ImageView());
		ivs.add(new ImageView());
		ivs.add(new ImageView());
		ivs.add(new ImageView());

		int index = 0;
		if ((x == 0) && (y == 0)) {
			index = 0;
		} else if ((x == 0) && (y == 1)) {
			index = 1;
		} else if ((x == 1) && (y == 0)) {
			index = 2;
		} else if ((x == 1) && (y == 1)) {
			index = 3;
		} else if ((x == 2) && (y == 0)) {
			index = 4;
		} else if ((x == 2) && (y == 1)) {
			index = 5;
		} else if ((x == 3) && (y == 0)) {
			index = 6;
		} else if ((x == 3) && (y == 1)) {
			index = 7;
		} else if ((x == 4) && (y == 0)) {
			index = 8;
		} else if ((x == 4) && (y == 1)) {
			index = 9;
		} else if ((x == 5) && (y == 0)) {
			index = 10;
		} else if ((x == 5) && (y == 1)) {
			index = 11;
		}

		if (addOrRemove) {
			Image i;
			i = new Image("file:images/x/x.png");

			ImageView iv = ivs.get(index);
			iv.setImage(i);

			if ((x == 0) && (y == 0)) {
				iv.setTranslateX(13);
				iv.setTranslateY(-50);
			} else if ((x == 0) && (y == 1)) {
				iv.setTranslateX(13);
				iv.setTranslateY(98);
			} else if ((x == 1) && (y == 0)) {
				iv.setTranslateX(278);
				iv.setTranslateY(-50);
			} else if ((x == 1) && (y == 1)) {
				iv.setTranslateX(278);
				iv.setTranslateY(98);
			} else if ((x == 2) && (y == 0)) {
				iv.setTranslateX(543);
				iv.setTranslateY(-50);
			} else if ((x == 2) && (y == 1)) {
				iv.setTranslateX(543);
				iv.setTranslateY(98);
			} else if ((x == 3) && (y == 0)) {
				iv.setTranslateX(807);
				iv.setTranslateY(-50);
			} else if ((x == 3) && (y == 1)) {
				iv.setTranslateX(807);
				iv.setTranslateY(98);
			} else if ((x == 4) && (y == 0)) {
				iv.setTranslateX(1071);
				iv.setTranslateY(-50);
			} else if ((x == 4) && (y == 1)) {
				iv.setTranslateX(1071);
				iv.setTranslateY(98);
			} else if ((x == 5) && (y == 0)) {
				iv.setTranslateX(1338);
				iv.setTranslateY(-50);
			} else if ((x == 5) && (y == 1)) {
				iv.setTranslateX(1338);
				iv.setTranslateY(98);
			}
			spane.getChildren().add(iv);
			StackPane.setAlignment(iv, Pos.CENTER_LEFT);
		} else {
			clearXs();
			bl.loadCompleteData();// TODO adding unxed x
			// spane.getChildren().remove(ivs.get(index));
		}
	}

	public void clearXs() {
		VBox vbox = (VBox) spane.getChildren().get(0);
		spane.getChildren().clear();
		spane.getChildren().add(vbox);
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

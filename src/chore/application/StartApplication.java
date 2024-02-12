package chore.application;

import chore.controller.Controller;
import chore.model.ChoresModel;
import chore.model.DatabaseHandler;
import chore.model.Model;
import chore.model.PersonModel;
import chore.others.LOG;
import chore.veiw.About;
import chore.veiw.AllStatistics;
import chore.veiw.ConfirmationBox;
import chore.veiw.Loading;
import chore.veiw.MainView;
import chore.veiw.MessageBox;
import chore.veiw.PasswordBox;
import chore.veiw.SplashScreen;
import chore.veiw.StartUpView;
import chore.veiw.Statistics;
import chore.veiw.TryAgainBox;
import chore.veiw.UpdatePage;
import javafx.application.Application;
import javafx.stage.Stage;

public class StartApplication extends Application {
	String url;
	LOG log;
	ChoresModel cModel;
	PersonModel pModel;
	Model model;
	StartUpView suv;
	MainView mv;
	About about;
	DatabaseHandler dbh;
	Controller controller;
	TryAgainBox tsbx;
	MessageBox msgBx;
	Loading loading;
	Statistics stat;
	AllStatistics aStat;
	PasswordBox pb;
	ConfirmationBox cbox;
	UpdatePage updatePage;
	SplashScreen ss;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		url = setIcon();

		ss = new SplashScreen(url);
		ss.show();
		log = new LOG();

		dbh = new DatabaseHandler(log, dbh);
		cModel = new ChoresModel(log, dbh);
		pModel = new PersonModel(log, dbh);
		model = new Model(log, dbh);

		suv = new StartUpView(log, url);
		mv = new MainView(log, url);
		about = new About(log, url);
		stat = new Statistics(log, url);
		aStat = new AllStatistics(log, url);
		loading = new Loading(url);

		tsbx = new TryAgainBox(url);
		msgBx = new MessageBox(url);
		updatePage = new UpdatePage(url);
		cbox = new ConfirmationBox(url);
		pb = new PasswordBox(url);

		controller = new Controller(suv, mv, pModel, log, dbh, about, loading, stat, cModel, pb, model, aStat, ss,
				cbox);
		log.println("Application classes made", "StartApplication", new Throwable().getStackTrace()[0].getLineNumber(),
				5);

		loadStartData();
	}

	private void loadStartData() {
		log.setLoggginLevel(2);
		mv.setListener(controller);
		suv.setListener(controller);
		dbh.setListener(controller);
		tsbx.setListener(controller);
		updatePage.setListener(controller);
		pb.setListener(controller);
		cbox.setListener(controller);
		controller.checkDayOfWeek();

		if (controller.checkVersion() == true) {
			suv.startApp();
		} else {
			if (controller.getIfUpdateAvialible()) {
				ss.close();
				updatePage.show(controller.getUpdateMessage(),
						"You have the wrong version of " + model.getProgramName()
								+ " please get\nthe latest version from the cloud, or get Matthew",
						controller.getUpdateVersion());
			} // TODO
		}
	}

	public String setIcon() {
		return "file:images/icons/icon3-large.png";
	}
}

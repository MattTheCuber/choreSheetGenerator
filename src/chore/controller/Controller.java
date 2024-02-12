package chore.controller;

import java.util.Date;

import chore.generator.GController;
import chore.generator.GModel;
import chore.model.ChoresModel;
import chore.model.DatabaseHandler;
import chore.model.Model;
import chore.model.PersonModel;
import chore.others.LOG;
import chore.others.Listener;
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
import javafx.collections.ObservableList;

public class Controller implements Listener {
	PersonModel pModel;
	ChoresModel cModel;
	public LOG LOG;
	MainView mv;
	StartUpView suv;
	String className = "Controller";
	GController cgControl;
	GModel cgModel;
	DatabaseHandler dbh;
	About about;
	Loading loading;
	Statistics stat;
	PasswordBox pb;
	ConfirmationBox cbox;
	Model model;
	boolean cacc;
	AllStatistics as;
	SplashScreen ss;

	public Controller(StartUpView newSuv, MainView newMv, PersonModel newPersonModel, LOG log, DatabaseHandler dbha,
			About abouT, Loading lOading, Statistics s, ChoresModel CModel, PasswordBox PB, Model mOdel,
			AllStatistics aStat, SplashScreen SS, ConfirmationBox cb) {
		pModel = newPersonModel;
		cbox = cb;
		cModel = CModel;
		model = mOdel;
		mv = newMv;
		ss = SS;
		as = aStat;
		suv = newSuv;
		LOG = log;
		dbh = dbha;
		about = abouT;
		pb = PB;
		loading = lOading;
		stat = s;
		cgModel = new GModel(LOG);
		cgControl = new GController(cgModel, dbh, LOG);
		cgControl.setListener(this);
	}

	public String checkDay(int i) {
		String newDay = "";
		switch (i) {
		case 0:
			newDay = "Monday";
		case 1:
			newDay = "Tuesday";
		case 2:
			newDay = "Wednesday";
		case 3:
			newDay = "Thursday";
		case 4:
			newDay = "Friday";
		case 5:
			newDay = "Saturday";
		}
		if (i == 0) {
			newDay = "Monday";
		} else if (i == 1) {
			newDay = "Tuesday";
		} else if (i == 2) {
			newDay = "Wednesday";
		} else if (i == 3) {
			newDay = "Thursday";
		} else if (i == 4) {
			newDay = "Friday";
		} else if (i == 5) {
			newDay = "Saturday";
		}
		return newDay;
	}

	@Override
	public void finishChore(int x2, int y) {
		String x = checkDay(x2);
		String complete = pModel.checkIfComplete(x, y);

		if (complete.equals("yes")) {
			mv.addX(x2, y, false);
			pModel.completeChore(x, y + 1, "no");// no = uncheck
		} else if (complete.equals("no")) {
			mv.addX(x2, y, true);
			pModel.completeChore(x, y + 1, "yes");// yes = check
		} else {
			MessageBox.show("An error has occured, please restart this program.", "Error");
		}
	}

	@Override
	public void finishCompleteChore(int x2, int y) {
		mv.addX(x2, y, true);
	}

	@Override
	public ObservableList<ChoresModel> loadData() {
		ObservableList<ChoresModel> data = cModel.returnUserChores();
		LOG.println("Sending data to View", className, new Throwable().getStackTrace()[0].getLineNumber(), 5);
		return data;
	}

	@Override
	public void backClicked(int status) {
		if (status == 1) {
			suv.startApp();
		} else if (status == 2) {
			mv.startChoreView();
		} else if (status == 3) {
			pModel.clearAllCompleteChores();
			suv.startApp();
		}
	}

	@Override
	public void closeClicked(int status) {
		int num;
		if (status > 2) {
			LOG.println(5);
			LOG.println("Closing program", className, new Throwable().getStackTrace()[0].getLineNumber(), status);
			num = 0;
		} else {
			LOG.println(1);
			LOG.println("You should close the program by clicking close", className,
					new Throwable().getStackTrace()[0].getLineNumber(), status);
			num = 1;
		}
		LOG.close();
		System.exit(num);
	}

	@Override
	public void personClicked(String name) {
		pModel.setYourName(name);
		pModel.setStuff();
		mv.startChoreView();
	}

	@Override
	public void clearTable() {
		mv.clearXs();
		pModel.clearCompleteChores();
	}

	@Override
	public ChoresModel addRecord(String sql1, String sql2, String sql3, String sql4, String sql5, String sql6) {
		cModel = new ChoresModel(sql1, sql2, sql3, sql4, sql5, sql6, LOG);
		return cModel;
	}

	@Override
	public void setYourName(String name) {
		pModel.setYourName(name);
	}

	@Override
	public String getYourName() {
		return pModel.getYourName();
	}

	@Override
	public void tryAgain() {
		pModel.tryAgain();
	}

	@Override
	public boolean getIfAllComplete() {
		return pModel.getIfAllComplete();
	}

	@Override
	public void about() {
		about.show(model.getVersion(), model.getCopyright(), model.getProgramName());
	}

	public boolean checkVersion() {
		String compatible = model.getCompatibleVersion();
		if (compatible.equals(model.getVersion())) {
			return true;
		}
		return false;
	}

	@Override
	public void showLoadingScreen() {
		loading.show();
	}

	@Override
	public void closeLoading() {
		loading.close();
	}

	@Override
	public void setLoadingText(String string) {
		loading.setLoadingText(string);
	}

	@Override
	public void setLoadingText(String string, String string2) {
		loading.setLoadingText(string, string2);
	}

	@Override
	public String getProName() {
		return model.getProgramName();
	}

	@Override
	public void openStatistics() {
		/*
		 * stat.show(pModel.getCC(), pModel.getWC(), pModel.getLevel(),
		 * pModel.getWM(), pModel.getCM());
		 */
	}

	@SuppressWarnings("deprecation")
	public void checkDayOfWeek() {
		Date d = new Date();
		if (d.getDay() == 1) {
			// TODO plus CC & WC
			if (pModel.getWeekReset()) {
				/*
				 * pModel.getNumberOfChoresMissed();
				 * pModel.clearAllCompleteChores(); pModel.setWeekReset(true);
				 */
			}
		}
	}

	@Override
	public void openAllStatistics() {
		// as.show(model.getCC(), model.getWC(), model.getW(), model.getC());
	}

	@SuppressWarnings("unused")
	@Override
	public void checkPassword(String text) {
		String password = model.getPassword();
		if ((!password.equals("")) || (password != null)) {
			if (text.equals(password)) {
				cgControl.makeThem();
				if (cacc == true) {
					pModel.clearAllCompleteChores();
				}
				mv.startChoreView();
			} else {
				MessageBox.show("Wrong password", "Incorrect");
			}
		} else {
			pb.show("You have not created a password yet please create one now", "Create");
		}
	}

	@Override
	public void loadCompleteData() {
		pModel.loadCompletes();
	}

	@Override
	public void addNewWeeksAndChores(int i, int j) {
		// model.setC(j);
		// model.setW(i);
	}

	@Override
	public void closeSplashScreen() {
		ss.close();
	}

	@Override
	public void clearAllCompleteChores() {
		cbox.show("Are you would like to uncheck everyone's chore?", "Confirming", "Cancel");
	}

	@Override
	public void makeNewChoresSheet() {
		pb.show("You need to type the parent password\nbefore you may reset the chores.", "Generate");
	}

	public boolean getIfUpdateAvialible() {
		return model.getIfUpdateAvalible();
	}

	public String getUpdateMessage() {
		return model.getUpdateMessage();
	}

	public String getUpdateVersion() {
		return model.getUpdateVersion();
	}
}

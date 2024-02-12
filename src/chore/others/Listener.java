package chore.others;

import chore.model.ChoresModel;
import javafx.collections.ObservableList;

public interface Listener {
	public void backClicked(int status);
	public void closeClicked(int status);
	public void finishChore(int x, int y);
	public void personClicked(String name);
	public ObservableList<ChoresModel> loadData();
	public ChoresModel addRecord(String sql1, String sql2, String sql3, String sql4, String sql5, String sql6);
	public void setYourName(String string);
	public String getYourName();
	public void tryAgain();
	public void clearTable();
	public boolean getIfAllComplete();
	public void about();
	public void showLoadingScreen();
	public void closeLoading();
	public void setLoadingText(String string);
	public String getProName();
	public void openStatistics();
	void finishCompleteChore(int x2, int y);
	public void openAllStatistics();
	public void checkPassword(String text);
	public void loadCompleteData();
	public void addNewWeeksAndChores(int i, int j);
	public void closeSplashScreen();
	public void clearAllCompleteChores();
	public void makeNewChoresSheet();
	public void setLoadingText(String string, String string2);
}

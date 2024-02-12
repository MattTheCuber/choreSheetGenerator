package chore.model;

import chore.others.LOG;

public class PersonModel {
	private String yourName;
	boolean allComplete, weekReset;
	DatabaseHandler dbh;
	LOG LOG;
	String className = "Model";

	public PersonModel(LOG log, DatabaseHandler DBH) {
		LOG = log;
		dbh = DBH;
	}

	public void setStuff() {
		weekReset = dbh.getProgramData2(yourName);
	}

	public void completeChore(String x, int y, String whichWay) {
		dbh.complete(x, y, yourName, whichWay);
	}

	public void tryAgain() {
		dbh.connect("tryAgain() Model");
	}

	public void clearAllCompleteChores() {
		dbh.clearAllCompleteChores();
	}

	public void clearCompleteChores() {
		dbh.clearCompleteChores(yourName);
		loadCompletes();
	}

	public void loadCompletes() {
		dbh.loadCompleteData();
	}

	public boolean getIfAllComplete() {
		allComplete = dbh.getIfAllComplete(yourName);
		return allComplete;
	}

	public String checkIfComplete(String x, int y) {
		return dbh.checkIfComplete(x, y, yourName);
	}

	public String getYourName() {
		return this.yourName;
	}

	public void setYourName(String yourNewName) {
		this.yourName = yourNewName;
	}

	public boolean getWeekReset() {
		return this.weekReset;
	}

	public void setWeekReset(boolean b) {
		dbh.setProgramData(b, yourName);
		this.weekReset = b;
	}
}

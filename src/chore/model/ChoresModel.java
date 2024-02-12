package chore.model;

import chore.others.LOG;
import javafx.collections.ObservableList;

public class ChoresModel {
	private String monday, tuesday, wednesday, Thursday, friday, saturday;
	DatabaseHandler dbh;
	LOG LOG;
	static String className = "Model";

	public ChoresModel(LOG log, DatabaseHandler DBH) {
		LOG = log;
		dbh = DBH;
	}

	public void clearChores() {
		monday = null;
		tuesday = null;
		wednesday = null;
		Thursday = null;
		friday = null;
		saturday = null;
	}

	public void addRecord(String mondays, String tuesdays, String wednesdays, String Thursdays, String fridays,
			String saturdays) {
		this.monday = mondays;
		this.tuesday = tuesdays;
		this.wednesday = wednesdays;
		this.Thursday = Thursdays;
		this.friday = fridays;
		this.saturday = saturdays;
	}

	public ChoresModel(String mondays, String tuesdays, String wednesdays, String Thursdays, String fridays,
			String saturdays, LOG log) {
		this.monday = mondays;
		this.tuesday = tuesdays;
		this.wednesday = wednesdays;
		this.Thursday = Thursdays;
		this.friday = fridays;
		this.saturday = saturdays;
		this.LOG = log;
	}

	public String getMonday() {
		return monday;
	}
	

	public void setMonday(String monday) {
		this.monday = monday;
	}

	public String getTuesday() {
		return tuesday;
	}

	public void setTuesday(String tuesday) {
		this.tuesday = tuesday;
	}

	public String getWednesday() {
		return wednesday;
	}

	public void setWednesday(String wednesday) {
		this.wednesday = wednesday;
	}

	public String getThursday() {
		return Thursday;
	}

	public void setThursday(String Thursday) {
		this.Thursday = Thursday;
	}

	public String getFriday() {
		return friday;
	}

	public void setFriday(String friday) {
		this.friday = friday;
	}

	public String getSaturday() {
		return saturday;
	}

	public void setSaturday(String saturday) {
		this.saturday = saturday;
	}

	public ObservableList<ChoresModel> returnUserChores() {
		ObservableList<ChoresModel> data = dbh.loadChoresData();
		dbh.loadCompleteData();
		LOG.println("Sending data to controller", className, new Throwable().getStackTrace()[0].getLineNumber(), 5);
		return data;
	}
}

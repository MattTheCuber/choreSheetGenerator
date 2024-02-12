package chore.model;

import chore.others.LOG;

public class Model {
	private String compatibleVersion, password;
	boolean allComplete, weekReset, updateAvalible;
	DatabaseHandler dbh;
	LOG LOG;
	String className = "Model", updateMessage, updateVersion;

	public Model(LOG log, DatabaseHandler DBH) {
		LOG = log;
		dbh = DBH;
		
		password = dbh.getProgramData("Password");
		compatibleVersion = "1.1.0";//dbh.getProgramData("Compatible Version");
		updateVersion = "1.0.0";//dbh.getUpdateData("Version");
		updateMessage = "efuhebdbfvniurhbeghdbifuvgyurbfhnj";//dbh.getUpdateData("Discription");
		updateAvalible = true;//dbh.getUpdateAvaliblility();//TODO
	}

	public String getVersion() {
		return "1.0.0";
	}

	public String getProgramName() {
		return "Chore Sheet";
	}

	public String getCopyright() {
		return "2015 by Matthew Vine";
	}

	public String getCompatibleVersion() {
		return compatibleVersion;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String newPassword) {
		this.password = dbh.setPassword(newPassword);
	}

	public boolean getIfUpdateAvalible() {
		return updateAvalible;
	}

	public String getUpdateMessage() {
		return updateMessage;
	}

	public String getUpdateVersion() {
		return updateVersion;
	}
}

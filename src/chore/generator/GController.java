package chore.generator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import chore.model.DatabaseHandler;
import chore.others.LOG;
import chore.others.Listener;
import chore.veiw.TryAgainBox;

public class GController {
	GModel cgm;
	DatabaseHandler dbh;
	LOG LOG;
	String className = "ChoreGeneratorController";
	Statement stmt;
	Connection c;
	Listener l;

	public GController(GModel newCgm, DatabaseHandler newDbh, LOG log) {
		cgm = newCgm;
		dbh = newDbh;
		LOG = log;
	}

	public void setListener(Listener li) {
		l = li;
	}

	public void makeThem() {
		cgm.createChoresArrays(dbh);
		writeJulias();
		writeRachels();
		writeMatthews();
		writeElis();
		writeLydias();
		writeMercys();

		l.addNewWeeksAndChores(6, 72);
	}

	private void insertRow(String tableName, ArrayList<String> chore1, int index1, String dayOfWeek, int diff1) {
		System.out.println("Array size: " + chore1.size());
		System.out.println("Index size: " + index1);
		System.out.println();
		cgm.insertRow(tableName, dbh, chore1.get(index1), dayOfWeek);
		cgm.remove(dayOfWeek, diff1, index1);
	}

	private void insertRow2(String tableName, ArrayList<String> chore2, int index2, String dayOfWeek, int diff2) {
		System.out.println("Array size: " + chore2.size());
		System.out.println("Index size: " + index2);
		System.out.println();

		cgm.insertRow2(tableName, dbh, chore2.get(index2), dayOfWeek);
		cgm.remove(dayOfWeek, diff2, index2);
	}

	public boolean connect(String i) {
		boolean connected = false;
		c = null;
		stmt = null;
		LOG.println(5);
		try {
			Class.forName("org.sqlite.JDBC");
			LOG.println("Connected to org.sqlite.JDBC", className, new Throwable().getStackTrace()[0].getMethodName(),
					new Throwable().getStackTrace()[0].getLineNumber(), 5);
		} catch (Exception e) {
			LOG.println(1);
			LOG.println("connect() Unable to connect to org.sqlite.JDBC", className,
					e.getStackTrace()[1].getLineNumber(), 1);
			LOG.println(e.toString(), className, new Throwable().getStackTrace()[0].getMethodName(),
					e.getStackTrace()[1].getLineNumber(), 1);
			TryAgainBox.show("Unable to load chores please get Matthew", "Unable to connect");
			return connected;
		}
		try {
			c = DriverManager.getConnection("jdbc:sqlite:file:V:/Shared School/Chores/choreData.sqlite");
			LOG.println("Connecting to choreData.sqlite: from " + i, className,
					new Throwable().getStackTrace()[1].getLineNumber(), 5);
			connected = true;
		} catch (Exception e) {
			LOG.println(1);
			LOG.println("Unable to connect to choreData.sqlite", className, e.getStackTrace()[1].getLineNumber(), 1);
			LOG.println(e.toString(), className, e.getStackTrace()[1].getLineNumber(), 1);
			TryAgainBox.show("Unable to load chores please connect to the cloud", "Unable to connect");
			return connected;
		}
		return connected;
	}

	private void writeJulias() {
		try {
			Random random = new Random();
			int numMHard = random.nextInt(5 + 1);
			int numMHard2 = random.nextInt(4 + 1);

			int numTMedium = random.nextInt(6 + 1);
			int numTEasy = random.nextInt(6 + 1);

			int numWMedium = random.nextInt(7 + 1);
			int numWMedium2 = random.nextInt(6 + 1);

			int numThMedium = random.nextInt(4 + 1);
			int numThEasy = random.nextInt(8 + 1);

			int numFMedium = random.nextInt(6 + 1);
			int numFMedium2 = random.nextInt(5 + 1);

			int numSHard = random.nextInt(3 + 1);
			int numSHard2 = random.nextInt(2 + 1);

			if (connect("writeJulias")) {
				try {
					stmt = c.createStatement();
					String sql = "DELETE FROM Julia";
					stmt.executeUpdate(sql);

					stmt.close();
					c.close();
				} catch (Exception e) {
					LOG.println(1);
					LOG.println(e.toString(), className, new Throwable().getStackTrace()[0].getLineNumber(), 1);
					LOG.println("Removing old data failed", className,
							new Throwable().getStackTrace()[0].getLineNumber(), 1);
				}
			} else {
				LOG.println(1);
				LOG.println("Did not get connect", className, new Throwable().getStackTrace()[0].getLineNumber(), 1);
			}

			insertRow("Julia", cgm.getHardM(), numMHard, "Monday", 3);
			insertRow2("Julia", cgm.getHardM(), numMHard2, "Monday", 3);

			insertRow("Julia", cgm.getMediumT(), numTMedium, "Tuesday", 2);
			insertRow2("Julia", cgm.getEasyT(), numTEasy, "Tuesday", 1);

			insertRow("Julia", cgm.getMediumW(), numWMedium, "Wednesday", 2);
			insertRow2("Julia", cgm.getMediumW(), numWMedium2, "Wednesday", 2);

			insertRow("Julia", cgm.getMediumTh(), numThMedium, "Thursday", 2);
			insertRow2("Julia", cgm.getEasyTh(), numThEasy, "Thursday", 1);

			insertRow("Julia", cgm.getMediumF(), numFMedium, "Friday", 2);
			insertRow2("Julia", cgm.getMediumF(), numFMedium2, "Friday", 2);

			insertRow("Julia", cgm.getHardS(), numSHard, "Saturday", 3);
			insertRow2("Julia", cgm.getHardS(), numSHard2, "Saturday", 3);
		} catch (Error e) {
			LOG.println("Write eli's failed", className, new Throwable().getStackTrace()[0].getMethodName(),
					new Throwable().getStackTrace()[0].getLineNumber(), 0);
			System.exit(1);
		}
	}

	private void writeRachels() {
		try {
			Random random = new Random();
			int numMHard = random.nextInt(3 + 1);
			int numMMedium = random.nextInt(5 + 1);

			int numTMedium = random.nextInt(5 + 1);
			int numTMedium2 = random.nextInt(4 + 1);

			int numWMedium = random.nextInt(5 + 1);
			int numWMedium2 = random.nextInt(4 + 1);

			int numThMedium = random.nextInt(3 + 1);
			int numThEasy = random.nextInt(7 + 1);

			int numFMedium = random.nextInt(4 + 1);
			int numFEasy = random.nextInt(6 + 1);

			int numSHard = 1;
			int numSMedium = random.nextInt(6 + 1);

			if (connect("writeRachels")) {
				try {
					stmt = c.createStatement();
					String sql = "DELETE FROM Rachel";
					stmt.executeUpdate(sql);

					stmt.close();
					c.close();
				} catch (Exception e) {
					LOG.println(1);
					LOG.println(e.toString(), className, new Throwable().getStackTrace()[0].getLineNumber(), 1);
					LOG.println("Removing old data failed", className,
							new Throwable().getStackTrace()[0].getLineNumber(), 1);
				}
			} else {
				LOG.println(1);
				LOG.println("Did not get connect", className, new Throwable().getStackTrace()[0].getLineNumber(), 1);
			}

			insertRow("Rachel", cgm.getHardM(), numMHard, "Monday", 3);
			insertRow2("Rachel", cgm.getMediumM(), numMMedium, "Monday", 2);

			insertRow("Rachel", cgm.getMediumT(), numTMedium, "Tuesday", 2);
			insertRow2("Rachel", cgm.getMediumT(), numTMedium2, "Tuesday", 2);

			insertRow("Rachel", cgm.getMediumW(), numWMedium, "Wednesday", 2);
			insertRow2("Rachel", cgm.getMediumW(), numWMedium2, "Wednesday", 2);

			insertRow("Rachel", cgm.getMediumTh(), numThMedium, "Thursday", 2);
			insertRow2("Rachel", cgm.getEasyTh(), numThEasy, "Thursday", 1);

			insertRow("Rachel", cgm.getMediumF(), numFMedium, "Friday", 2);
			insertRow2("Rachel", cgm.getEasyF(), numFEasy, "Friday", 1);

			insertRow("Rachel", cgm.getHardS(), numSHard, "Saturday", 3);
			insertRow2("Rachel", cgm.getMediumS(), numSMedium, "Saturday", 2);
		} catch (Error e) {
			LOG.println("Write eli's failed", className, new Throwable().getStackTrace()[0].getMethodName(),
					new Throwable().getStackTrace()[0].getLineNumber(), 0);
			System.exit(1);
		}
	}//

	private void writeMatthews() {
		try {
			Random random = new Random();
			int numMHard = random.nextInt(2 + 1);
			int numMMedium = random.nextInt(4 + 1);

			int numTMedium = random.nextInt(3 + 1);
			int numTMedium2 = random.nextInt(2 + 1);

			int numWMedium = random.nextInt(3 + 1);
			int numWEasy = random.nextInt(7 + 1);

			int numThMedium = random.nextInt(2 + 1);
			int numThEasy = random.nextInt(6 + 1);

			int numFMedium = random.nextInt(3 + 1);
			int numFEasy = random.nextInt(5 + 1);

			int numSHard = random.nextInt(0 + 1);
			int numSMedium = random.nextInt(5 + 1);

			if (connect("writeMatthews")) {
				try {
					stmt = c.createStatement();
					String sql = "DELETE FROM Matthew";
					stmt.executeUpdate(sql);

					stmt.close();
					c.close();
				} catch (Exception e) {
					LOG.println(1);
					LOG.println(e.toString(), className, new Throwable().getStackTrace()[0].getLineNumber(), 1);
					LOG.println("Removing old data failed", className,
							new Throwable().getStackTrace()[0].getLineNumber(), 1);
				}
			} else {
				LOG.println(1);
				LOG.println("Did not get connect", className, new Throwable().getStackTrace()[0].getLineNumber(), 1);
			}

			insertRow("Matthew", cgm.getHardM(), numMHard, "Monday", 3);
			insertRow2("Matthew", cgm.getMediumM(), numMMedium, "Monday", 2);

			insertRow("Matthew", cgm.getMediumT(), numTMedium, "Tuesday", 2);
			insertRow2("Matthew", cgm.getMediumT(), numTMedium2, "Tuesday", 2);

			insertRow("Matthew", cgm.getMediumW(), numWMedium, "Wednesday", 2);
			insertRow2("Matthew", cgm.getEasyW(), numWEasy, "Wednesday", 1);

			insertRow("Matthew", cgm.getMediumTh(), numThMedium, "Thursday", 1);
			insertRow2("Matthew", cgm.getEasyTh(), numThEasy, "Thursday", 1);

			insertRow("Matthew", cgm.getMediumF(), numFMedium, "Friday", 2);
			insertRow2("Matthew", cgm.getEasyF(), numFEasy, "Friday", 1);

			insertRow("Matthew", cgm.getHardS(), numSHard, "Saturday", 3);
			insertRow2("Matthew", cgm.getMediumS(), numSMedium, "Saturday", 2);
		} catch (Error e) {
			LOG.println("Write eli's failed", className, new Throwable().getStackTrace()[0].getMethodName(),
					new Throwable().getStackTrace()[0].getLineNumber(), 0);
			System.exit(1);
		}
	}

	private void writeElis() {
		try {
			Random random = new Random();
			int numMHard = 1;
			int numMMedium = random.nextInt(3 + 1);

			int numTMedium = 1;
			int numTEasy = random.nextInt(5 + 1);

			int numWMedium = random.nextInt(2 + 1);
			int numWEasy = random.nextInt(6 + 1);

			int numThMedium = 1;
			int numThEasy = random.nextInt(5 + 1);

			int numFMedium = random.nextInt(2 + 1);
			int numFEasy = random.nextInt(4 + 1);

			int numSMedium = random.nextInt(4 + 1);
			int numSMedium2 = random.nextInt(3 + 1);

			if (connect("writeElis")) {
				try {
					stmt = c.createStatement();
					String sql = "DELETE FROM Eli";
					stmt.executeUpdate(sql);

					stmt.close();
					c.close();
				} catch (Exception e) {
					LOG.println(1);
					LOG.println(e.toString(), className, new Throwable().getStackTrace()[0].getLineNumber(), 1);
					LOG.println("Removing old data failed", className,
							new Throwable().getStackTrace()[0].getLineNumber(), 1);
				}
			} else {
				LOG.println(1);
				LOG.println("Did not get connect", className, new Throwable().getStackTrace()[0].getLineNumber(), 1);
			}

			insertRow("Eli", cgm.getHardM(), numMHard, "Monday", 3);
			insertRow2("Eli", cgm.getMediumM(), numMMedium, "Monday", 2);

			insertRow("Eli", cgm.getMediumT(), numTMedium, "Tuesday", 2);
			insertRow2("Eli", cgm.getEasyT(), numTEasy, "Tuesday", 1);

			insertRow("Eli", cgm.getMediumW(), numWMedium, "Wednesday", 2);
			insertRow2("Eli", cgm.getEasyW(), numWEasy, "Wednesday", 1);

			insertRow("Eli", cgm.getMediumTh(), numThMedium, "Thursday", 2);
			insertRow2("Eli", cgm.getEasyTh(), numThEasy, "Thursday", 1);

			insertRow("Eli", cgm.getMediumF(), numFMedium, "Friday", 2);
			insertRow2("Eli", cgm.getEasyF(), numFEasy, "Friday", 1);

			insertRow("Eli", cgm.getMediumS(), numSMedium, "Saturday", 2);
			insertRow2("Eli", cgm.getMediumS(), numSMedium2, "Saturday", 2);

		} catch (Error e) {
			LOG.println("Write Eli's failed", className, new Throwable().getStackTrace()[0].getMethodName(),
					new Throwable().getStackTrace()[0].getLineNumber(), 0);
			System.exit(1);
		}
	}

	private void writeLydias() {
		try {
			Random random = new Random();
			int numMMedium = random.nextInt(2 + 1);
			int numMMedium2 = 1;

			int numTEasy = random.nextInt(4 + 1);
			int numTEasy2 = random.nextInt(3 + 1);

			int numWMedium = random.nextInt(2 + 1);
			int numWEasy = random.nextInt(5 + 1);

			int numThEasy = random.nextInt(4 + 1);
			int numThEasy2 = random.nextInt(3 + 1);

			int numFMedium = 1;
			int numFEasy = random.nextInt(3 + 1);

			int numSMedium = random.nextInt(2 + 1);
			int numSEasy = random.nextInt(3 + 1);

			if (connect("writeLydia's")) {
				try {
					stmt = c.createStatement();
					String sql = "DELETE FROM Lydia";
					stmt.executeUpdate(sql);

					stmt.close();
					c.close();
				} catch (Exception e) {
					LOG.println(1);
					LOG.println(e.toString(), className, new Throwable().getStackTrace()[0].getLineNumber(), 1);
					LOG.println("Removing old data failed", className,
							new Throwable().getStackTrace()[0].getLineNumber(), 1);
				}
			} else {
				LOG.println(1);
				LOG.println("Did not get connect", className, new Throwable().getStackTrace()[0].getLineNumber(), 1);
			}

			insertRow("Lydia", cgm.getMediumM(), numMMedium, "Monday", 2);
			insertRow2("Lydia", cgm.getMediumM(), numMMedium2, "Monday", 2);

			insertRow("Lydia", cgm.getEasyT(), numTEasy, "Tuesday", 1);
			insertRow2("Lydia", cgm.getEasyT(), numTEasy2, "Tuesday", 1);

			insertRow("Lydia", cgm.getMediumW(), numWMedium, "Wednesday", 2);
			insertRow2("Lydia", cgm.getEasyW(), numWEasy, "Wednesday", 1);

			insertRow("Lydia", cgm.getEasyTh(), numThEasy, "Thursday", 1);
			insertRow2("Lydia", cgm.getEasyTh(), numThEasy2, "Thursday", 1);

			insertRow("Lydia", cgm.getMediumF(), numFMedium, "Friday", 2);
			insertRow2("Lydia", cgm.getEasyF(), numFEasy, "Friday", 1);

			insertRow("Lydia", cgm.getMediumS(), numSMedium, "Saturday", 2);
			insertRow2("Lydia", cgm.getEasyS(), numSEasy, "Saturday", 1);
		} catch (Error e) {
			LOG.println("Write Lydia's failed", className, new Throwable().getStackTrace()[0].getMethodName(),
					new Throwable().getStackTrace()[0].getLineNumber(), 0);
			System.exit(1);
		}
	}

	private void writeMercys() {
		try {
			Random random = new Random();
			int numMEasy = random.nextInt(2 + 1);
			int numMEasy2 = 1;

			int numTEasy = random.nextInt(2 + 1);
			int numTEasy2 = 1;

			int numWEasy = random.nextInt(4 + 1);
			int numWEasy2 = random.nextInt(3 + 1);

			int numThEasy = random.nextInt(2 + 1);
			int numThEasy2 = 1;

			int numFEasy = random.nextInt(2 + 1);
			int numFEasy2 = 1;

			int numSEasy = random.nextInt(2 + 1);
			int numSEasy2 = 0;

			if (connect("writeMercy's")) {
				try {
					stmt = c.createStatement();
					String sql = "DELETE FROM Mercy";
					stmt.executeUpdate(sql);

					stmt.close();
					c.close();
				} catch (Exception e) {
					LOG.println(1);
					LOG.println(e.toString(), className, new Throwable().getStackTrace()[0].getLineNumber(), 1);
					LOG.println("Removing old data failed", className,
							new Throwable().getStackTrace()[0].getLineNumber(), 1);
				}
			} else {
				LOG.println(1);
				LOG.println("Did not get connect", className, new Throwable().getStackTrace()[0].getLineNumber(), 1);
			}

			insertRow("Mercy", cgm.getEasyM(), numMEasy, "Monday", 1);
			insertRow2("Mercy", cgm.getEasyM(), numMEasy2, "Monday", 1);

			insertRow("Mercy", cgm.getEasyT(), numTEasy, "Tuesday", 1);
			insertRow2("Mercy", cgm.getEasyT(), numTEasy2, "Tuesday", 1);

			insertRow("Mercy", cgm.getEasyW(), numWEasy, "Wednesday", 1);
			insertRow2("Mercy", cgm.getEasyW(), numWEasy2, "Wednesday", 1);

			insertRow("Mercy", cgm.getEasyTh(), numThEasy, "Thursday", 1);
			insertRow2("Mercy", cgm.getEasyTh(), numThEasy2, "Thursday", 1);

			insertRow("Mercy", cgm.getEasyF(), numFEasy, "Friday", 1);
			insertRow2("Mercy", cgm.getEasyF(), numFEasy2, "Friday", 1);

			insertRow("Mercy", cgm.getEasyS(), numSEasy, "Saturday", 1);
			insertRow2("Mercy", cgm.getEasyS(), numSEasy2, "Saturday", 1);
		} catch (Error e) {
			LOG.println("Write Mercy's failed", className, new Throwable().getStackTrace()[0].getMethodName(),
					new Throwable().getStackTrace()[0].getLineNumber(), 0);
			System.exit(1);
		}
	}
}

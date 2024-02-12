package chore.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import chore.others.LOG;
import chore.others.Listener;
import chore.veiw.TryAgainBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DatabaseHandler {
	ObservableList<ChoresModel> data;
	public Connection c;
	public Statement stmt;
	LOG LOG;
	Listener bl;
	String className = "DatabaseHandler";
	ResultSet rs;

	public DatabaseHandler(LOG log, DatabaseHandler dbh) {
		LOG = log;
	}

	public void setListener(Listener bli) {
		this.bl = bli;
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
			stmt = c.createStatement();
		} catch (Exception e) {
			LOG.println(1);
			LOG.println("Unable to connect to choreData.sqlite", className, e.getStackTrace()[1].getLineNumber(), 1);
			LOG.println(e.toString(), className, e.getStackTrace()[1].getLineNumber(), 1);
			TryAgainBox.show("Unable to load chores please connect to the cloud", "Unable to connect");
			return connected;
		}
		return connected;
	}

	public ObservableList<ChoresModel> loadChoresData() {
		boolean isConnected = false;
		data = FXCollections.observableArrayList();

		isConnected = connect(new Throwable().getStackTrace()[0].getMethodName());
		if (isConnected) {
			try {
				rs = stmt.executeQuery("SELECT * FROM " + bl.getYourName());

				ArrayList<String> s1 = new ArrayList<String>();
				ArrayList<String> s2 = new ArrayList<String>();

				while (rs.next()) {
					String sql1 = rs.getString("Chore1");
					s1.add(sql1);

					String sql2 = rs.getString("Chore2");
					s2.add(sql2);
				}

				data.add(bl.addRecord(s1.get(0), s1.get(1), s1.get(2), s1.get(3), s1.get(4), s1.get(5)));
				data.add(bl.addRecord(s2.get(0), s2.get(1), s2.get(2), s2.get(3), s2.get(4), s2.get(5)));

				close(true);
			} catch (Exception e) {
				LOG.println(1);
				LOG.println(e.toString(), className, 95, 1);
			}
		} else {
			LOG.println(1);
			LOG.println(new Throwable().getStackTrace()[0].getMethodName() + " did not get a connection", className,
					new Throwable().getStackTrace()[1].getLineNumber(), 1);
		}

		LOG.println(5);
		LOG.println("Sending data to model", className, new Throwable().getStackTrace()[0].getLineNumber(), 5);
		return data;
	}

	private void close(boolean closeRs) {
		try {
			c.close();
			stmt.close();
			if (closeRs == true) {
				rs.close();
			}
		} catch (SQLException e) {
			LOG.println(1);
			LOG.println(e.toString(), className, e.getStackTrace()[1].getLineNumber(), 1);
		}
	}

	public void loadCompleteData() {
		boolean isConnected = false;
		data = FXCollections.observableArrayList();

		isConnected = connect(new Throwable().getStackTrace()[0].getMethodName());
		if (isConnected) {
			try {
				rs = stmt.executeQuery("SELECT * FROM " + bl.getYourName() + ";");

				int x = 0;
				while (rs.next()) {
					String sql1 = rs.getString("Complete1");
					if (sql1.equals("yes")) {
						bl.finishCompleteChore(x, 0);
					}

					String sql2 = rs.getString("Complete2");
					if (sql2.equals("yes")) {
						bl.finishCompleteChore(x, 1);
					}
					x++;
				}

				close(true);
			} catch (Exception e) {
				LOG.println(1);
				LOG.println(e.toString(), className, e.getStackTrace()[1].getLineNumber(), 1);
			}
		} else {
			LOG.println(1);
			LOG.println(new Throwable().getStackTrace()[0].getMethodName() + " did not get a connection", className,
					new Throwable().getStackTrace()[1].getLineNumber(), 1);
		}
	}

	public void complete(String day, int yIndex, String tableName, String whichWay) {
		boolean isConnected = false;

		isConnected = connect(new Throwable().getStackTrace()[0].getMethodName());
		if (isConnected) {
			try {
				String sql = "UPDATE " + tableName + " set Complete" + yIndex + " = '" + whichWay + "' where Day = '"
						+ day + "';";
				System.out.println(sql);
				stmt.executeUpdate(sql);

				close(false);
			} catch (Exception e) {
				LOG.println(e.toString(), className, e.getStackTrace()[1].getLineNumber(), 1);
			}
		} else {
			LOG.println(new Throwable().getStackTrace()[0].getMethodName() + " did not get a connection", className,
					new Throwable().getStackTrace()[1].getLineNumber(), 1);
		}
	}

	public void clearAllCompleteChores() {
		boolean isConnected = false;

		isConnected = connect(new Throwable().getStackTrace()[0].getMethodName());
		if (isConnected) {
			try {
				String sql = "UPDATE Julia set Complete1 = 'no';";
				stmt.executeUpdate(sql);
				sql = "UPDATE Julia set Complete2 = 'no';";
				stmt.executeUpdate(sql);
				sql = "UPDATE Rachel set Complete1 = 'no';";
				stmt.executeUpdate(sql);
				sql = "UPDATE Rachel set Complete2 = 'no';";
				stmt.executeUpdate(sql);
				sql = "UPDATE Matthew set Complete1 = 'no';";
				stmt.executeUpdate(sql);
				sql = "UPDATE Matthew set Complete2 = 'no';";
				stmt.executeUpdate(sql);
				sql = "UPDATE Eli set Complete1 = 'no';";
				stmt.executeUpdate(sql);
				sql = "UPDATE Eli set Complete2 = 'no';";
				stmt.executeUpdate(sql);
				sql = "UPDATE Lydia set Complete1 = 'no';";
				stmt.executeUpdate(sql);
				sql = "UPDATE Lydia set Complete2 = 'no';";
				stmt.executeUpdate(sql);
				sql = "UPDATE Mercy set Complete1 = 'no';";
				stmt.executeUpdate(sql);
				sql = "UPDATE Mercy set Complete2 = 'no';";
				stmt.executeUpdate(sql);

				close(false);
			} catch (Exception e) {
				LOG.println(e.toString(), className, e.getStackTrace()[1].getLineNumber(), 1);
			}
		} else {
			LOG.println(new Throwable().getStackTrace()[0].getMethodName() + " did not get a connection", className,
					new Throwable().getStackTrace()[1].getLineNumber(), 1);
		}
	}

	public void clearCompleteChores(String yourName) {
		boolean isConnected = false;

		isConnected = connect(new Throwable().getStackTrace()[0].getMethodName());
		if (isConnected) {
			try {
				String sql = "UPDATE " + yourName + " set Complete1 = 'no';";
				stmt.executeUpdate(sql);
				sql = "UPDATE " + yourName + " set Complete2 = 'no';";
				stmt.executeUpdate(sql);

				close(false);
			} catch (Exception e) {
				LOG.println(1);
				LOG.println(e.toString(), className, e.getStackTrace()[1].getLineNumber(), 1);
			}
		} else {
			LOG.println(1);
			LOG.println(new Throwable().getStackTrace()[0].getMethodName() + " did not get a connection", className,
					new Throwable().getStackTrace()[1].getLineNumber(), 1);
		}
	}

	public ArrayList<String> getArray(int i, String string) {
		boolean isConnected = false;
		ArrayList<String> list = new ArrayList<String>();

		isConnected = connect(new Throwable().getStackTrace()[0].getMethodName());
		if (isConnected) {
			try {
				ResultSet rs = stmt.executeQuery("SELECT * FROM allChores");

				while (rs.next()) {
					String sql1 = rs.getString("Chore");
					String sql2 = rs.getString("Day");
					int sql3 = rs.getInt("Differculty");

					if ((sql2.equals(string)) && (sql3 == i)) {
						list.add(sql1);
					}
				}

				close(true);
			} catch (Exception e) {
				LOG.println(1);
				LOG.println(e.toString(), className, e.getStackTrace()[1].getLineNumber(), 1);
			}
		} else {
			LOG.println(1);
			LOG.println(new Throwable().getStackTrace()[0].getMethodName() + " did not get a connection", className,
					new Throwable().getStackTrace()[1].getLineNumber(), 1);
		}
		/*
		 * String diff = ""; if (i == 3) { diff = "Hard"; } else if (i == 2) {
		 * diff = "Medium"; } else if (i == 1) { diff = "Easy"; }
		 * 
		 * System.out.println(string + "'s differculty: " + diff + "; " + string
		 * + "'s size is " + list.size());
		 */

		return list;
	}

	public void insertRow(String tableName, String chore1, String chore2, String dayOfWeek) {
		boolean isConnected = false;

		isConnected = connect(new Throwable().getStackTrace()[0].getMethodName());
		if (isConnected) {
			try {
				String sql = "INSERT INTO " + tableName + " (Day, Chore1, Chore2, Complete1, Complete2) " + "VALUES ('"
						+ dayOfWeek + "', '" + chore1 + "', '" + chore2 + "', 'no', 'no');";
				stmt.executeUpdate(sql);

				close(false);
			} catch (Exception e) {
				LOG.println(1);
				LOG.println(e.toString(), className, e.getStackTrace()[1].getLineNumber(), 1);
			}
		} else {
			LOG.println(1);
			LOG.println(new Throwable().getStackTrace()[0].getMethodName() + " did not get a connection", className,
					new Throwable().getStackTrace()[1].getLineNumber(), 1);
		}
	}

	public boolean getIfAllComplete(String name) {
		boolean allComplete = true;
		boolean isConnected = false;

		isConnected = connect(new Throwable().getStackTrace()[0].getMethodName());
		if (isConnected) {
			try {
				ResultSet rs = stmt.executeQuery("SELECT * FROM " + name);

				while (rs.next()) {
					String sql1 = rs.getString("Complete1");
					String sql2 = rs.getString("Complete2");

					if ((sql1.equals("no")) || (sql2.equals("no"))) {
						allComplete = false;
					}
				}

				close(true);
			} catch (Exception e) {
				LOG.println(1);
				LOG.println(e.toString(), className, e.getStackTrace()[1].getLineNumber(), 1);
			}
		} else {
			LOG.println(1);
			LOG.println(new Throwable().getStackTrace()[0].getMethodName() + " did not get a connection", className,
					new Throwable().getStackTrace()[1].getLineNumber(), 1);
		}

		return allComplete;
	}

	public String getProgramData(String string) {
		String data = "";
		boolean isConnected = false;

		isConnected = connect(new Throwable().getStackTrace()[0].getMethodName());
		if (isConnected) {
			try {
				ResultSet rs = stmt.executeQuery("SELECT * FROM 'Program Data'");

				data = rs.getString(string);

				close(true);
			} catch (Exception e) {
				LOG.println(1);
				LOG.println(e.toString(), className, e.getStackTrace()[1].getLineNumber(), 1);
			}
		} else {
			LOG.println(1);
			LOG.println(new Throwable().getStackTrace()[0].getMethodName() + " did not get a connection", className,
					new Throwable().getStackTrace()[1].getLineNumber(), 1);
		}

		return data;
	}

	public String checkIfComplete(String x, int y, String tableName) {
		String complete = "unknown";
		boolean isConnected = false;

		isConnected = connect(new Throwable().getStackTrace()[0].getMethodName());
		if (isConnected) {
			try {
				ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);

				while (rs.next()) {
					if (rs.getString("Day").equals(x)) {
						if (y == 0) {
							y = 1;
						} else {
							y = 2;
						}
						complete = rs.getString("Complete" + y);
					}
				}

				close(true);
			} catch (Exception e) {
				LOG.println(1);
				LOG.println(e.toString(), className, e.getStackTrace()[1].getLineNumber(), 1);
			}
		} else {
			LOG.println(1);
			LOG.println(new Throwable().getStackTrace()[0].getMethodName() + " did not get a connection", className,
					new Throwable().getStackTrace()[1].getLineNumber(), 1);
		}

		return complete;
	}

	public Integer getStatistic(String string, String name) {
		int data = 0;
		boolean isConnected = false;

		isConnected = connect(new Throwable().getStackTrace()[0].getMethodName());
		if (isConnected) {
			try {
				ResultSet rs = stmt.executeQuery("SELECT * FROM Statistics");

				while (rs.next()) {
					if (rs.getString(name).equals(string)) {
						data = rs.getInt(string);
					}
				}

				close(true);
			} catch (Exception e) {
				LOG.println(1);
				LOG.println(e.toString(), className, e.getStackTrace()[1].getLineNumber(), 1);
			}
		} else {
			LOG.println(1);
			LOG.println(new Throwable().getStackTrace()[0].getMethodName() + " did not get a connection", className,
					new Throwable().getStackTrace()[1].getLineNumber(), 1);
		}

		return data;
	}

	public Double getLevel(String string) {
		double level = 1.0;
		boolean isConnected = false;

		isConnected = connect(new Throwable().getStackTrace()[0].getMethodName());
		if (isConnected) {
			try {
				ResultSet rs = stmt.executeQuery("SELECT * FROM Statistics");

				while (rs.next()) {
					if (rs.getString("Julia").equals(string)) {
						level = rs.getDouble("Level");
					}
				}

				close(true);
			} catch (Exception e) {
				LOG.println(1);
				LOG.println(e.toString(), className, e.getStackTrace()[1].getLineNumber(), 1);
			}
		} else {
			LOG.println(1);
			LOG.println(new Throwable().getStackTrace()[0].getMethodName() + " did not get a connection", className,
					new Throwable().getStackTrace()[1].getLineNumber(), 1);
		}

		return level;
	}

	public void setProgramData(boolean b, String yourName) {
		boolean isConnected = false;

		isConnected = connect(new Throwable().getStackTrace()[0].getMethodName());
		if (isConnected) {
			try {
				String sql = "UPDATE " + yourName + " set Week reset = '" + b + "';";
				stmt.executeUpdate(sql);

				close(false);
			} catch (Exception e) {
				LOG.println(1);
				LOG.println(e.toString(), className, e.getStackTrace()[1].getLineNumber(), 1);
			}
		} else {
			LOG.println(1);
			LOG.println(new Throwable().getStackTrace()[0].getMethodName() + "  did not get a connection", className,
					new Throwable().getStackTrace()[1].getLineNumber(), 1);
		}
	}

	public boolean getProgramData2(String yourName) {
		boolean data = false;
		boolean isConnected = false;

		isConnected = connect(new Throwable().getStackTrace()[0].getMethodName());
		if (isConnected) {
			try {
				ResultSet rs = stmt.executeQuery("SELECT * FROM Statistics");
				data = rs.getBoolean("Week reset");

				close(true);
			} catch (Exception e) {
				LOG.println(1);
				LOG.println(e.toString(), className, e.getStackTrace()[1].getLineNumber(), 1);
			}
		} else {
			LOG.println(1);
			LOG.println(new Throwable().getStackTrace()[0].getMethodName() + " did not get a connection", className,
					new Throwable().getStackTrace()[1].getLineNumber(), 1);
		}

		return data;
	}

	public double setStatistic(String string, double i, String yourName) {
		boolean isConnected = false;
		double data = 0;

		isConnected = connect(new Throwable().getStackTrace()[0].getMethodName());
		if (isConnected) {
			try {
				ResultSet rs = stmt.executeQuery("SELECT * FROM Statistics");

				String sql = "UPDATE " + yourName + " set " + string + " = " + i + ";";
				stmt.executeUpdate(sql);

				while (rs.next()) {
					if (rs.getString("Person").equals(yourName)) {
						data = rs.getDouble(string);
					}
				}

				close(true);
			} catch (Exception e) {
				LOG.println(1);
				LOG.println(e.toString(), className, e.getStackTrace()[1].getLineNumber(), 1);
			}
		} else {
			LOG.println(1);
			LOG.println(new Throwable().getStackTrace()[0].getMethodName() + " did not get a connection", className,
					new Throwable().getStackTrace()[1].getLineNumber(), 1);
		}
		return data;
	}

	public int setAllStatistic(String string, int i) {
		boolean isConnected = false;
		int data = 0;

		isConnected = connect(new Throwable().getStackTrace()[0].getMethodName());
		if (isConnected) {
			try {
				ResultSet rs = stmt.executeQuery("SELECT * FROM Statistics");

				String sql = "UPDATE allStatistics set " + string + " = " + i + ";";
				stmt.executeUpdate(sql);

				data = rs.getInt(string);

				close(true);
			} catch (Exception e) {
				LOG.println(1);
				LOG.println(e.toString(), className, e.getStackTrace()[1].getLineNumber(), 1);
			}
		} else {
			LOG.println(1);
			LOG.println(new Throwable().getStackTrace()[0].getMethodName() + " did not get a connection", className,
					new Throwable().getStackTrace()[1].getLineNumber(), 1);
		}
		return data;
	}

	public String setPassword(String newPassword) {
		boolean isConnected = false;
		String data = "";

		isConnected = connect(new Throwable().getStackTrace()[0].getMethodName());
		if (isConnected) {
			try {
				ResultSet rs = stmt.executeQuery("SELECT * FROM Statistics");

				String sql = "UPDATE allStatistics set Password = '" + newPassword + "';";
				stmt.executeUpdate(sql);

				data = rs.getString("Password");

				close(true);
			} catch (Exception e) {
				LOG.println(1);
				LOG.println(e.toString(), className, e.getStackTrace()[1].getLineNumber(), 1);
			}
		} else {
			LOG.println(1);
			LOG.println(new Throwable().getStackTrace()[0].getMethodName() + " did not get a connection", className,
					new Throwable().getStackTrace()[1].getLineNumber(), 1);
		}
		return data;
	}

	public int setStatistic(String string, int d, String yourName) {
		boolean isConnected = false;
		int data = 0;

		isConnected = connect(new Throwable().getStackTrace()[0].getMethodName());
		if (isConnected) {
			try {
				ResultSet rs = stmt.executeQuery("SELECT * FROM Statistics");

				String sql = "UPDATE " + yourName + " set " + string + " = " + d + ";";
				stmt.executeUpdate(sql);

				while (rs.next()) {
					if (rs.getString("Person").equals(yourName)) {
						data = rs.getInt(string);
					}
				}

				close(true);
			} catch (Exception e) {
				LOG.println(1);
				LOG.println(e.toString(), className, e.getStackTrace()[1].getLineNumber(), 1);
			}
		} else {
			LOG.println(1);
			LOG.println(new Throwable().getStackTrace()[0].getMethodName() + " did not get a connection", className,
					new Throwable().getStackTrace()[1].getLineNumber(), 1);
		}
		return data;
	}

	public boolean getUpdateAvaliblility() {
		boolean isConnected = false;
		boolean data = false;

		isConnected = connect(new Throwable().getStackTrace()[0].getMethodName());
		if (isConnected) {
			try {
				ResultSet rs = stmt.executeQuery("SELECT * FROM Updates");
				data = rs.getBoolean("Bool");

				close(true);
			} catch (Exception e) {
				LOG.println(1);
				LOG.println(e.toString(), className, e.getStackTrace()[1].getLineNumber(), 1);
			}
		} else {
			LOG.println(1);
			LOG.println(new Throwable().getStackTrace()[0].getMethodName() + " did not get a connection", className,
					new Throwable().getStackTrace()[1].getLineNumber(), 1);
		}
		return data;
	}

	public String getUpdateData(String string) {
		boolean isConnected = false;
		String data = "unknown";

		isConnected = connect(new Throwable().getStackTrace()[0].getMethodName());
		if (isConnected) {
			try {
				ResultSet rs = stmt.executeQuery("SELECT * FROM Updates");
				data = rs.getString(string);

				close(true);
			} catch (Exception e) {
				LOG.println(1);
				LOG.println(e.toString(), className, e.getStackTrace()[1].getLineNumber(), 1);
			}
		} else {
			LOG.println(1);
			LOG.println(new Throwable().getStackTrace()[0].getMethodName() + " did not get a connection", className,
					new Throwable().getStackTrace()[1].getLineNumber(), 1);
		}
		return data;
	}
}

package chore.generator;

import java.util.ArrayList;

import chore.model.DatabaseHandler;
import chore.others.LOG;

public class GModel {
	ArrayList<String> hardM;
	ArrayList<String> mediumM;
	ArrayList<String> easyM;
	ArrayList<String> hardT;
	ArrayList<String> mediumT;
	ArrayList<String> easyT;
	ArrayList<String> hardW;
	ArrayList<String> mediumW;
	ArrayList<String> easyW;
	ArrayList<String> hardTh;
	ArrayList<String> mediumTh;
	ArrayList<String> easyTh;
	ArrayList<String> hardF;
	ArrayList<String> mediumF;
	ArrayList<String> easyF;
	ArrayList<String> hardS;
	ArrayList<String> mediumS;
	ArrayList<String> easyS;
	LOG LOG;
	String sql1;

	public GModel(LOG newLog) {
		LOG = newLog;
	}

	public void createChoresArrays(DatabaseHandler dbh) {
		hardM = dbh.getArray(3, "Monday");
		mediumM = dbh.getArray(2, "Monday");
		easyM = dbh.getArray(1, "Monday");

		hardT = dbh.getArray(3, "Tuesday");
		mediumT = dbh.getArray(2, "Tuesday");
		easyT = dbh.getArray(1, "Tuesday");

		hardW = dbh.getArray(3, "Wednesday");
		mediumW = dbh.getArray(2, "Wednesday");
		easyW = dbh.getArray(1, "Wednesday");

		hardTh = dbh.getArray(3, "Thursday");
		mediumTh = dbh.getArray(2, "Thursday");
		easyTh = dbh.getArray(1, "Thursday");

		hardF = dbh.getArray(3, "Friday");
		mediumF = dbh.getArray(2, "Friday");
		easyF = dbh.getArray(1, "Friday");

		hardS = dbh.getArray(3, "Saturday");
		mediumS = dbh.getArray(2, "Saturday");
		easyS = dbh.getArray(1, "Saturday");

	}

	public ArrayList<String> getHardM() {
		return hardM;
	}

	public ArrayList<String> getMediumM() {
		return mediumM;
	}

	public ArrayList<String> getEasyM() {
		return easyM;
	}

	public ArrayList<String> getHardT() {
		return hardT;
	}

	public ArrayList<String> getMediumT() {
		return mediumT;
	}

	public ArrayList<String> getEasyT() {
		return easyT;
	}

	public ArrayList<String> getHardW() {
		return hardW;
	}

	public ArrayList<String> getMediumW() {
		return mediumW;
	}

	public ArrayList<String> getEasyW() {
		return easyW;
	}

	public ArrayList<String> getHardTh() {
		return hardTh;
	}

	public ArrayList<String> getMediumTh() {
		return mediumTh;
	}

	public ArrayList<String> getEasyTh() {
		return easyTh;
	}

	public ArrayList<String> getHardF() {
		return hardF;
	}

	public ArrayList<String> getMediumF() {
		return mediumF;
	}

	public ArrayList<String> getEasyF() {
		return easyF;
	}

	public ArrayList<String> getHardS() {
		return hardS;
	}

	public ArrayList<String> getMediumS() {
		return mediumS;
	}

	public ArrayList<String> getEasyS() {
		return easyS;
	}

	public void insertRow2(String name, DatabaseHandler dbh, String sql2, String day) {
		dbh.insertRow(name, sql1, sql2, day);
	}

	public void insertRow(String name, DatabaseHandler dbh, String sqL1, String day) {
		sql1 = sqL1;
	}

	public void remove(String dayOfWeek, int diff, int index) {
		if ((dayOfWeek.equals("Monday")) && (diff == 3)) {
			System.out.println("Array size hardM: " + hardM.size());
			System.out.println("Index size hardM: " + index);
			System.out.println();
			hardM.remove(hardM.get(index));
		} else if ((dayOfWeek.equals("Monday")) && (diff == 2)) {
			System.out.println("Array size mediumM: " + mediumM.size());
			System.out.println("Index size mediumM: " + index);
			System.out.println();
			mediumM.remove(mediumM.get(index));
		} else if ((dayOfWeek.equals("Monday")) && (diff == 1)) {
			System.out.println("Array size easyM: " + easyM.size());
			System.out.println("Index size easyM: " + index);
			System.out.println();
			easyM.remove(easyM.get(index));
		} else if ((dayOfWeek.equals("Tuesday")) && (diff == 3)) {
			System.out.println("Array size hardT: " + hardT.size());
			System.out.println("Index size hardT: " + index);
			System.out.println();
			hardT.remove(hardT.get(index));
		} else if ((dayOfWeek.equals("Tuesday")) && (diff == 2)) {
			System.out.println("Array size mediumT: " + mediumT.size());
			System.out.println("Index size mediumT: " + index);
			System.out.println();
			mediumT.remove(mediumT.get(index));
		} else if ((dayOfWeek.equals("Tuesday")) && (diff == 1)) {
			System.out.println("Array size easyT: " + easyT.size());
			System.out.println("Index size easyT: " + index);
			System.out.println();
			easyT.remove(easyT.get(index));
		} else if ((dayOfWeek.equals("Wednesday")) && (diff == 3)) {
			System.out.println("Array size hardW: " + hardW.size());
			System.out.println("Index size hardW: " + index);
			System.out.println();
			hardW.remove(hardW.get(index));
		} else if ((dayOfWeek.equals("Wednesday")) && (diff == 2)) {
			System.out.println("Array size mediumW: " + mediumW.size());
			System.out.println("Index size mediumW: " + index);
			System.out.println();
			mediumW.remove(mediumW.get(index));
		} else if ((dayOfWeek.equals("Wednesday")) && (diff == 1)) {
			System.out.println("Array size easyW: " + easyW.size());
			System.out.println("Index size easyW: " + index);
			System.out.println();
			easyW.remove(easyW.get(index));
		} else if ((dayOfWeek.equals("Thursday")) && (diff == 3)) {
			System.out.println("Array size hardTh: " + hardTh.size());
			System.out.println("Index size hardTh: " + index);
			System.out.println();
			hardTh.remove(hardTh.get(index));
		} else if ((dayOfWeek.equals("Thursday")) && (diff == 2)) {
			System.out.println("Array size mediumTh: " + mediumTh.size());
			System.out.println("Index size mediumTh: " + index);
			System.out.println();
			mediumTh.remove(mediumTh.get(index));
		} else if ((dayOfWeek.equals("Thursday")) && (diff == 1)) {
			System.out.println("Array size easyTh: " + easyTh.size());
			System.out.println("Index size easyTh: " + index);
			System.out.println();
			easyTh.remove(easyTh.get(index));
		} else if ((dayOfWeek.equals("Friday")) && (diff == 3)) {
			System.out.println("Array size hardF: " + hardF.size());
			System.out.println("Index size hardF: " + index);
			System.out.println();
			hardF.remove(hardF.get(index));
		} else if ((dayOfWeek.equals("Friday")) && (diff == 2)) {
			System.out.println("Array size mediumF: " + mediumF.size());
			System.out.println("Index size mediumF: " + index);
			System.out.println();
			mediumF.remove(mediumF.get(index));
		} else if ((dayOfWeek.equals("Friday")) && (diff == 1)) {
			System.out.println("Array size easyF: " + easyF.size());
			System.out.println("Index size easyF: " + index);
			System.out.println();
			easyF.remove(easyF.get(index));
		} else if ((dayOfWeek.equals("Saturday")) && (diff == 3)) {
			System.out.println("Array size hardS: " + hardS.size());
			System.out.println("Index size hardS: " + index);
			System.out.println();
			hardS.remove(hardS.get(index));
		} else if ((dayOfWeek.equals("Saturday")) && (diff == 2)) {
			System.out.println("Array size mediumS: " + mediumS.size());
			System.out.println("Index size mediumS: " + index);
			System.out.println();
			mediumS.remove(mediumS.get(index));
		} else if ((dayOfWeek.equals("Saturday")) && (diff == 1)) {
			System.out.println("Array size easyS: " + easyS.size());
			System.out.println("Index size easyS: " + index);
			System.out.println();
			easyS.remove(easyS.get(index));
		}
	}
}
package Utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Models.Album;
import Models.Collection;

public  class Utility {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
	
	public static String ReadField(JTextField a) {
		return a.getText().toString();
	}

	public static boolean filled(JTextField a) {
		String s = ReadField(a);
		if (s.length() > 0)
			return true;
		else
			return false;
	}

	public static void showMD(String s, int i) {
		JOptionPane.showMessageDialog(null, s, "Hiba", i);
	}

	public static boolean goodDate(JTextField a) {
		String s = ReadField(a);
		Date testDate = null;
		try {
			testDate = sdf.parse(s);
		} catch (ParseException e) {
			return false;
		}
		if (sdf.format(testDate).equals(s))
			return true;
		else
			return false;
	}

	public static boolean goodInt(JTextField a) {
		String s = ReadField(a);
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static Date StringToDate(String s) {
		Date testDate = null, vid = null;
		try {
			testDate = sdf.parse(s);
		} catch (ParseException e) {
			return vid;
		}
		if (!sdf.format(testDate).equals(s)) {
			return vid;
		}
		return testDate;
	}

	public static int StringToInt(String s) { 
		int x = -55;
		x = Integer.parseInt(s);
		return x;
	}
	
	public static void AlbumArrayListToTableModel(ArrayList<Album> al, DefaultTableModel tm) {
		tm.setRowCount(0);
		for(int i=0;i<al.size();i++) {
			tm.addRow(new Object[] { new Boolean(false),al.get(i).getId(),al.get(i).getEloado(), al.get(i).getAlbum(), DateToString(al.get(i).getEvjarat()),al.get(i).getCim(),al.get(i).getStilus(), al.get(i).getZeneSzamokDb() });
		}
	}
	
	public static void CollectionArrayListToTableModel(ArrayList<Collection> cl, DefaultTableModel tm) {
		tm.setRowCount(0);
		for(int i=0;i<cl.size();i++) {
			tm.addRow(new Object[] { new Boolean(false),cl.get(i).getId(),cl.get(i).getName()});
		}
	}
	
	public static String DateToString(Date a) {
		return sdf.format(a).toString();
	}
	
	public static int CollectionArrayListFindId(ArrayList<Collection> a, int Id) {
		for(int i=0; i<a.size();i++) {
			if(a.get(i).getId() == Id) {
				return i;
			}
		}
		return -1;
	}
	
	public static void CollectionArrayListRemoveById(ArrayList<Collection> a, int Id) {
		int index = CollectionArrayListFindId(a, Id);
		if(index != -1) {
			a.remove(index);
		}
	}
	
	public static int AlbumArrayListFindId(ArrayList<Album> a, int Id) {
		for(int i=0; i<a.size();i++) {
			if(a.get(i).getId() == Id) {
				return i;
			}
		}
		return -1;
	}
	
	public static void AlbumArrayListRemoveById(ArrayList<Album> a, int Id) {
		int index = AlbumArrayListFindId(a, Id);
		if(index != -1) {
			a.remove(index);
		}
	}
	
	public static int Checked(DefaultTableModel tm) {
		int db = 0, jel = 0, x = 0;
		for (x = 0; x < tm.getRowCount(); x++)
			if ((Boolean) tm.getValueAt(x, 0)) { // ha kivan jelölve
				db++;
				jel = x;
			}
		if (db == 0)
			Utility.showMD("Nincs kijelölve a törlendõ rekord!", 0);

		if (db > 1)
			Utility.showMD("Több rekord van kijelölve!\nEgyszerrecsak egy rekord törölhetõ!", 0);
		if (db == 1) {
			return jel;
		}
		
		return -55;
	}
	
	
}

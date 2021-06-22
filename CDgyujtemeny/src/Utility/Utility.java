package Utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import GUI.CDMenu;
import GUI.CollectionView;
import GUI.NewAlbum;
import GUI.NewCollection;
import Models.Album;
import Models.Collection;

public class Utility {

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
		for (int i = 0; i < al.size(); i++) {
			tm.addRow(new Object[] { new Boolean(false), al.get(i).getId(), al.get(i).getEloado(), al.get(i).getAlbum(),
					DateToString(al.get(i).getEvjarat()), al.get(i).getCim(), al.get(i).getStilus(),
					al.get(i).getZeneSzamokDb() });
		}
	}

	public static void CollectionArrayListToTableModel(ArrayList<Collection> cl, DefaultTableModel tm) {
		tm.setRowCount(0);
		for (int i = 0; i < cl.size(); i++) {
			tm.addRow(new Object[] { new Boolean(false), cl.get(i).getId(), cl.get(i).getName(),
					Utility.NumberOfSongsInCollection(cl.get(i)) });
		}
	}

	public static int NumberOfSongsInCollection(Collection c) {
		int numberOfSongsInCollection = 0;
		for (int i = 0; i < c.getAlbums().size(); i++) {
			numberOfSongsInCollection += c.getAlbums().get(i).getZeneSzamokDb();
		}
		return numberOfSongsInCollection;
	}

	public static String DateToString(Date a) {
		return sdf.format(a).toString();
	}

	public static int CollectionArrayListFindId(ArrayList<Collection> a, int Id) {
		for (int i = 0; i < a.size(); i++) {
			if (a.get(i).getId() == Id) {
				return i;
			}
		}
		return -1;
	}

	public static void CollectionArrayListRemoveById(ArrayList<Collection> a, int Id) {
		int index = CollectionArrayListFindId(a, Id);
		if (index != -1) {
			a.remove(index);
		}
	}

	public static int AlbumArrayListFindId(ArrayList<Album> a, int Id) {
		for (int i = 0; i < a.size(); i++) {
			if (a.get(i).getId() == Id) {
				return i;
			}
		}
		return -1;
	}

	public static void AlbumArrayListRemoveById(ArrayList<Album> a, int Id) {
		int index = AlbumArrayListFindId(a, Id);
		if (index != -1) {
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
			Utility.showMD("Nincs kijelölve rekord!", 0);

		if (db > 1)
			Utility.showMD("Több rekord van kijelölve!\nEgyszerrecsak egy rekord lehet kijelölve!", 0);
		if (db == 1) {
			return jel;
		}

		return -55;
	}

	public static void ModdifySelectedCollection(JFrame F, int jel, DefaultTableModel tm,
			ArrayList<Collection> CollectionArrayList, ArrayList<Album> AlbumArrayList) {
		if (jel > -1) {
			int kodv = (int) tm.getValueAt(jel, 1);
			String collectionName = (String) tm.getValueAt(jel, 2);
			NewCollection createCollection = new NewCollection(F, AlbumArrayList, kodv, collectionName);
			createCollection.setVisible(true);
			int acvb = createCollection.Exit();
			if (acvb == 1) {
				Collection newCollection = createCollection.getCollection();
				Utility.CollectionArrayListRemoveById(CollectionArrayList, kodv);
				CollectionArrayList.add(newCollection);
				Utility.CollectionArrayListToTableModel(CollectionArrayList, tm);
			}
		}

	}

	public static void ViewCollection(JFrame F, DefaultTableModel tm, ArrayList<Collection> CollectionArrayList,
			ArrayList<Album> AlbumArrayList) {
		int jel = Utility.Checked(tm);
		if (jel > -1) {
			CollectionView collectionView = new CollectionView(F, CollectionArrayList.get(jel), AlbumArrayList);
			collectionView.setVisible(true);
		}
	}

	public static void DeleteCollection(DefaultTableModel tm, ArrayList<Collection> CollectionArrayList) {
		int jel = Utility.Checked(tm);
		if (jel > -1) {
			int Id = (int) tm.getValueAt(jel, 1);
			tm.removeRow(jel);
			Utility.CollectionArrayListRemoveById(CollectionArrayList, Id);
			Utility.showMD("A rekord törölve!", 1);
		}
	}

	public static void NewCollection(JFrame F, DefaultTableModel tm, ArrayList<Collection> CollectionArrayList,
			ArrayList<Album> AlbumArrayList) {
		int kodv = 0;
		if (CollectionArrayList.size() == 0) {
			kodv = 60;
		} else {
			kodv = CollectionArrayList.get(CollectionArrayList.size() - 1).getId() + 1;
			for (int i = 0; i < CollectionArrayList.size(); i++) {
				if (kodv < CollectionArrayList.get(i).getId()) {
					kodv = CollectionArrayList.get(i).getId() + 1;
				}
			}
		}
		NewCollection createCollection = new NewCollection(F, AlbumArrayList, kodv);
		createCollection.setVisible(true);
		int acvb = createCollection.Exit();
		if (acvb == 1) {
			Collection newCollection = createCollection.getCollection();
			CollectionArrayList.add(newCollection);
			Utility.CollectionArrayListToTableModel(CollectionArrayList, tm);
		}
	}

	public static void ModdifyCollection(JFrame F, DefaultTableModel tm, ArrayList<Collection> CollectionArrayList,
			ArrayList<Album> AlbumArrayList) {
		int jel = Utility.Checked(tm);
		Utility.ModdifySelectedCollection(F, jel, tm, CollectionArrayList, AlbumArrayList);
	}

	public static void NewAlbum(JFrame F, DefaultTableModel tm, ArrayList<Album> AlbumArrayList) {
		int kodv = 0;
		if (AlbumArrayList.size() == 0) {
			kodv = 60;
		} else {
			kodv = AlbumArrayList.get(AlbumArrayList.size() - 1).getId() + 1;
			for (int i = 0; i < AlbumArrayList.size(); i++) {
				if (kodv < AlbumArrayList.get(i).getId()) {
					kodv = AlbumArrayList.get(i).getId() + 1;
				}
			}
		}
		NewAlbum createAlbum = new NewAlbum(F, kodv);
		createAlbum.setVisible(true);
		System.out.println("album: " + createAlbum.Exit());
		if (createAlbum.Exit() == 1) {
			Album newAlbum = createAlbum.getAlbum();
			AlbumArrayList.add(newAlbum);
			Utility.AlbumArrayListToTableModel(AlbumArrayList, tm);
		}
	}

	public static void ModdifySelectedAlbum(JFrame F, int jel, DefaultTableModel tm, ArrayList<Album> AlbumArrayList) {
		if (jel > -1) {
			int kodv = (int) tm.getValueAt(jel, 1);
			int index = Utility.AlbumArrayListFindId(AlbumArrayList, kodv);
			NewAlbum createAlbum = new NewAlbum(F, kodv, AlbumArrayList.get(index));
			createAlbum.setVisible(true);
			int acvb = createAlbum.Exit();
			if (acvb == 1) {
				Album newAlbum = createAlbum.getAlbum();
				Utility.AlbumArrayListRemoveById(AlbumArrayList, kodv);
				AlbumArrayList.add(newAlbum);
				Utility.AlbumArrayListToTableModel(AlbumArrayList, tm);
			}
		}
	}

	public static void ModdifyAlbumn(JFrame F, DefaultTableModel tm, ArrayList<Album> AlbumArrayList) {
		int jel = Utility.Checked(tm);
		Utility.ModdifySelectedAlbum(F, jel, tm, AlbumArrayList);
	}

	public static void DeleteAlbum(DefaultTableModel tm, ArrayList<Album> AlbumArrayList) {
		int jel = Utility.Checked(tm);
		if (jel > -1) {
			int Id = (int) tm.getValueAt(jel, 1);
			tm.removeRow(jel);
			Utility.AlbumArrayListRemoveById(AlbumArrayList, Id);
			Utility.showMD("A rekord törölve!", 1);
		}
	}

}// end Utility

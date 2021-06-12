package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import GUI.Tables.AlbumTableModel;
import GUI.Tables.CollectionTableModel;
import IO.FileManager;
import Models.Album;
import Models.Collection;
import Utility.*;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;

public class CDMenu extends JFrame {

	private JPanel contentPane;
	private JTable table;

	private File fbe;

	private CollectionTableModel ctm;
	private AlbumTableModel atm;

	private ArrayList<Album> AlbumArrayList = new ArrayList<Album>();
	private ArrayList<Collection> CollectionArrayList = new ArrayList<Collection>();

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CDMenu frame = new CDMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CDMenu() {
		Object tableColumnsCTM[] = { "-", "Id", "Név", "Zeneszámok(db)" };
		ctm = new CollectionTableModel(tableColumnsCTM, 0);

		Object tableColumnsATM[] = { "-", "Id", "Elõadó", "Album", "Évjárat", "Cím", "Stílus", "Zeneszámok(db)" };
		atm = new AlbumTableModel(tableColumnsATM, 0);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 559, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 45, 513, 202);
		contentPane.add(scrollPane);

		table = new JTable(ctm);
		scrollPane.setViewportView(table);
		table.setAutoCreateRowSorter(true);
		TableColumn tc = null;
		for (int i = 0; i < 4; i++) {
			tc = table.getColumnModel().getColumn(i);
			if (i == 0 || i == 1 || i == 3)
				tc.setPreferredWidth(30);
			else {
				tc.setPreferredWidth(100);
			}
		} // end for

		table.setAutoCreateRowSorter(true);
		TableRowSorter<CollectionTableModel> trs = (TableRowSorter<CollectionTableModel>) table.getRowSorter();
		trs.setSortable(0, false);

		JButton btnBetoltes = new JButton("Betoltes");
		btnBetoltes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * FileDialog fd = new FileDialog(new Frame(), " ", FileDialog.LOAD);
				 * fd.setFile("*.csv"); fd.setVisible(true); if (fd.getFile() != null) { fbe =
				 * new File(fd.getDirectory(), fd.getFile()); FileManager.CsvReader(fbe, ctm); }
				 * // end if
				 */
				//fbe = new File("resources/testadatok.csv");
				//FileManager.CsvReader(fbe, ctm);

				fbe = new File("resources/testGyujtemenyek.csv");
				FileManager.CsvReaderCollection(fbe, CollectionArrayList);
				Utility.CollectionArrayListToTableModel(CollectionArrayList, ctm);
				
				fbe = new File("resources/testAlbumok.csv");
				FileManager.CsvReaderAlbum(fbe, AlbumArrayList);
				Utility.AlbumArrayListToTableModel(AlbumArrayList, atm);

			}
		});
		btnBetoltes.setBounds(10, 11, 89, 23);
		contentPane.add(btnBetoltes);

		JButton btnKiiras = new JButton("Kiiras");
		btnKiiras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileManager.CsvWriterAlbum("resources/testAlbumok.csv", AlbumArrayList);
				FileManager.CsvWriterCollection("resources/testGyujtemenyek.csv", CollectionArrayList);
			}
		});
		btnKiiras.setBounds(104, 11, 89, 23);
		contentPane.add(btnKiiras);

		JButton button = new JButton("View Collection");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int db = 0, jel = 0, x = 0;
				for (x = 0; x < ctm.getRowCount(); x++)
					if ((Boolean) ctm.getValueAt(x, 0)) { //ha kivan jelölve
						db++;
						jel = x;
					}
				if (db == 0)
					Utility.showMD("Nincs kijelölve Gyûjtemény!", 0);
				
				if (db > 1)
					Utility.showMD("Több Gyûjtemény van kijelölve!", 0);
				if (db == 1) {
					CollectionView collectionView = new CollectionView(CDMenu.this, CollectionArrayList.get(jel));
					collectionView.setVisible(true);
				}
				
				
				
			}
		});
		button.setBounds(395, 258, 138, 23);
		contentPane.add(button);

		JButton btnNewCollection = new JButton("New Collection");
		btnNewCollection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
				NewCollection createCollection = new NewCollection(CDMenu.this, AlbumArrayList, kodv);
				createCollection.setVisible(true);
				int acvb = createCollection.Exit();
				System.out.println("collection: "+acvb);
				if (acvb == 1) {
					Collection newCollection = createCollection.getCollection();
					CollectionArrayList.add(newCollection);
					Utility.CollectionArrayListToTableModel(CollectionArrayList, ctm);
				}
			}
		});
		btnNewCollection.setBounds(20, 258, 138, 23);
		contentPane.add(btnNewCollection);

		JButton btnNewalbum = new JButton("NewAlbum");
		btnNewalbum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
				NewAlbum createAlbum = new NewAlbum(CDMenu.this, kodv);
				createAlbum.setVisible(true);
				System.out.println("album: "+createAlbum.Exit());
				if (createAlbum.Exit() == 1) {
					Album newAlbum = createAlbum.getAlbum();
					AlbumArrayList.add(newAlbum);
					Utility.AlbumArrayListToTableModel(AlbumArrayList, atm);
				}
			}
		});
		btnNewalbum.setBounds(20, 292, 138, 23);
		contentPane.add(btnNewalbum);

		JButton btnListOfAlbums = new JButton("List of albums");
		btnListOfAlbums.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlbumList albumList = new AlbumList(CDMenu.this, atm);
				albumList.setVisible(true);
			}
		});
		btnListOfAlbums.setBounds(395, 292, 138, 23);
		contentPane.add(btnListOfAlbums);

	}
}

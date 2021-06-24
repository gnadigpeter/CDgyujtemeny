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
		setBounds(100, 100, 559, 430);
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
				fbe = new File("resources/testAlbumok.csv");
				FileManager.CsvReaderAlbum(fbe, AlbumArrayList);
				Utility.AlbumArrayListToTableModel(AlbumArrayList, atm);

				fbe = new File("resources/testGyujtemenyek.csv");
				FileManager.CsvReaderCollection3(fbe, CollectionArrayList, AlbumArrayList);
				System.out.println("aaa");
				Utility.CollectionArrayListToTableModel(CollectionArrayList,AlbumArrayList, ctm);
				System.out.println("aaa");
			}
		});
		btnBetoltes.setBounds(20, 11, 89, 23);
		contentPane.add(btnBetoltes);

		JButton btnKiiras = new JButton("Kiiras");
		btnKiiras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileManager.CsvWriterAlbum("resources/testAlbumok.csv", AlbumArrayList);
				FileManager.CsvWriterCollection2("resources/testGyujtemenyek.csv", CollectionArrayList, AlbumArrayList);
			}
		});
		btnKiiras.setBounds(119, 11, 89, 23);
		contentPane.add(btnKiiras);

		JButton button = new JButton("View Collection");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Utility.ViewCollection(CDMenu.this, ctm, CollectionArrayList, AlbumArrayList);
			}
		});
		button.setBounds(20, 292, 138, 23);
		contentPane.add(button);

		JButton btnNewCollection = new JButton("New Collection");
		btnNewCollection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Utility.NewCollection(CDMenu.this, ctm, CollectionArrayList, AlbumArrayList);
			}
		});
		btnNewCollection.setBounds(20, 258, 138, 23);
		contentPane.add(btnNewCollection);

		JButton btnNewalbum = new JButton("NewAlbum");
		btnNewalbum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Utility.NewAlbum(CDMenu.this, atm, AlbumArrayList);
			}
		});
		btnNewalbum.setBounds(395, 258, 138, 23);
		contentPane.add(btnNewalbum);

		JButton btnListOfAlbums = new JButton("List of albums");
		btnListOfAlbums.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlbumList albumList = new AlbumList(CDMenu.this, atm, AlbumArrayList);
				albumList.setVisible(true);
			}
		});
		btnListOfAlbums.setBounds(395, 292, 138, 23);
		contentPane.add(btnListOfAlbums);

		JButton btnDelete = new JButton("Delete Collection");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Utility.DeleteCollection(ctm, CollectionArrayList);
			}
		});
		btnDelete.setBounds(20, 360, 138, 23);
		contentPane.add(btnDelete);

		JButton btnModdifyCollection = new JButton("Moddify Collection");
		btnModdifyCollection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Utility.ModdifyCollection(CDMenu.this, ctm, CollectionArrayList, AlbumArrayList);
			}
		});
		btnModdifyCollection.setBounds(20, 326, 138, 23);
		contentPane.add(btnModdifyCollection);
		
		JButton btnRefress = new JButton("refress");
		btnRefress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Utility.RefressTable(CollectionArrayList, AlbumArrayList, ctm);
			}
		});
		btnRefress.setBounds(210, 258, 138, 23);
		contentPane.add(btnRefress);

	}
}

package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import GUI.Tables.AlbumTableModel;
import Models.Album;
import Models.Collection;
import Utility.Utility;

public class AlbumList extends JDialog {
	private JTable table;
	private AlbumTableModel atm;

	public AlbumList(JFrame f, AlbumTableModel betm, ArrayList<Album> AlbumArrayList) {
		super(f, "List of albums", true);
		atm = betm;
		setBounds(100, 100, 500, 300);
		getContentPane().setLayout(null);

		JButton btnBezar = new JButton("Close");
		btnBezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				setVisible(false);
			}
		});
		btnBezar.setBounds(377, 225, 97, 25);
		getContentPane().add(btnBezar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 482, 218);
		getContentPane().add(scrollPane);

		table = new JTable(atm);
		scrollPane.setViewportView(table);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Utility.DeleteAlbum(atm, AlbumArrayList);
			}
		});
		btnDelete.setBounds(189, 225, 97, 25);
		getContentPane().add(btnDelete);

		JButton btnModdifyAlbum = new JButton("Moddify");
		btnModdifyAlbum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Utility.ModdifyAlbumn(f, atm, AlbumArrayList);
			}
		});
		btnModdifyAlbum.setBounds(10, 225, 97, 25);
		getContentPane().add(btnModdifyAlbum);
	}
}// end class

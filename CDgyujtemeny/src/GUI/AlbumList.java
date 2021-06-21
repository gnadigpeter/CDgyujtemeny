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
import Utility.Utility;

public class AlbumList extends JDialog {
	private JTable table;
	private AlbumTableModel atm;
	
	public AlbumList(JFrame f, AlbumTableModel betm, ArrayList<Album> AlbumArrayList) {
		super(f, "List of albums", true);
		atm = betm;
		setBounds(100, 100, 500, 300);
		getContentPane().setLayout(null);

		JButton btnBezar = new JButton("Bezar");
		btnBezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				setVisible(false);
			}
		});
		btnBezar.setBounds(270, 219, 97, 25);
		getContentPane().add(btnBezar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 482, 218);
		getContentPane().add(scrollPane);

		table = new JTable(atm);
		scrollPane.setViewportView(table);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int db = 0, jel = 0, x = 0;
				for (x = 0; x < atm.getRowCount(); x++)
					if ((Boolean) atm.getValueAt(x, 0)) { // ha kivan jelölve
						db++;
						jel = x;
					}
				if (db == 0)
					Utility.showMD("Nincs kijelölve a törlendõ rekord!", 0);

				if (db > 1)
					Utility.showMD("Több rekord van kijelölve!\nEgyszerrecsak egy rekord törölhetõ!", 0);
				if (db == 1) {
					int Id = (int) atm.getValueAt(jel, 1);
					atm.removeRow(jel);
					Utility.AlbumArrayListRemoveById(AlbumArrayList,Id);
					Utility.showMD("A rekord törölve!", 1);
				}

			}
		});
		btnDelete.setBounds(86, 220, 97, 25);
		getContentPane().add(btnDelete);

		/*
		 * TableColumn tc = null; for (int i = 0; i < 6; i++) { tc =
		 * table.getColumnModel().getColumn(i); if (i == 0 || i == 1 || i == 5)
		 * tc.setPreferredWidth(30); else { tc.setPreferredWidth(100); } } // end for
		 * 
		 * table.setAutoCreateRowSorter(true); TableRowSorter<AlbumTableModel> trs =
		 * (TableRowSorter<AlbumTableModel>) table.getRowSorter(); trs.setSortable(0,
		 * false);
		 */
	}// end EmpList konstruktor
}// end class

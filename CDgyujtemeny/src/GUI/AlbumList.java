package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class AlbumList extends JDialog {
	private JTable table;
	private AlbumTableModel atm;

	public AlbumList(JFrame f, AlbumTableModel betm) {
		super(f, "List of albums", true);
		atm = betm;
		setBounds(100, 100, 500, 300);
		getContentPane().setLayout(null);

		JButton btnBezar = new JButton("Bezar");
		btnBezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose(); setVisible(false);
			}
		});
		btnBezar.setBounds(270, 219, 97, 25);
		getContentPane().add(btnBezar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 482, 218);
		getContentPane().add(scrollPane);

		table = new JTable(atm);
		scrollPane.setViewportView(table);
		
		/*
		TableColumn tc = null;
		for (int i = 0; i < 6; i++) {
			tc = table.getColumnModel().getColumn(i);
			if (i == 0 || i == 1 || i == 5)
				tc.setPreferredWidth(30);
			else {
				tc.setPreferredWidth(100);
			}
		} // end for

		table.setAutoCreateRowSorter(true);
		TableRowSorter<AlbumTableModel> trs = (TableRowSorter<AlbumTableModel>) table.getRowSorter();
		trs.setSortable(0, false);
		*/
	}// end EmpList konstruktor
}// end class

